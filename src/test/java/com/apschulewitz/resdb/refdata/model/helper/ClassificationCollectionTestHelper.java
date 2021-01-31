package com.apschulewitz.resdb.refdata.model.helper;

import com.apschulewitz.resdb.common.model.entity.VersionStatus;
import com.apschulewitz.resdb.refdata.model.dto.PublicationTypeDto;
import com.apschulewitz.resdb.refdata.model.entity.PublicationType;
import com.apschulewitz.resdb.research.model.AbstractTestHelper;
import com.apschulewitz.resdb.research.model.dto.ClassificationCollectionDto;
import com.apschulewitz.resdb.research.model.entity.ClassificationCollection;
import org.springframework.stereotype.Component;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.List;

@Component
public class ClassificationCollectionTestHelper extends AbstractTestHelper<ClassificationCollection, ClassificationCollectionDto> {

  @Override
  public ClassificationCollection constructUnsavedMinimalEntity() {
    return ClassificationCollection.builder()
      .name(ClassificationCollection.class.getSimpleName())
      .build();
  }

  @Override
  public ClassificationCollection constructNewEntityWithAllValues() {
    return ClassificationCollection.builder()
      .createdBy(USER_NAME)
      .id(ID.getAndIncrement())
      .lastUpdated(ZonedDateTime.now(ZoneOffset.UTC))
      .name(PublicationType.class.getSimpleName())
      .status(VersionStatus.New)
      .updatedBy(USER_NAME1)
      .versionNumber(VERSION_NUMBER.getAndIncrement())
      .build();
  }

  @Override
  public ClassificationCollectionDto constructUnsavedMinimalDto() {
//    return ClassificationCollectionDto.builder()
//      .name(ClassificationCollectionDto.class.getSimpleName())
//      .build();
    ClassificationCollectionDto dto = new ClassificationCollectionDto();
    dto.setName(ClassificationCollectionDto.class.getSimpleName());
    return dto;
  }

  @Override
  public ClassificationCollectionDto constructNewDtoWithAllValues() {
//    return ClassificationCollectionDto.builder()
//      .createdBy(USER_NAME)
//      .id(ID.getAndIncrement())
//      .lastUpdated(ZonedDateTime.now(ZoneOffset.UTC))
//      .name(ClassificationCollectionDto.class.getSimpleName())
//      .status(VersionStatus.Amend.name())
//      .updatedBy(USER_NAME1)
//      .versionNumber(VERSION_NUMBER.getAndIncrement())
//      .build();
    ClassificationCollectionDto dto = new ClassificationCollectionDto();
    dto.setCreatedBy(USER_NAME);
    dto.setId(ID.getAndIncrement());
    dto.setLastUpdated(ZonedDateTime.now(ZoneOffset.UTC));
    dto.setName(ClassificationCollectionDto.class.getSimpleName());
    dto.setStatus(VersionStatus.Amend.name());
    dto.setUpdatedBy(USER_NAME1);
    dto.setVersionNumber(VERSION_NUMBER.getAndIncrement());
    return dto;
  }

  @Override
  public ClassificationCollectionDto constructModifiedMinimalDto(ClassificationCollectionDto saved) {
    ClassificationCollectionDto dto1 = constructUnsavedMinimalDto();
    dto1.setId(saved.getId());
    dto1.setCreatedBy(saved.getCreatedBy());
    dto1.setStatus(saved.getStatus());
    dto1.setName(saved.getName()+"2");
    dto1.setVersionNumber(saved.getVersionNumber());
    return dto1;
  }

  @Override
  public List<ClassificationCollectionDto> constructListOfUnsavedMinimalDto() {
    ClassificationCollectionDto dto1 = constructUnsavedMinimalDto();
    ClassificationCollectionDto dto2 = constructUnsavedMinimalDto();
    dto2.setName(dto2.getName()+"2");
    return Arrays.asList(dto1, dto2);
  }

}
