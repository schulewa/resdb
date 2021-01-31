package com.apschulewitz.resdb.refdata.model.helper;

import com.apschulewitz.resdb.common.model.entity.VersionStatus;
import com.apschulewitz.resdb.refdata.model.dto.AddressTypeDto;
import com.apschulewitz.resdb.refdata.model.entity.AddressType;
import com.apschulewitz.resdb.research.model.AbstractTestHelper;
import org.springframework.stereotype.Component;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.List;

/**
 * <CODE>AddressTypeTestHelper</CODE> provides helper methods to construct sample data
 * for use in unit or integration tests.
 */
@Component
public class AddressTypeTestHelper extends AbstractTestHelper<AddressType, AddressTypeDto> {

  @Override
  public AddressType constructUnsavedMinimalEntity() {
    return AddressType.builder()
      .name(AddressType.class.getSimpleName())
      .build();
  }

  @Override
  public AddressType constructNewEntityWithAllValues() {
    return AddressType.builder()
      .createdBy(USER_NAME)
      .id(ID.getAndIncrement())
      .lastUpdated(ZonedDateTime.now(ZoneOffset.UTC))
      .name(AddressType.class.getSimpleName())
      .status(VersionStatus.Amend)
      .updatedBy(USER_NAME1)
      .versionNumber(VERSION_NUMBER.getAndIncrement())
      .build();
  }

  @Override
  public AddressTypeDto constructUnsavedMinimalDto() {
    return AddressTypeDto.builder()
      .name(AddressTypeDto.class.getSimpleName())
      .build();
  }

  @Override
  public AddressTypeDto constructNewDtoWithAllValues() {
    return AddressTypeDto.builder()
      .createdBy(USER_NAME)
      .id(ID.getAndIncrement())
      .lastUpdated(ZonedDateTime.now(ZoneOffset.UTC))
      .name(AddressTypeDto.class.getSimpleName())
      .status(VersionStatus.Amend.name())
      .updatedBy(USER_NAME1)
      .versionNumber(VERSION_NUMBER.getAndIncrement())
      .build();
  }

  @Override
  public AddressTypeDto constructModifiedMinimalDto(AddressTypeDto savedDto) {
    savedDto.setName(savedDto.getName()+"2");
    return savedDto;
  }

  @Override
  public List<AddressTypeDto> constructListOfUnsavedMinimalDto() {
    AddressTypeDto unsaveDto1 = constructUnsavedMinimalDto();
    AddressTypeDto unsaveDto2 = constructUnsavedMinimalDto();
    unsaveDto2.setName(AddressTypeDto.class.getSimpleName()+"2");
    return Arrays.asList(unsaveDto1, unsaveDto2);
  }

}
