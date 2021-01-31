package com.apschulewitz.resdb.refdata.model.helper;

import com.apschulewitz.resdb.common.model.entity.VersionStatus;
import com.apschulewitz.resdb.refdata.model.dto.RaceTypeDto;
import com.apschulewitz.resdb.refdata.model.entity.RaceType;
import com.apschulewitz.resdb.research.model.AbstractTestHelper;
import org.springframework.stereotype.Component;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.List;

@Component
public class RaceTypeTestHelper extends AbstractTestHelper<RaceType, RaceTypeDto> {

  @Override
  public RaceType constructUnsavedMinimalEntity() {
    return RaceType.builder()
      .name(RaceType.class.getSimpleName())
      .build();
  }

  @Override
  public RaceType constructNewEntityWithAllValues() {
    return RaceType.builder()
      .createdBy(USER_NAME)
      .id(ID.getAndIncrement())
      .lastUpdated(ZonedDateTime.now(ZoneOffset.UTC))
      .name(RaceType.class.getSimpleName())
      .status(VersionStatus.New)
      .updatedBy(USER_NAME1)
      .versionNumber(VERSION_NUMBER.getAndIncrement())
      .build();
  }

  @Override
  public RaceTypeDto constructUnsavedMinimalDto() {
    return RaceTypeDto.builder()
      .name(RaceTypeDto.class.getSimpleName())
      .build();
  }

  @Override
  public RaceTypeDto constructNewDtoWithAllValues() {
    return RaceTypeDto.builder()
      .createdBy(USER_NAME)
      .id(ID.getAndIncrement())
      .lastUpdated(ZonedDateTime.now(ZoneOffset.UTC))
      .name(RaceTypeDto.class.getSimpleName())
      .status(VersionStatus.Amend.name())
      .updatedBy(USER_NAME1)
      .versionNumber(VERSION_NUMBER.getAndIncrement())
      .build();
  }

  @Override
  public List<RaceTypeDto> constructListOfUnsavedMinimalDto() {
    RaceTypeDto dto1 = constructUnsavedMinimalDto();
    RaceTypeDto dto2 = constructUnsavedMinimalDto();
    dto2.setName(dto2.getName()+"2");
    return Arrays.asList(dto1, dto2);
  }
}
