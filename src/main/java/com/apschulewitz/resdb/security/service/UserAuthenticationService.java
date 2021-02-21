package com.apschulewitz.resdb.security.service;

import com.apschulewitz.resdb.common.ApplicationResponse;
import com.apschulewitz.resdb.common.ResponseStatus;
import com.apschulewitz.resdb.security.model.dao.UserAccountDao;
import com.apschulewitz.resdb.security.model.dto.UserDto;
import com.apschulewitz.resdb.security.model.entity.AccountStatus;
import com.apschulewitz.resdb.security.model.entity.Permission;
import com.apschulewitz.resdb.security.model.entity.UserAccount;
import com.apschulewitz.resdb.security.model.entity.UserGroupMembership;
import com.apschulewitz.resdb.security.model.entity.UserGroupPermission;
import com.apschulewitz.resdb.security.model.mapper.PermissionMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;


@Slf4j
@Service
public class UserAuthenticationService {

    private final UserAccountDao userAccountDao;
    private final AuthenticationManager authenticationManager;

    private final CsrfTokenRepository csrfTokenRepository;

    private final PasswordEncoder passwordEncoder;

    public UserAuthenticationService(UserAccountDao userAccountDao,
                                     AuthenticationManager authenticationManager,
                                     PasswordEncoder passwordEncoder,
                                     CsrfTokenRepository csrfTokenRepository) {
        this.userAccountDao = userAccountDao;
        this.authenticationManager = authenticationManager;
        this.passwordEncoder = passwordEncoder;
        this.csrfTokenRepository = csrfTokenRepository;
    }

    public ApplicationResponse<UserDto> authenticateUser(String userName, String password) {
        ApplicationResponse<UserDto> response;
        ResponseStatus responseStatus = ResponseStatus.AUTHENTICATED;
        String message = "";
        UserAccount userAccount;
        UserDto userDto = null;

        log.info("Processing authentication request for user " + userName);
        try {
            userAccount = userAccountDao.findByLogonName(userName);
            if (userAccount != null) {

                if (AccountStatus.Locked.equals(userAccount.getStatus()) ||
                        AccountStatus.Suspended.equals(userAccount.getStatus()) ||
                        AccountStatus.Inactive.equals(userAccount.getStatus())) {
                    responseStatus = ResponseStatus.ACCOUNT_LOCKED;
                } else if (AccountStatus.Active.equals(userAccount.getStatus())) {
                    log.info("User account {} is active", userAccount.getLogonName());
                    // check the user plain password against the encoded version in the database
                    if (userAccount.getCurrentPassword().isPresent() && passwordEncoder.matches(password, userAccount.getCurrentPassword().get().getPassword())) {
                      CsrfToken csrfToken = csrfTokenRepository.generateToken(null);
                      PermissionMapper permissionMapper = new PermissionMapper();
                      userDto = UserDto.builder().logonName(userName)
                        .permissions(permissionMapper.toDto(userAccount.getGroupMemberships()))
                        .familyName(userAccount.getFamilyName())
                        .firstName(userAccount.getFirstName())
                        .id(userAccount.getId())
                        .status(userAccount.getStatus())
                        .token(csrfToken.getToken())
                        .build();
                      userAccount.setLastLogon(LocalDateTime.now());
                      userAccountDao.save(userAccount);
                      log.info("update last logon time to {} for user {}", userAccount.getLastLogon(), userAccount.getLogonName());
//                        authenticationManager.authenticate(jwtUser); // TODO should we call this ??
                    } else {
                      log.info("Invalid credentials supplied for user {}", userAccount.getLogonName());
                        responseStatus = ResponseStatus.INVALID_CREDENTIALS;
                    }
                } else if (AccountStatus.PasswordNeedsResetting.equals(userAccount.getStatus())) {
                    log.info("User account {} password needs resetting", userAccount.getLogonName());
                    responseStatus = ResponseStatus.PASSWORD_NEEDS_RESET;
                } else if (AccountStatus.Unknown.equals(userAccount.getStatus())) {
                  log.info("User account {} is locked", userAccount.getLogonName());
                  responseStatus = ResponseStatus.ACCOUNT_LOCKED;
                }

            } else {
                responseStatus = ResponseStatus.NO_DATA_FOUND;
                message = "User account not found for logon name ["  + userName + "]";
                log.info(message);
            }
        } catch (Exception e) {
            message = "Error processing logon request: " + e.getMessage();
            responseStatus = ResponseStatus.ERROR;
            log.error(message, e);
        }

        log.debug("User " + userName + " authenticated with status " + responseStatus.toString());

        if (ResponseStatus.AUTHENTICATED.equals(responseStatus)) {
            response = new ApplicationResponse<>(userDto); // userAccount
            response.setResponseStatus(ResponseStatus.AUTHENTICATED);
        } else {
            response = new ApplicationResponse<>(responseStatus, message);
        }

        return response;
    }

    /*
        @PostMapping("/authenticate")
    @Timed
    public ResponseEntity authorize(@Valid @RequestBody LoginVM loginVM, HttpServletResponse response) {

        UsernamePasswordAuthenticationToken authenticationToken =
            new UsernamePasswordAuthenticationToken(loginVM.getUsername(), loginVM.getPassword());

        try {
            Authentication authentication = this.authenticationManager.authenticate(authenticationToken);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            boolean rememberMe = (loginVM.isRememberMe() == null) ? false : loginVM.isRememberMe();
            String jwt = tokenProvider.createToken(authentication, rememberMe);
            response.addHeader(JWTConfigurer.AUTHORIZATION_HEADER, "Bearer " + jwt);
            return ResponseEntity.ok(new JWTToken(jwt));
        } catch (AuthenticationException ae) {
            log.trace("Authentication exception trace: {}", ae);
            return new ResponseEntity<>(Collections.singletonMap("AuthenticationException",
                ae.getLocalizedMessage()), HttpStatus.UNAUTHORIZED);
        }
    }
     */
//    private String getSecretKey() throws IOException {
//
//        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("secret.key");
//        StringBuilder sb = new StringBuilder();
//        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
//            String line;
//            while ((line = reader.readLine()) != null) {
//                sb.append(line);
//            }
//        }
//
//        return sb.toString();
//    }


//    private Date currentDate() {
//        return new Date(System.currentTimeMillis());
//    }

    public Collection<Permission> extractPermissions(boolean onlyActive, UserAccount userAccount) {
        log.info("Extracting permissions for user {}", userAccount);
        LocalDateTime now = LocalDateTime.now();

        Predicate<UserGroupMembership> isActiveGroup = (UserGroupMembership m) -> m != null &&
                (onlyActive &&
                 (now.isAfter(m.getValidFrom()) || now.isEqual(m.getValidFrom())) &&
                 (m.getValidTo() == null || now.isBefore(m.getValidTo()))) ||
                !onlyActive;

        Predicate<UserGroupMembership> hasPermissions = (UserGroupMembership m) -> m != null &&
                m.getGroup() != null && m.getGroup().getGroupPermissions() != null &&
                !m.getGroup().getGroupPermissions().isEmpty();

        Collection<Permission> perms = userAccount.getGroupMemberships().stream()
                .filter(isActiveGroup)
                .filter(hasPermissions)
                .flatMap(membership -> membership.getGroup().getGroupPermissions().stream()
                        .filter(p -> Permission.PermissionStatus.Active.equals(p.getPermission().getStatus()))
                        .map(UserGroupPermission::getPermission))
                .collect(Collectors.toList());

        return perms.stream() // check Permission status (but only if onlyActive is set)
                .filter(p -> !onlyActive || onlyActive && Permission.PermissionStatus.Active.equals(p.getStatus()))
                .collect(Collectors.toList());

    }

    public Optional<String> extractPermissionsAsString(boolean onlyActive, UserAccount userAccount) {
        Collection<Permission> activePermissions = extractPermissions(onlyActive, userAccount);
        if (activePermissions == null || activePermissions.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(activePermissions.stream()
                .map(Permission::getName)
                .collect(Collectors.joining(",")));
    }

}
