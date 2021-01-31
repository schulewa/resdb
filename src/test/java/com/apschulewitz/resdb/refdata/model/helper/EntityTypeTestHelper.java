package com.apschulewitz.resdb.refdata.model.helper;

import com.apschulewitz.resdb.common.model.entity.VersionStatus;
import com.apschulewitz.resdb.refdata.model.dto.EntityTypeDto;
import com.apschulewitz.resdb.refdata.model.entity.EntityType;
import com.apschulewitz.resdb.research.model.AbstractTestHelper;
import org.springframework.stereotype.Component;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.List;

@Component
public class EntityTypeTestHelper extends AbstractTestHelper<EntityType, EntityTypeDto> {

  @Override
  public EntityType constructUnsavedMinimalEntity() {
    return EntityType.builder()
      .name(getClass().getSimpleName())
      .build();
  }

  @Override
  public EntityType constructNewEntityWithAllValues() {
    return EntityType.builder()
      .createdBy(USER_NAME)
      .id(ID.getAndIncrement())
      .lastUpdated(ZonedDateTime.now(ZoneOffset.UTC))
      .name(getClass().getSimpleName())
      .status(VersionStatus.New)
      .updatedBy(USER_NAME1)
      .versionNumber(VERSION_NUMBER.getAndIncrement())
      .build();
  }

  @Override
  public EntityTypeDto constructUnsavedMinimalDto() {
    return EntityTypeDto.builder()
      .name(getClass().getSimpleName())
      .build();
  }

  @Override
  public EntityTypeDto constructNewDtoWithAllValues() {
    return EntityTypeDto.builder()
      .createdBy(USER_NAME)
      .id(ID.getAndIncrement())
      .lastUpdated(ZonedDateTime.now(ZoneOffset.UTC))
      .name(getClass().getSimpleName())
      .status(VersionStatus.New.name())
      .updatedBy(USER_NAME1)
      .versionNumber(VERSION_NUMBER.getAndIncrement())
      .build();
  }

  @Override
  public List<EntityTypeDto> constructListOfUnsavedMinimalDto() {
    EntityTypeDto dto1 = constructUnsavedMinimalDto();
    EntityTypeDto dto2 = constructUnsavedMinimalDto();
    dto2.setName(dto2.getName()+"2");
    return Arrays.asList(dto1, dto2);
  }
}
