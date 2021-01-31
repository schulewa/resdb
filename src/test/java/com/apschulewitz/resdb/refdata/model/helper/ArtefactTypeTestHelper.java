package com.apschulewitz.resdb.refdata.model.helper;

import com.apschulewitz.resdb.common.model.entity.VersionStatus;
import com.apschulewitz.resdb.refdata.model.dto.ArtefactTypeDto;
import com.apschulewitz.resdb.refdata.model.entity.ArtefactType;
import com.apschulewitz.resdb.research.model.AbstractTestHelper;
import org.springframework.stereotype.Component;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.List;

@Component
public class ArtefactTypeTestHelper extends AbstractTestHelper<ArtefactType, ArtefactTypeDto> {

  @Override
  public ArtefactType constructUnsavedMinimalEntity() {
    return ArtefactType.builder()
      .name(ArtefactType.class.getSimpleName())
      .build();
  }

  @Override
  public ArtefactType constructNewEntityWithAllValues() {
    return ArtefactType.builder()
      .createdBy(USER_NAME)
      .id(ID.getAndIncrement())
      .lastUpdated(ZonedDateTime.now(ZoneOffset.UTC))
      .name(ArtefactType.class.getSimpleName())
      .status(VersionStatus.Amend)
      .updatedBy(USER_NAME1)
      .versionNumber(VERSION_NUMBER.getAndIncrement())
      .build();
  }

  @Override
  public ArtefactTypeDto constructUnsavedMinimalDto() {
    return ArtefactTypeDto.builder()
      .name(ArtefactType.class.getSimpleName())
      .build();
  }

  @Override
  public ArtefactTypeDto constructNewDtoWithAllValues() {
    return ArtefactTypeDto.builder()
      .createdBy(USER_NAME)
      .id(ID.getAndIncrement())
      .lastUpdated(ZonedDateTime.now(ZoneOffset.UTC))
      .name(ArtefactType.class.getSimpleName())
      .status(VersionStatus.Amend.name())
      .updatedBy(USER_NAME1)
      .versionNumber(VERSION_NUMBER.getAndIncrement())
      .build();
  }

  @Override
  public ArtefactTypeDto constructModifiedMinimalDto(ArtefactTypeDto savedDto) {
    savedDto.setName(savedDto.getName()+"2");
    return savedDto;
  }

  @Override
  public List<ArtefactTypeDto> constructListOfUnsavedMinimalDto() {
    ArtefactTypeDto unsaveDto1 = constructUnsavedMinimalDto();
    ArtefactTypeDto unsaveDto2 = constructUnsavedMinimalDto();
    unsaveDto2.setName(unsaveDto2.getName()+"2");
    return Arrays.asList(unsaveDto1, unsaveDto2);
  }
}
