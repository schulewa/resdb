package com.apschulewitz.resdb.refdata.model.helper;

import com.apschulewitz.resdb.common.model.entity.DataOperation;
import com.apschulewitz.resdb.common.model.entity.VersionStatus;
import com.apschulewitz.resdb.refdata.model.dto.RegionDto;
import com.apschulewitz.resdb.refdata.model.entity.Region;
import com.apschulewitz.resdb.research.model.AbstractTestHelper;
import org.springframework.stereotype.Component;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.List;

@Component
public class RegionTestHelper extends AbstractTestHelper<Region, RegionDto> {

  @Override
  public Region constructUnsavedMinimalEntity() {
    return Region.builder()
      .name(Region.class.getSimpleName())
      .build();
  }

  @Override
  public Region constructNewEntityWithAllValues() {
    return Region.builder()
      .createdBy(USER_NAME)
      .id(ID.getAndIncrement())
      .lastUpdated(ZonedDateTime.now(ZoneOffset.UTC))
      .name(Region.class.getSimpleName())
      .operation(DataOperation.CREATE)
      .status(VersionStatus.Amend)
      .updatedBy(USER_NAME1)
      .versionNumber(VERSION_NUMBER.getAndIncrement())
      .build();
  }

  @Override
  public RegionDto constructUnsavedMinimalDto() {
    return RegionDto.builder()
      .name(RegionDto.class.getSimpleName())
      .build();
  }

  @Override
  public RegionDto constructNewDtoWithAllValues() {
    return RegionDto.builder()
      .createdBy(USER_NAME)
      .id(ID.getAndIncrement())
      .lastUpdated(ZonedDateTime.now(ZoneOffset.UTC))
      .name(RegionDto.class.getSimpleName())
      .status(VersionStatus.Amend.name())
      .updatedBy(USER_NAME1)
      .versionNumber(VERSION_NUMBER.getAndIncrement())
      .build();
  }

  @Override
  public List<RegionDto> constructListOfUnsavedMinimalDto() {
    return null;
  }
}
