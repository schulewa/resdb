package com.apschulewitz.resdb.security.service;

import com.apschulewitz.resdb.common.ApplicationResponse;
import com.apschulewitz.resdb.common.ResponseStatus;
import com.apschulewitz.resdb.common.model.entity.VersionStatus;
import com.apschulewitz.resdb.common.utils.StringUtils;
import com.apschulewitz.resdb.security.model.dto.UserRegistrationDto;
import com.apschulewitz.resdb.security.model.entity.RegistrationStatus;
import com.apschulewitz.resdb.security.model.helper.UserRegistrationTestHelper;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@WithMockUser("testuser")
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@Slf4j
public class UserRegistrationServiceTest {

  @Autowired
  private UserRegistrationService userRegistrationService;

  @Autowired
  private UserRegistrationTestHelper testHelper;

  @Test
  public void given_valid_registration_when_registerUser_is_executed_then_return_no_error() {
    // given
    UserRegistrationDto userRegistrationDto = testHelper.constructUnsavedMinimalDto();

    // when
    ApplicationResponse<UserRegistrationDto> response = userRegistrationService.registerUser(userRegistrationDto);

    // then
    assertNotNull(response);
    assertEquals(ResponseStatus.USER_REGISTRATION_INITIATED, response.getResponseStatus());
    assertNotNull(response.getData());
    UserRegistrationDto savedUserRegistration = response.getData().get(0);
    assertNotNull(savedUserRegistration);
    assertFalse(StringUtils.isEmpty(savedUserRegistration.getVerificationCode()));
    assertEquals("testuser", savedUserRegistration.getCreatedBy());
    Long expectedVersionNumber = 1L;
    assertEquals(expectedVersionNumber, savedUserRegistration.getVersionNumber());
    assertNull(savedUserRegistration.getLastUpdated());
    assertEquals(RegistrationStatus.Initiated.name(), savedUserRegistration.getRegistrationStatus());
    assertEquals(VersionStatus.New.name(), savedUserRegistration.getStatus());
    assertNull(savedUserRegistration.getUpdatedBy());
    assertNull(savedUserRegistration.getEmailVerified());
  }

  @Test
  public void given_valid_registration_when_verifyUser_is_executed_then_return_no_error() {
    // given
    UserRegistrationDto unregisteredUserDto = testHelper.constructUnsavedMinimalDto();
    ApplicationResponse<UserRegistrationDto> registeredUserResponse = userRegistrationService.registerUser(unregisteredUserDto);
    UserRegistrationDto registeredUser = registeredUserResponse.getData().get(0);
    UserRegistrationDto userToBeVerified = UserRegistrationDto.builder()
      .email(unregisteredUserDto.getEmail())
      .verificationCode(registeredUser.getVerificationCode())
      .build();

    // when
    ApplicationResponse<UserRegistrationDto> response = userRegistrationService.verifyUser(userToBeVerified);

    // then
    assertNotNull(response);
    assertEquals(ResponseStatus.USER_REGISTRATION_VERIFIED, response.getResponseStatus());
    assertNotNull(response.getData());
    UserRegistrationDto savedUserRegistration = response.getData().get(0);
    assertNotNull(savedUserRegistration);
    assertEquals("testuser", savedUserRegistration.getCreatedBy());
    assertNotNull(savedUserRegistration.getEmailVerified());
    assertNotNull(savedUserRegistration.getId());
    assertNotNull(savedUserRegistration.getLastUpdated());
    assertEquals(RegistrationStatus.Verified.name(), savedUserRegistration.getRegistrationStatus());
    assertEquals(VersionStatus.Amend.name(), savedUserRegistration.getStatus());
    assertEquals("testuser", savedUserRegistration.getUpdatedBy());
    Long expectedVersionNumber = 2L;
    assertEquals(expectedVersionNumber, savedUserRegistration.getVersionNumber());
  }
}
