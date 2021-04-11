package com.apschulewitz.resdb.security.service;

import com.apschulewitz.resdb.common.ApplicationResponse;
import com.apschulewitz.resdb.common.ResponseStatus;
import com.apschulewitz.resdb.common.model.entity.VersionStatus;
import com.apschulewitz.resdb.common.service.EmailServiceImpl;
import com.apschulewitz.resdb.common.utils.AuthenticationUtils;
import com.apschulewitz.resdb.security.UserRegistrationConfiguration;
import com.apschulewitz.resdb.security.model.dao.UserAccountDao;
import com.apschulewitz.resdb.security.model.dao.UserRegistrationDao;
import com.apschulewitz.resdb.security.model.dto.UserRegistrationDto;
import com.apschulewitz.resdb.security.model.entity.RegistrationStatus;
import com.apschulewitz.resdb.security.model.entity.UserAccount;
import com.apschulewitz.resdb.security.model.entity.UserRegistration;
import com.apschulewitz.resdb.security.model.mapper.UserRegistrationMapper;
import freemarker.template.TemplateException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
public class UserRegistrationService {

  private static final String EMAIL_SUBJECT_VERIFY_USER = "Research Database Application - Verify Your Email";

  private static final String TEMPLATE_BASE_PATH = "templates/email/user_registration";
  private static final String TEMPLATE_RESET_PASSWORD = TEMPLATE_BASE_PATH + "/reset_password.html";
  private static final String TEMPLATE_VERIFY_USER = "verify_user.html";

  private static final String TEMPLATE_FIELD_RECIPIENT_NAME = "recipientName";
  private static final String TEMPLATE_FIELD_RESET_PASSWORD = "reset_password_url";
  private static final String TEMPLATE_FIELD_SENDER_NAME = "senderName";
  private static final String TEMPLATE_FIELD_TEXT = "text";
  private static final String TEMPLATE_FIELD_VERIFY_USER_URL = "verify_user_url";

  private static final String TEMPLATE_FIELD_EXPIRY_TIMESTAMP = "expiry_timestamp";

  private final String DEFAULT_USER_LOCALE = "en";

  private final UserRegistrationConfiguration userRegistrationConfiguration;
  private final UserAccountDao userAccountDao;
  private final UserRegistrationDao userRegistrationDao;
  private final UserRegistrationMapper userRegistrationMapper;

  private final AuthenticationUtils authenticationUtils;

  private EmailServiceImpl emailService;

  private boolean sendUserVerificationEmailWhenRegistering;

  public UserRegistrationService(UserRegistrationConfiguration userRegistrationConfiguration,
                                 UserAccountDao userAccountDao,
                                 UserRegistrationDao userRegistrationDao,
                                 UserRegistrationMapper userRegistrationMapper,
                                 AuthenticationUtils authenticationUtils,
                                 EmailServiceImpl emailService,
                                 Boolean sendUserVerificationEmailWhenRegistering) {
    this.userRegistrationConfiguration = userRegistrationConfiguration;
    this.userAccountDao = userAccountDao;
    this.userRegistrationDao = userRegistrationDao;
    this.userRegistrationMapper = userRegistrationMapper;
    this.authenticationUtils = authenticationUtils;
    this.emailService = emailService;
    this.sendUserVerificationEmailWhenRegistering = sendUserVerificationEmailWhenRegistering;
  }

  /**
   * Method <CODE>registerUser</CODE> saves the user's details to the list of registrations.
   *
   * @param user holds the details for the user being registered
   * @return an ApplicationResponse holding the result of registering the user
   * @see UserRegistrationDto
   * @see ApplicationResponse
   */
  public ApplicationResponse<UserRegistrationDto> registerUser(@NotNull UserRegistrationDto user) {
    ApplicationResponse<UserRegistrationDto> response = new ApplicationResponse<>();
    UserRegistration userRegistration;
    UserRegistrationDto savedRegistration = null;

    log.info("Processing registration request for user {} {}", user.getFirstName(), user.getFamilyName());
    try {
      userRegistration = userRegistrationMapper.toEntity(user);
      userRegistration.setRegistrationStatus(RegistrationStatus.Initiated);
      userRegistration.setVerificationCode(UUID.randomUUID().toString()); // generate unique code
      userRegistration.setStatus(VersionStatus.New);
      userRegistration.setCreatedBy(authenticationUtils.getCurrentAuthenticatedUser());
      userRegistration.setVersionNumber(1L);

      UserRegistration saved = userRegistrationDao.save(userRegistration);
      savedRegistration = userRegistrationMapper.toDto(saved);
      response.setResponseStatus(ResponseStatus.USER_REGISTRATION_INITIATED);
      response.setData(Collections.singletonList(savedRegistration));

    } catch (Exception e) {
      response.setMessage("Error saving user registration request: " + e.getMessage());
      response.setResponseStatus(ResponseStatus.ERROR);
      log.error(response.getMessage(), e);
    }

    if (this.sendUserVerificationEmailWhenRegistering) {
      ApplicationResponse<UserRegistrationDto> emailResponse = sendVerifyUserEmail(user);
      response.setResponseStatus(emailResponse.getResponseStatus());
      response.setMessage(emailResponse.getMessage());

      if (ResponseStatus.ERROR.equals(response.getResponseStatus())) {
        return response; // TODO do we need to update the saved registration to be in error ?
      }

      // update registration details
      savedRegistration.setRegistrationStatus(RegistrationStatus.VerifyEmailSent.name());
      UserRegistration registeredUser = userRegistrationDao.save(userRegistrationMapper.toEntity(savedRegistration));
      response.setData(Collections.singletonList(userRegistrationMapper.toDto(registeredUser)));
      response.setResponseStatus(ResponseStatus.USER_REGISTRATION_VERIFY_EMAIL_SENT);
    }

    return response;
  }

  private ApplicationResponse<UserRegistrationDto> sendVerifyUserEmail(UserRegistrationDto user) {
    // send verification email to user that they must respond to before it expires
    ApplicationResponse<UserRegistrationDto> response = new ApplicationResponse<>();

    Map<String, Object> templateModel = new HashMap<>();
    templateModel.put(TEMPLATE_FIELD_RECIPIENT_NAME, user.getEmail());
    templateModel.put(TEMPLATE_FIELD_SENDER_NAME,emailService.getEmailConfiguration().getEmailDefaultFrom());
    templateModel.put(TEMPLATE_FIELD_EXPIRY_TIMESTAMP, getVerifyUserExpiryTimestamp());
    templateModel.put(TEMPLATE_FIELD_VERIFY_USER_URL, userRegistrationConfiguration.verifyUserUrl());

    try {
      emailService.sendTemplateMessage(user.getEmail(), EMAIL_SUBJECT_VERIFY_USER, getTemplatePath(TEMPLATE_VERIFY_USER), templateModel);
    } catch (IOException e) {
      response.setMessage("Error sending verify user email: " + e.getMessage());
      response.setResponseStatus(ResponseStatus.ERROR);
      log.error(response.getMessage(), e);
    } catch (TemplateException e) {
      response.setMessage("Error loading email template whilst sending verify user email: " + e.getMessage());
      response.setResponseStatus(ResponseStatus.ERROR);
      log.error(response.getMessage(), e);
    }

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

  private String getTemplatePath(String templateName) {
    return TEMPLATE_BASE_PATH + "/" + getUserLocale() + "/" + templateName;
  }

  private String getUserLocale() {
    String userLocale = null;
    String authenticateduser = authenticationUtils.getCurrentAuthenticatedUser();
    Optional<UserAccount> user = Optional.ofNullable(userAccountDao.findByLogonName(authenticateduser));
    if (user.isPresent()) {
      if (user.get().getPreferredLanguage() != null) {
        return user.get().getPreferredLanguage().getIso6391Code1();
      }
    }
    return DEFAULT_USER_LOCALE;
  }

  private ZonedDateTime getVerifyUserExpiryTimestamp() {
    return ZonedDateTime.now(ZoneOffset.UTC).plus(15, ChronoUnit.MINUTES);
  }

//  private URI generateUserVerifyUrl() {
//    return new UriBuilder()
//      .scheme()
//  }
}
