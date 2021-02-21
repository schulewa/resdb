package com.apschulewitz.resdb.security.model.helper;

import com.apschulewitz.resdb.common.model.entity.DataOperation;
import com.apschulewitz.resdb.common.model.entity.VersionStatus;
import com.apschulewitz.resdb.refdata.model.dto.AddressTypeDto;
import com.apschulewitz.resdb.refdata.model.entity.AddressType;
import com.apschulewitz.resdb.refdata.model.entity.Language;
import com.apschulewitz.resdb.refdata.model.entity.LanguageGroup;
import com.apschulewitz.resdb.refdata.model.entity.Region;
import com.apschulewitz.resdb.research.model.AbstractTestHelper;
import com.apschulewitz.resdb.security.model.AuthenticationResult;
import com.apschulewitz.resdb.security.model.dto.UserRegistrationDto;
import com.apschulewitz.resdb.security.model.entity.AccountStatus;
import com.apschulewitz.resdb.security.model.entity.Permission;
import com.apschulewitz.resdb.security.model.entity.RegistrationStatus;
import com.apschulewitz.resdb.security.model.entity.UserAccount;
import com.apschulewitz.resdb.security.model.entity.UserGroup;
import com.apschulewitz.resdb.security.model.entity.UserGroupMembership;
import com.apschulewitz.resdb.security.model.entity.UserGroupPermission;
import com.apschulewitz.resdb.security.model.entity.UserPassword;
import com.apschulewitz.resdb.security.model.entity.UserRegistration;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Component
public class UserRegistrationTestHelper extends AbstractTestHelper<UserRegistration, UserRegistrationDto>  {

  public static final String TEST_USER_NAME = "testuser";
  public static final String TEST_USER_PASSWORD = "testpassword";

  public static final LocalDate VALID_FROM_DATE = LocalDate.of(2019, 1, 1);
  public static final LocalTime VALID_FROM_TIME = LocalTime.of(9, 1, 1, 0);

  public static final LocalDateTime VALID_FROM_DATE_TIME = LocalDateTime.of(VALID_FROM_DATE, VALID_FROM_TIME);

  @Override
  public UserRegistration constructUnsavedMinimalEntity() {
    return UserRegistration.builder()
      .email("wc@crapper.com")
      .firstName("Winston")
      .familyName("Churchill")
      .registrationStatus(RegistrationStatus.Initiated)
      .build();
  }

  @Override
  public UserRegistration constructNewEntityWithAllValues() {
    return UserRegistration.builder()
      .createdBy(TEST_USER_NAME)
      .email("wc@crapper.com")
      .emailVerified(ZonedDateTime.now(ZoneOffset.UTC))
      .firstName("Winston")
      .familyName("Churchill")
      .id(1L)
      .lastUpdated(ZonedDateTime.now(ZoneOffset.UTC))
      .registrationStatus(RegistrationStatus.Initiated)
      .status(VersionStatus.New)
      .updatedBy(TEST_USER_NAME)
      .verificationCode(UUID.randomUUID().toString())
      .versionNumber(1L)
      .build();
  }

  @Override
  public UserRegistrationDto constructUnsavedMinimalDto() {
    return UserRegistrationDto.builder()
      .email("wc@crapper.com")
      .firstName("Winston")
      .familyName("Churchill")
      .registrationStatus(RegistrationStatus.Initiated.name())
      .build();
  }

  @Override
  public List<UserRegistrationDto> constructListOfUnsavedMinimalDto() {
    return null;
  }

  @Override
  public UserRegistrationDto constructNewDtoWithAllValues() {
    return UserRegistrationDto.builder()
      .createdBy("testuser")
      .email("wc@crapper.com")
      .emailVerified(ZonedDateTime.now(ZoneOffset.UTC))
      .firstName("Winston")
      .familyName("Churchill")
      .id(ID.incrementAndGet())
      .lastUpdated(ZonedDateTime.now(ZoneOffset.UTC))
      .registrationStatus(RegistrationStatus.Initiated.name())
      .status("New")
      .updatedBy("testuser")
      .verificationCode("CODE1234")
      .versionNumber(1L)
      .build();
  }
}
