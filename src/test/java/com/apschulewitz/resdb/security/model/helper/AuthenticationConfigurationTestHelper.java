package com.apschulewitz.resdb.security.model.helper;

import com.apschulewitz.resdb.common.model.entity.VersionStatus;
import com.apschulewitz.resdb.research.model.AbstractTestHelper;
import com.apschulewitz.resdb.security.model.dto.AuthenticationConfigurationDto;
import com.apschulewitz.resdb.security.model.entity.AuthenticationConfiguration;
import org.springframework.stereotype.Component;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.List;

/**
 * <CODE>AuthenticationConfigurationTestHelper</CODE> provides helper methods to construct sample data
 * for use in unit or integration tests.
 */
@Component
public class AuthenticationConfigurationTestHelper
  extends AbstractTestHelper<AuthenticationConfiguration, AuthenticationConfigurationDto> {

  @Override
  public AuthenticationConfiguration constructUnsavedMinimalEntity() {
    return AuthenticationConfiguration.builder()
      .minimumUppercaseCharacters(1)
      .minimumSpecialCharacters(2)
      .minimumPasswordLength(8)
      .minimumNumberCharacters(3)
      .minimumLowercaseCharacters(4)
      .maximumPasswordLength(50)
      .maximumPasswordAgeInDays(90)
      .build();
  }

  @Override
  public AuthenticationConfiguration constructNewEntityWithAllValues() {
    return AuthenticationConfiguration.builder()
      .createdBy(USER_NAME)
      .id(ID.getAndIncrement())
      .lastUpdated(ZonedDateTime.now(ZoneOffset.UTC))
      .minimumUppercaseCharacters(1)
      .minimumSpecialCharacters(2)
      .minimumPasswordLength(8)
      .minimumNumberCharacters(3)
      .minimumLowercaseCharacters(4)
      .maximumPasswordLength(50)
      .maximumPasswordAgeInDays(90)
      .status(VersionStatus.Amend)
      .updatedBy(USER_NAME1)
      .versionNumber(VERSION_NUMBER.getAndIncrement())
      .build();
  }

  @Override
  public AuthenticationConfigurationDto constructUnsavedMinimalDto() {
    return AuthenticationConfigurationDto.builder()
      .minimumUppercaseCharacters(1)
      .minimumSpecialCharacters(2)
      .minimumPasswordLength(8)
      .minimumNumberCharacters(3)
      .minimumLowercaseCharacters(4)
      .maximumPasswordLength(50)
      .maximumPasswordAgeInDays(90)
      .build();
  }

  @Override
  public AuthenticationConfigurationDto constructNewDtoWithAllValues() {
    return AuthenticationConfigurationDto.builder()
      .createdBy(USER_NAME)
      .id(ID.getAndIncrement())
      .lastUpdated(ZonedDateTime.now(ZoneOffset.UTC))
      .minimumUppercaseCharacters(1)
      .minimumSpecialCharacters(2)
      .minimumPasswordLength(8)
      .minimumNumberCharacters(3)
      .minimumLowercaseCharacters(4)
      .maximumPasswordLength(50)
      .maximumPasswordAgeInDays(90)
      .status(VersionStatus.Amend.name())
      .updatedBy(USER_NAME1)
      .versionNumber(VERSION_NUMBER.getAndIncrement())
      .build();
  }

  @Override
  public AuthenticationConfigurationDto constructModifiedMinimalDto(AuthenticationConfigurationDto savedDto) {
    return savedDto;
  }

  @Override
  public List<AuthenticationConfigurationDto> constructListOfUnsavedMinimalDto() {
    AuthenticationConfigurationDto unsaveDto1 = constructUnsavedMinimalDto();
    AuthenticationConfigurationDto unsaveDto2 = constructUnsavedMinimalDto();
    return Arrays.asList(unsaveDto1, unsaveDto2);
  }

}
