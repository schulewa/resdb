package com.apschulewitz.resdb.refdata.model.helper;

import com.apschulewitz.resdb.common.model.entity.VersionStatus;
import com.apschulewitz.resdb.refdata.model.dto.PersonTypeDto;
import com.apschulewitz.resdb.refdata.model.entity.PersonType;
import com.apschulewitz.resdb.research.model.AbstractTestHelper;
import org.springframework.stereotype.Component;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.List;

@Component
public class PersonTypeTestHelper extends AbstractTestHelper<PersonType, PersonTypeDto> {

  @Override
  public PersonType constructUnsavedMinimalEntity() {
    return PersonType.builder()
      .name(PersonType.class.getSimpleName())
      .build();
  }

  @Override
  public PersonType constructNewEntityWithAllValues() {
    return PersonType.builder()
      .createdBy(USER_NAME)
      .id(ID.getAndIncrement())
      .lastUpdated(ZonedDateTime.now(ZoneOffset.UTC))
      .name(PersonType.class.getSimpleName())
      .status(VersionStatus.New)
      .updatedBy(USER_NAME1)
      .versionNumber(VERSION_NUMBER.getAndIncrement())
      .build();
  }

  @Override
  public PersonTypeDto constructUnsavedMinimalDto() {
    return PersonTypeDto.builder()
      .name(PersonTypeDto.class.getSimpleName())
      .build();
  }

  @Override
  public PersonTypeDto constructNewDtoWithAllValues() {
    return PersonTypeDto.builder()
      .createdBy(USER_NAME)
      .id(ID.getAndIncrement())
      .lastUpdated(ZonedDateTime.now(ZoneOffset.UTC))
      .name(PersonTypeDto.class.getSimpleName())
      .status(VersionStatus.Amend.name())
      .updatedBy(USER_NAME1)
      .versionNumber(VERSION_NUMBER.getAndIncrement())
      .build();
  }

  @Override
  public List<PersonTypeDto> constructListOfUnsavedMinimalDto() {
    PersonTypeDto dto1 = constructUnsavedMinimalDto();
    PersonTypeDto dto2 = constructUnsavedMinimalDto();
    dto2.setName(dto2.getName()+"2");
    return Arrays.asList(dto1, dto2);
  }
}
