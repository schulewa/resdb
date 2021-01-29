package com.apschulewitz.resdb.refdata.model.helper;

import com.apschulewitz.resdb.common.model.entity.VersionStatus;
import com.apschulewitz.resdb.refdata.model.dto.LanguageGroupDto;
import com.apschulewitz.resdb.refdata.model.entity.LanguageGroup;
import com.apschulewitz.resdb.research.model.AbstractTestHelper;
import org.springframework.stereotype.Component;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.List;

@Component
public class LanguageGroupTestHelper extends AbstractTestHelper<LanguageGroup, LanguageGroupDto> {

  @Override
  public LanguageGroup constructUnsavedMinimalEntity() {
    return LanguageGroup.builder()
      .name(LanguageGroup.class.getSimpleName())
      .build();
  }

  @Override
  public LanguageGroup constructNewEntityWithAllValues() {
    return LanguageGroup.builder()
      .createdBy(USER_NAME)
      .id(ID.getAndIncrement())
      .lastUpdated(ZonedDateTime.now(ZoneOffset.UTC))
      .name(LanguageGroup.class.getSimpleName())
      .status(VersionStatus.Amend)
      .updatedBy(USER_NAME1)
      .versionNumber(VERSION_NUMBER.getAndIncrement())
      .build();
  }

  @Override
  public LanguageGroupDto constructUnsavedMinimalDto() {
    return LanguageGroupDto.builder()
      .name(LanguageGroupDto.class.getSimpleName())
      .build();
  }

  @Override
  public LanguageGroupDto constructNewDtoWithAllValues() {
    return LanguageGroupDto.builder()
      .createdBy(USER_NAME)
      .id(ID.getAndIncrement())
      .lastUpdated(ZonedDateTime.now(ZoneOffset.UTC))
      .name(LanguageGroupDto.class.getSimpleName())
      .status(VersionStatus.Amend.name())
      .updatedBy(USER_NAME1)
      .versionNumber(VERSION_NUMBER.getAndIncrement())
      .build();
  }

  @Override
  public List<LanguageGroupDto> constructListOfUnsavedMinimalDto() {
    LanguageGroupDto dto1 = constructUnsavedMinimalDto();
    LanguageGroupDto dto2 = constructUnsavedMinimalDto();
    dto2.setName(dto2.getName()+"2");
    return Arrays.asList(dto1, dto2);
  }
}
