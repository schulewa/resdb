package com.apschulewitz.resdb.refdata.model.helper;

import com.apschulewitz.resdb.common.model.entity.VersionStatus;
import com.apschulewitz.resdb.refdata.model.dto.HierarchyTypeDto;
import com.apschulewitz.resdb.refdata.model.entity.HierarchyType;
import com.apschulewitz.resdb.research.model.AbstractTestHelper;
import org.springframework.stereotype.Component;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.List;

@Component
public class HierarchyTypeTestHelper extends AbstractTestHelper<HierarchyType, HierarchyTypeDto> {

  @Override
  public HierarchyType constructUnsavedMinimalEntity() {
    return HierarchyType.builder()
      .name(HierarchyType.class.getSimpleName())
      .build();
  }

  @Override
  public HierarchyType constructNewEntityWithAllValues() {
    return HierarchyType.builder()
      .createdBy(USER_NAME)
      .id(ID.getAndIncrement())
      .lastUpdated(ZonedDateTime.now(ZoneOffset.UTC))
      .name(HierarchyType.class.getSimpleName())
      .status(VersionStatus.New)
      .updatedBy(USER_NAME1)
      .versionNumber(VERSION_NUMBER.getAndIncrement())
      .build();
  }

  @Override
  public HierarchyTypeDto constructUnsavedMinimalDto() {
    return HierarchyTypeDto.builder()
      .name(HierarchyTypeDto.class.getSimpleName())
      .build();
  }

  @Override
  public HierarchyTypeDto constructNewDtoWithAllValues() {
    return HierarchyTypeDto.builder()
      .createdBy(USER_NAME)
      .id(ID.getAndIncrement())
      .lastUpdated(ZonedDateTime.now(ZoneOffset.UTC))
      .name(HierarchyTypeDto.class.getSimpleName())
      .status(VersionStatus.Amend.name())
      .updatedBy(USER_NAME1)
      .versionNumber(VERSION_NUMBER.getAndIncrement())
      .build();
  }

  @Override
  public List<HierarchyTypeDto> constructListOfUnsavedMinimalDto() {
    HierarchyTypeDto dto1 = constructUnsavedMinimalDto();
    HierarchyTypeDto dto2 = constructUnsavedMinimalDto();
    return Arrays.asList(dto1, dto2);
  }
}
