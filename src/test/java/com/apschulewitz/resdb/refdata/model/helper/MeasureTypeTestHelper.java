package com.apschulewitz.resdb.refdata.model.helper;

import com.apschulewitz.resdb.common.model.entity.VersionStatus;
import com.apschulewitz.resdb.refdata.model.dto.MeasureTypeDto;
import com.apschulewitz.resdb.refdata.model.entity.MeasureType;
import com.apschulewitz.resdb.research.model.AbstractTestHelper;
import org.springframework.stereotype.Component;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.List;

@Component
public class MeasureTypeTestHelper extends AbstractTestHelper<MeasureType, MeasureTypeDto> {

  @Override
  public MeasureType constructUnsavedMinimalEntity() {
    return MeasureType.builder()
      .name(MeasureType.class.getSimpleName())
      .build();
  }

  @Override
  public MeasureType constructNewEntityWithAllValues() {
    return MeasureType.builder()
      .createdBy(USER_NAME)
      .id(ID.getAndIncrement())
      .lastUpdated(ZonedDateTime.now(ZoneOffset.UTC))
      .name(MeasureType.class.getSimpleName())
      .status(VersionStatus.New)
      .updatedBy(USER_NAME1)
      .versionNumber(VERSION_NUMBER.getAndIncrement())
      .build();
  }

  @Override
  public MeasureTypeDto constructUnsavedMinimalDto() {
    return MeasureTypeDto.builder()
      .name(MeasureTypeDto.class.getSimpleName())
      .build();
  }

  @Override
  public MeasureTypeDto constructNewDtoWithAllValues() {
    return MeasureTypeDto.builder()
      .createdBy(USER_NAME)
      .id(ID.getAndIncrement())
      .lastUpdated(ZonedDateTime.now(ZoneOffset.UTC))
      .name(MeasureTypeDto.class.getSimpleName())
      .status(VersionStatus.Amend.name())
      .updatedBy(USER_NAME1)
      .versionNumber(VERSION_NUMBER.getAndIncrement())
      .build();
  }

  @Override
  public List<MeasureTypeDto> constructListOfUnsavedMinimalDto() {
    MeasureTypeDto dto1 = constructUnsavedMinimalDto();
    MeasureTypeDto dto2 = constructUnsavedMinimalDto();
    dto2.setName(dto2.getName()+"2");
    return Arrays.asList(dto1, dto2);
  }
}
