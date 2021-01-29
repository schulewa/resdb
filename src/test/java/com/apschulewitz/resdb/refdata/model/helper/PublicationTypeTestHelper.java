package com.apschulewitz.resdb.refdata.model.helper;

import com.apschulewitz.resdb.common.model.entity.VersionStatus;
import com.apschulewitz.resdb.refdata.model.dto.PublicationTypeDto;
import com.apschulewitz.resdb.refdata.model.entity.PublicationType;
import com.apschulewitz.resdb.research.model.AbstractTestHelper;
import org.springframework.stereotype.Component;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.List;

@Component
public class PublicationTypeTestHelper extends AbstractTestHelper<PublicationType, PublicationTypeDto> {

  @Override
  public PublicationType constructUnsavedMinimalEntity() {
    return PublicationType.builder()
      .name(PublicationType.class.getSimpleName())
      .build();
  }

  @Override
  public PublicationType constructNewEntityWithAllValues() {
    return PublicationType.builder()
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
  public PublicationTypeDto constructUnsavedMinimalDto() {
    return PublicationTypeDto.builder()
      .name(PublicationTypeDto.class.getSimpleName())
      .build();
  }

  @Override
  public PublicationTypeDto constructNewDtoWithAllValues() {
    return PublicationTypeDto.builder()
      .createdBy(USER_NAME)
      .id(ID.getAndIncrement())
      .lastUpdated(ZonedDateTime.now(ZoneOffset.UTC))
      .name(PublicationTypeDto.class.getSimpleName())
      .status(VersionStatus.Amend.name())
      .updatedBy(USER_NAME1)
      .versionNumber(VERSION_NUMBER.getAndIncrement())
      .build();
  }

  @Override
  public PublicationTypeDto constructModifiedMinimalDto(PublicationTypeDto saved) {
    PublicationTypeDto dto1 = constructUnsavedMinimalDto();
    dto1.setId(saved.getId());
    dto1.setCreatedBy(saved.getCreatedBy());
    dto1.setStatus(saved.getStatus());
    dto1.setName(saved.getName()+"2");
    dto1.setVersionNumber(saved.getVersionNumber());
    return dto1;
  }

  @Override
  public List<PublicationTypeDto> constructListOfUnsavedMinimalDto() {
    PublicationTypeDto dto1 = constructUnsavedMinimalDto();
    PublicationTypeDto dto2 = constructUnsavedMinimalDto();
    dto2.setName(dto2.getName()+"2");
    return Arrays.asList(dto1, dto2);
  }

}
