package com.apschulewitz.resdb.refdata.model.helper;

import com.apschulewitz.resdb.common.model.entity.VersionStatus;
import com.apschulewitz.resdb.refdata.model.dto.AddressTypeDto;
import com.apschulewitz.resdb.refdata.model.dto.ArtefactGroupDto;
import com.apschulewitz.resdb.refdata.model.entity.ArtefactGroup;
import com.apschulewitz.resdb.research.model.AbstractTestHelper;
import org.springframework.stereotype.Component;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.List;

@Component
public class ArtefactGroupTestHelper extends AbstractTestHelper<ArtefactGroup, ArtefactGroupDto> {

  @Override
  public ArtefactGroup constructUnsavedMinimalEntity() {
    return ArtefactGroup.builder()
      .name(ArtefactGroup.class.getSimpleName())
      .build();
  }

  @Override
  public ArtefactGroup constructNewEntityWithAllValues() {
    return ArtefactGroup.builder()
      .createdBy(USER_NAME)
      .id(ID.getAndIncrement())
      .lastUpdated(ZonedDateTime.now(ZoneOffset.UTC))
      .name(ArtefactGroup.class.getSimpleName())
      .status(VersionStatus.New)
      .updatedBy(USER_NAME1)
      .versionNumber(VERSION_NUMBER.getAndIncrement())
      .build();
  }

  @Override
  public ArtefactGroupDto constructUnsavedMinimalDto() {
    return ArtefactGroupDto.builder()
      .name(ArtefactGroupDto.class.getSimpleName())
      .build();
  }

  @Override
  public ArtefactGroupDto constructNewDtoWithAllValues() {
    return ArtefactGroupDto.builder()
      .createdBy(USER_NAME)
      .id(ID.getAndIncrement())
      .lastUpdated(ZonedDateTime.now(ZoneOffset.UTC))
      .name(ArtefactGroupDto.class.getSimpleName())
      .status(VersionStatus.New.name())
      .versionNumber(VERSION_NUMBER.getAndIncrement())
      .build();
  }

  @Override
  public ArtefactGroupDto constructModifiedMinimalDto(ArtefactGroupDto savedDto) {
    savedDto.setName(savedDto.getName()+"2");
    return savedDto;
  }

  @Override
  public List<ArtefactGroupDto> constructListOfUnsavedMinimalDto() {
    ArtefactGroupDto unsaveDto1 = constructUnsavedMinimalDto();
    ArtefactGroupDto unsaveDto2 = constructUnsavedMinimalDto();
    unsaveDto2.setName(unsaveDto2.getName()+"2");
    return Arrays.asList(unsaveDto1, unsaveDto2);
  }
}
