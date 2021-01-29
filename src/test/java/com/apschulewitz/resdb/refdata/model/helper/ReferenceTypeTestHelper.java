package com.apschulewitz.resdb.refdata.model.helper;

import com.apschulewitz.resdb.common.model.entity.VersionStatus;
import com.apschulewitz.resdb.refdata.model.dto.ReferenceTypeDto;
import com.apschulewitz.resdb.refdata.model.entity.ReferenceType;
import com.apschulewitz.resdb.research.model.AbstractTestHelper;
import org.springframework.stereotype.Component;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.List;

@Component
public class ReferenceTypeTestHelper extends AbstractTestHelper<ReferenceType, ReferenceTypeDto> {

  @Override
  public ReferenceType constructUnsavedMinimalEntity() {
    return null;
  }

  @Override
  public ReferenceType constructNewEntityWithAllValues() {
    return ReferenceType.builder()
      .createdBy(USER_NAME)
      .id(ID.getAndIncrement())
      .lastUpdated(ZonedDateTime.now(ZoneOffset.UTC))
      .name(ReferenceType.class.getSimpleName())
      .status(VersionStatus.Amend)
      .updatedBy(USER_NAME1)
      .versionNumber(VERSION_NUMBER.getAndIncrement())
      .build();
  }

  @Override
  public ReferenceTypeDto constructUnsavedMinimalDto() {
    return ReferenceTypeDto.builder()
      .name(ReferenceTypeDto.class.getSimpleName())
      .build();
  }

  @Override
  public ReferenceTypeDto constructNewDtoWithAllValues() {
    return ReferenceTypeDto.builder()
      .createdBy(USER_NAME)
      .id(ID.getAndIncrement())
      .lastUpdated(ZonedDateTime.now(ZoneOffset.UTC))
      .name(ReferenceTypeDto.class.getSimpleName())
      .status(VersionStatus.Amend.name())
      .updatedBy(USER_NAME1)
      .versionNumber(VERSION_NUMBER.getAndIncrement())
      .build();
  }

  @Override
  public List<ReferenceTypeDto> constructListOfUnsavedMinimalDto() {
    return null;
  }
}
