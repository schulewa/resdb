package com.apschulewitz.resdb.refdata.model.helper;

import com.apschulewitz.resdb.common.model.entity.VersionStatus;
import com.apschulewitz.resdb.refdata.model.dto.TaleTypeDto;
import com.apschulewitz.resdb.refdata.model.entity.TaleType;
import com.apschulewitz.resdb.research.model.AbstractTestHelper;
import org.springframework.stereotype.Component;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.List;

@Component
public class TaleTypeTestHelper extends AbstractTestHelper<TaleType, TaleTypeDto> {

  @Override
  public TaleType constructUnsavedMinimalEntity() {
    return TaleType.builder()
      .name(TaleType.class.getSimpleName())
      .build();
  }

  @Override
  public TaleType constructNewEntityWithAllValues() {
    return TaleType.builder()
      .createdBy(USER_NAME)
      .id(ID.getAndIncrement())
      .lastUpdated(ZonedDateTime.now(ZoneOffset.UTC))
      .name(TaleType.class.getSimpleName())
      .status(VersionStatus.Amend)
      .updatedBy(USER_NAME1)
      .versionNumber(VERSION_NUMBER.getAndIncrement())
      .build();
  }

  @Override
  public TaleTypeDto constructUnsavedMinimalDto() {
    return TaleTypeDto.builder()
      .name(TaleTypeDto.class.getSimpleName())
      .build();
  }

  @Override
  public TaleTypeDto constructNewDtoWithAllValues() {
    return TaleTypeDto.builder()
      .createdBy(USER_NAME)
      .id(ID.getAndIncrement())
      .lastUpdated(ZonedDateTime.now(ZoneOffset.UTC))
      .name(TaleTypeDto.class.getSimpleName())
      .status(VersionStatus.Amend.name())
      .updatedBy(USER_NAME1)
      .versionNumber(VERSION_NUMBER.getAndIncrement())
      .build();
  }

  @Override
  public List<TaleTypeDto> constructListOfUnsavedMinimalDto() {
    return null;
  }
}
