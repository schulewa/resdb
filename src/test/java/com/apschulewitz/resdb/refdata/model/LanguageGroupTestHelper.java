package com.apschulewitz.resdb.refdata.model;

import com.apschulewitz.resdb.common.model.entity.DataOperation;
import com.apschulewitz.resdb.common.model.entity.VersionStatus;
import com.apschulewitz.resdb.refdata.model.dto.LanguageGroupDto;
import com.apschulewitz.resdb.refdata.model.entity.LanguageGroup;
import com.apschulewitz.resdb.research.model.AbstractTestHelper;

import java.time.ZonedDateTime;

public class LanguageGroupTestHelper extends AbstractTestHelper {

  public static LanguageGroup constructNewLanguageGroupWithAllValues(Long id, String name) {
    return LanguageGroup.builder()
      .region(RegionTestHelper.constructNewRegionWithAllValues(1L, "Region 1"))
      .id(id)
      .name(name)
      .createdBy(USER_NAME)
      .status(VersionStatus.New)
      .build();
  }

  public static LanguageGroup constructNewLanguageGroupWithMandatoryValues(Long id, String name) {
    return LanguageGroup.builder()
      .createdBy(USER_NAME)
      .id(id)
      .name(name)
      .operation(DataOperation.CREATE)
      .status(VersionStatus.New)
      .build();
  }

  public static LanguageGroupDto constructNewLanguageGroupDtoWithMandatoryValues(Long id, String name) {
    return LanguageGroupDto.builder()
      .createdBy(USER_NAME)
      .id(id)
      .name(name)
      .status(VersionStatus.New.name())
      .build();
  }
}
