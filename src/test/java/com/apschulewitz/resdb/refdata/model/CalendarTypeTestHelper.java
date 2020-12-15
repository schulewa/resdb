package com.apschulewitz.resdb.refdata.model;

import com.apschulewitz.resdb.common.model.entity.VersionStatus;
import com.apschulewitz.resdb.refdata.model.dto.CalendarTypeDto;
import com.apschulewitz.resdb.refdata.model.entity.CalendarType;
import com.apschulewitz.resdb.research.model.AbstractTestHelper;

public class CalendarTypeTestHelper extends AbstractTestHelper {

  public static CalendarType constructNewEntityWithAllValues(Long id, String name) {
    return CalendarType.builder()
      .createdBy(USER_NAME)
      .id(id)
      .name(name)
      .status(VersionStatus.New)
      .build();
  }

  public static CalendarType constructNewEntityWithMandatoryValues(Long id, String name) {
    return CalendarType.builder()
      .createdBy(USER_NAME)
      .id(id)
      .name(name)
      .status(VersionStatus.New)
      .build();
  }

  public static CalendarTypeDto constructNewDtoWithMandatoryValues(Long id, String name) {
    return CalendarTypeDto.builder()
      .createdBy(USER_NAME)
      .id(id)
      .name(name)
      .status(VersionStatus.New.name())
      .build();
  }
}
