package com.apschulewitz.resdb.security.filter;

import com.apschulewitz.resdb.common.ApplicationResponse;
import com.apschulewitz.resdb.common.ResponseStatus;
import com.apschulewitz.resdb.security.service.UserAuthenticationService;
import com.apschulewitz.resdb.security.exception.JwtBadSignatureException;
import com.apschulewitz.resdb.security.exception.JwtExpirationException;
import com.apschulewitz.resdb.security.exception.MalformedJwtException;
import com.apschulewitz.resdb.security.model.dto.JwtUser;
import com.apschulewitz.resdb.security.model.dto.UserLogonDto;
import com.google.gson.Gson;
import com.nimbusds.jose.JOSEException;
import com.nimbusds.jwt.SignedJWT;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.util.Collection;
import java.util.Date;
import java.util.Enumeration;
import java.util.Optional;

import static com.apschulewitz.resdb.security.controller.JwtUtil.*;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@Slf4j
public class JwtTokenAuthenticationFilter extends GenericFilterBean {

    private RequestMatcher requestMatcher;
    private String secretKey;

    private Gson gson = new Gson();

    private final UserAuthenticationService userAuthenticationService;

    @Value("${environment")
    private String environmentName;

    public JwtTokenAuthenticationFilter(UserAuthenticationService userAuthenticationService, String path, String secretKey) {
        this.userAuthenticationService = userAuthenticationService;
        this.requestMatcher = new AntPathRequestMatcher(path);
        this.secretKey = secretKey;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        log.info("Processing request from host {} using auth type {}", request.getHeader("host"),
                                                                        request.getAuthType() == null ? "<not set>" : request.getAuthType());
        if (request.getHeaderNames() != null) {

            String headerName;
            Enumeration<String> headerNames = request.getHeaderNames();
            while (headerNames.hasMoreElements()) {
                headerName = headerNames.nextElement();
                log.info("    Provided header {} value {}", headerName, request.getHeader(headerName));
            }

        }

        if (!requiresAuthentication(request)) {
            log.trace("Received request that does not require authentication: " + request);
            /*
            If the URL requested doesn't match the URL handled by the filter then pass it on to the next filter
             */
            filterChain.doFilter(request, response);
            return;
        }

        Optional<String> header = Optional.ofNullable(getAuthHeader(request));
        boolean isValid = false;
        if (header.isPresent()) {

            // header contains: Basic YWRyaWFuOnBhc3N3b3Jk
            Optional<UserLogonDto> user = Optional.ofNullable(gson.fromJson(header.get(), UserLogonDto.class));
            if (user.isPresent() && !header.get().startsWith("Bearer") /*&& "dev".equals(environmentName)*/) {
                isValid = validateUserViaDbAuthentication(user.get());
            } else if (header.get().startsWith("Bearer")) {
                isValid = validJwtAuthentication(request, response, filterChain);
            }
        } else {
            // login request does not contain a Basic AUTHORISATION header
            log.info("Request: {}", request);
        }

        if (!isValid) {
            log.trace("Received request {} that does require authentication but header is {}", request, header);
            /*
            If there's no authentication information, then pass to the next filter. The SecurityContext will be
            analysed by the next filter that will throw AuthenticationException's if necessary.
             */
            filterChain.doFilter(request, response);
            return;
        }

        /* SecurityContext is then cleared as we are stateless */
        SecurityContextHolder.clearContext();
    }

    private boolean validateUserViaDbAuthentication(UserLogonDto userLogonDto) {
        log.info("Validating");
        ApplicationResponse applicationResponse;
//        UserAccount userAccount =  null;
        try {
            applicationResponse = userAuthenticationService.authenticateUser(userLogonDto.getUserName(), userLogonDto.getPassword());
            SecurityContextHolder.getContext().setAuthentication((Authentication) applicationResponse.getData().get(0));
        } catch (Exception e) {
            log.error("Error occurred authenticating user " + userLogonDto.getUserName() + ": " + e.getMessage(), e);
            return false;
        }

        if (applicationResponse.getResponseStatus() != null && !applicationResponse.getResponseStatus().equals(ResponseStatus.OK))
            return false;

        return true;
    }

    private boolean validJwtAuthentication(HttpServletRequest request,
                                           HttpServletResponse response,
                                           FilterChain filterChain) throws IOException, ServletException {
        String authHeader = getAuthHeader(request);
        if (authHeader.startsWith("Bearer")) {
            try {
            /*
            The token is extracted from the header. It is then checked (signatures and expiration). An Authentication is
            then created and registered in the SecurityContext. The SecurityContext will be analysed by the next filter
            that will throw an Exception if necessary.
             */
                SignedJWT jwt = extractAndDecodeJwt(authHeader);
                checkAuthenticationAndValidity(jwt);
                Authentication auth = buildAuthenticationFromJwt(jwt, request);
                SecurityContextHolder.getContext().setAuthentication(auth);

                filterChain.doFilter(request, response);
            } catch (JwtExpirationException jee) {
                throw new AccountExpiredException("Token is not valid anymore");
            } catch (JwtBadSignatureException jbse) {
                throw new MalformedJwtException("Token is malformed");
            } catch (ParseException pe) {
                throw new MalformedJwtException("Error parsing token");
            } catch (JOSEException je) {
                throw new MalformedJwtException("Error with token");
            }
        }

        return true;
    }

    private boolean requiresAuthentication(HttpServletRequest request) {
        return requestMatcher.matches(request);
    }

    private SignedJWT extractAndDecodeJwt(String authHeader) throws ParseException {
//        String authHeader = request.getHeader(AUTHORIZATION);
        String token = authHeader.substring("Bearer ".length());
        return parse(token);
    }

    private void checkAuthenticationAndValidity(SignedJWT jwt) throws ParseException, JOSEException {
        // assertNotExpired(jwt); // token renewal is not available and hence no expiry date is set.
        // Enable check above when token renewal is in place.
        assertValidSignature(jwt, secretKey);
    }

    private Authentication buildAuthenticationFromJwt(SignedJWT jwt, HttpServletRequest request) throws ParseException {
        String userName = getUsername(jwt);
        Collection<? extends GrantedAuthority> authorities = getRoles(jwt);
        Date creationDate = getIssueTime(jwt);
        JwtUser userDetails = new JwtUser(userName, creationDate, authorities);

        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, authorities);
        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        return authentication;
    }

    private String getAuthHeader(HttpServletRequest request) {
        return request.getHeader(AUTHORIZATION);
    }
}
