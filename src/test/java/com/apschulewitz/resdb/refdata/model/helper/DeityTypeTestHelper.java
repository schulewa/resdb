package com.apschulewitz.resdb.refdata.model.helper;

import com.apschulewitz.resdb.common.model.entity.VersionStatus;
import com.apschulewitz.resdb.refdata.model.dto.DeityTypeDto;
import com.apschulewitz.resdb.refdata.model.entity.DeityType;
import com.apschulewitz.resdb.research.model.AbstractTestHelper;
import org.springframework.stereotype.Component;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.List;

@Component
public class DeityTypeTestHelper extends AbstractTestHelper<DeityType, DeityTypeDto> {

  @Override
  public DeityType constructUnsavedMinimalEntity() {
    return DeityType.builder()
      .name(DeityType.class.getSimpleName())
      .build();
  }

  @Override
  public DeityType constructNewEntityWithAllValues() {
    return DeityType.builder()
      .createdBy(USER_NAME)
      .id(ID.getAndIncrement())
      .lastUpdated(ZonedDateTime.now(ZoneOffset.UTC))
      .name(DeityType.class.getSimpleName())
      .status(VersionStatus.New)
      .updatedBy(USER_NAME1)
      .versionNumber(VERSION_NUMBER.getAndIncrement())
      .build();
  }

  @Override
  public DeityTypeDto constructUnsavedMinimalDto() {
    return DeityTypeDto.builder()
      .name(DeityTypeDto.class.getSimpleName())
      .build();
  }

  @Override
  public DeityTypeDto constructNewDtoWithAllValues() {
    return DeityTypeDto.builder()
      .createdBy(USER_NAME)
      .id(ID.getAndIncrement())
      .lastUpdated(ZonedDateTime.now(ZoneOffset.UTC))
      .name(DeityTypeDto.class.getSimpleName())
      .status(VersionStatus.Amend.name())
      .updatedBy(USER_NAME1)
      .versionNumber(VERSION_NUMBER.getAndIncrement())
      .build();
  }

  @Override
  public List<DeityTypeDto> constructListOfUnsavedMinimalDto() {
    DeityTypeDto dto1 = constructUnsavedMinimalDto();
    DeityTypeDto dto2 = constructUnsavedMinimalDto();
    dto2.setName(dto2.getName()+"2");
    return Arrays.asList(dto1, dto2);
  }
}
