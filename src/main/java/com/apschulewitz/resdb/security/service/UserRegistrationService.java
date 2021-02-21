package com.apschulewitz.resdb.security.service;

import com.apschulewitz.resdb.common.ApplicationResponse;
import com.apschulewitz.resdb.common.ResponseStatus;
import com.apschulewitz.resdb.common.model.entity.VersionStatus;
import com.apschulewitz.resdb.common.utils.AuthenticationUtils;
import com.apschulewitz.resdb.security.model.dao.UserRegistrationDao;
import com.apschulewitz.resdb.security.model.dto.UserRegistrationDto;
import com.apschulewitz.resdb.security.model.entity.RegistrationStatus;
import com.apschulewitz.resdb.security.model.entity.UserRegistration;
import com.apschulewitz.resdb.security.model.mapper.UserRegistrationMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.validation.constraints.NotNull;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
public class UserRegistrationService {

  private final UserRegistrationDao userRegistrationDao;
  private final UserRegistrationMapper userRegistrationMapper;

  private final AuthenticationUtils authenticationUtils;

  public UserRegistrationService(UserRegistrationDao userRegistrationDao,
                                 UserRegistrationMapper userRegistrationMapper,
                                 AuthenticationUtils authenticationUtils) {
    this.userRegistrationDao = userRegistrationDao;
    this.userRegistrationMapper = userRegistrationMapper;
    this.authenticationUtils = authenticationUtils;
  }

  public ApplicationResponse<UserRegistrationDto> registerUser(@NotNull UserRegistrationDto user) {
    ApplicationResponse<UserRegistrationDto> response = new ApplicationResponse<>();
    String message = "";
    ResponseStatus responseStatus;
    UserRegistration userRegistration;
    UserRegistrationDto savedRegistration = null;

    log.info("Processing registration request for user " + user);
    try {
      userRegistration = userRegistrationMapper.toEntity(user);
      userRegistration.setRegistrationStatus(RegistrationStatus.Initiated);
      userRegistration.setVerificationCode(UUID.randomUUID().toString()); // generate unique code
      userRegistration.setStatus(VersionStatus.New);
      userRegistration.setCreatedBy(authenticationUtils.getCurrentAuthenticatedUser());
      userRegistration.setVersionNumber(1L);

      UserRegistration saved = userRegistrationDao.save(userRegistration);
      savedRegistration = userRegistrationMapper.toDto(saved);
      responseStatus = ResponseStatus.USER_REGISTRATION_INITIATED;
    } catch (Exception e) {
      message = "Error processing user registration request: " + e.getMessage();
      responseStatus = ResponseStatus.ERROR;
      log.error(message, e);
    }

    response.setResponseStatus(responseStatus);
    response.setMessage(message);
    response.setData(Arrays.asList(savedRegistration));

    return response;
  }

  public ApplicationResponse<UserRegistrationDto> verifyUser(@NotNull UserRegistrationDto user) {
    ApplicationResponse<UserRegistrationDto> response = new ApplicationResponse<>();
    String message = "";
    ResponseStatus responseStatus;
    UserRegistration userRegistration = null;
    UserRegistrationDto savedRegistration = null;

    log.info("Processing request for new user registration verification code for user " + user.getEmail());

    // look up existing registration request
    try {
      if (!StringUtils.isEmpty(user.getVerificationCode())) {
        Optional<UserRegistration> existing = userRegistrationDao.findByVerificationCode(user.getVerificationCode());
        if (existing.isPresent()) {
          userRegistration = existing.get();
        }
      } else if (!StringUtils.isEmpty(user.getEmail())){
        Optional<UserRegistration> existing = userRegistrationDao.findByEmail(user.getEmail());
        if (existing.isPresent()) {
          userRegistration = existing.get();
        }
      } else {
        message = "Unable to find existing user registration as neither verification code nor email provided";
        responseStatus = ResponseStatus.ERROR;
        response.setMessage(message);
        response.setResponseStatus(responseStatus);
        log.error(message);
        return response;
      }
    } catch (Exception e) {
      response.setResponseStatus(ResponseStatus.ERROR);
      response.setMessage(e.getMessage());
      return response;
    }

    // then update the registration with the newly generated verification code
    try {
      userRegistration.setEmailVerified(ZonedDateTime.now(ZoneOffset.UTC));
      userRegistration.setRegistrationStatus(RegistrationStatus.Verified);
      userRegistration.setLastUpdated(ZonedDateTime.now(ZoneOffset.UTC));
      userRegistration.setStatus(VersionStatus.Amend);
      userRegistration.setUpdatedBy(authenticationUtils.getCurrentAuthenticatedUser());
      userRegistration.setVersionNumber(userRegistration.getVersionNumber() + 1);
      UserRegistration saved = userRegistrationDao.save(userRegistration);
      savedRegistration = userRegistrationMapper.toDto(saved);
      responseStatus = ResponseStatus.USER_REGISTRATION_VERIFIED;
      log.info("User {} verified with code {} at {}", userRegistration.getEmail(),
                                                      userRegistration.getVerificationCode(),
                                                      userRegistration.getEmailVerified());
    } catch (Exception e) {
      message = "Error processing request for new user registration verification code: " +
                user.getVerificationCode() + e.getMessage();
      responseStatus = ResponseStatus.ERROR;
      log.error(message, e);
    }

    response.setResponseStatus(responseStatus);
    response.setMessage(message);
    response.setData(Arrays.asList(savedRegistration));

    log.info("Request for new user registration verification code processed: " + responseStatus.name());
    return response;
  }

  public ApplicationResponse<UserRegistrationDto> acceptUser(@NotNull UserRegistrationDto user) {
    return null;
  }

  public ApplicationResponse<UserRegistrationDto> rejectUser(@NotNull UserRegistrationDto user) {
    return null;
  }
}
