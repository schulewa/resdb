package com.apschulewitz.resdb.refdata.model;

import com.apschulewitz.resdb.common.model.entity.VersionStatus;
import com.apschulewitz.resdb.refdata.model.dto.AddressTypeDto;
import com.apschulewitz.resdb.refdata.model.dto.LanguageGroupDto;
import com.apschulewitz.resdb.refdata.model.entity.AddressType;
import com.apschulewitz.resdb.research.model.AbstractTestHelper;

public class AddressTypeTestHelper extends AbstractTestHelper {

  public static AddressType constructNewEntityWithAllValues(Long id, String name) {
    return AddressType.builder()
      .createdBy(USER_NAME)
      .id(id)
      .name(name)
      .status(VersionStatus.New)
      .build();
  }

  public static AddressType constructNewEntityWithMandatoryValues(Long id, String name) {
    return AddressType.builder()
      .createdBy(USER_NAME)
      .id(id)
      .name(name)
      .status(VersionStatus.New)
      .build();
  }

  public static AddressTypeDto constructNewDtoWithMandatoryValues(Long id, String name) {
    return AddressTypeDto.builder()
      .createdBy(USER_NAME)
      .id(id)
      .name(name)
      .status(VersionStatus.New.name())
      .build();
  }
}
