package com.apschulewitz.resdb.refdata.model.helper;

import com.apschulewitz.resdb.common.model.entity.VersionStatus;
import com.apschulewitz.resdb.refdata.model.dto.TechnologyTypeGroupDto;
import com.apschulewitz.resdb.refdata.model.entity.TechnologyTypeGroup;
import com.apschulewitz.resdb.research.model.AbstractTestHelper;
import org.springframework.stereotype.Component;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.List;

@Component
public class TechnologyTypeGroupTestHelper extends AbstractTestHelper<TechnologyTypeGroup, TechnologyTypeGroupDto> {

  @Override
  public TechnologyTypeGroup constructUnsavedMinimalEntity() {
    return TechnologyTypeGroup.builder()
      .name(TechnologyTypeGroup.class.getSimpleName())
      .build();
  }

  @Override
  public TechnologyTypeGroup constructNewEntityWithAllValues() {
    return TechnologyTypeGroup.builder()
      .createdBy(USER_NAME)
      .id(ID.getAndIncrement())
      .lastUpdated(ZonedDateTime.now(ZoneOffset.UTC))
      .name(TechnologyTypeGroup.class.getSimpleName())
      .status(VersionStatus.New)
      .updatedBy(USER_NAME1)
      .versionNumber(VERSION_NUMBER.getAndIncrement())
      .build();
  }

  @Override
  public TechnologyTypeGroupDto constructUnsavedMinimalDto() {
    return TechnologyTypeGroupDto.builder()
      .name(TechnologyTypeGroupDto.class.getSimpleName())
      .build();
  }

  @Override
  public TechnologyTypeGroupDto constructNewDtoWithAllValues() {
    return TechnologyTypeGroupDto.builder()
      .createdBy(USER_NAME)
      .id(ID.getAndIncrement())
      .lastUpdated(ZonedDateTime.now(ZoneOffset.UTC))
      .name(TechnologyTypeGroupDto.class.getSimpleName())
      .status(VersionStatus.Amend.name())
      .updatedBy(USER_NAME1)
      .versionNumber(VERSION_NUMBER.getAndIncrement())
      .build();
  }

  @Override
  public List<TechnologyTypeGroupDto> constructListOfUnsavedMinimalDto() {
    return null;
  }
}
