package com.apschulewitz.resdb.refdata.model;

import com.apschulewitz.resdb.common.model.entity.DataOperation;
import com.apschulewitz.resdb.common.model.entity.VersionStatus;
import com.apschulewitz.resdb.refdata.model.dto.RegionDto;
import com.apschulewitz.resdb.refdata.model.entity.Region;
import com.apschulewitz.resdb.research.model.AbstractTestHelper;
import org.springframework.stereotype.Component;

import java.time.ZonedDateTime;

@Component
public class RegionTestHelper extends AbstractTestHelper {

  public static Region constructNewRegionWithAllValues(Long id, String name) {
    return Region.builder()
      .createdBy(USER_NAME)
      .id(id)
      .lastUpdated(ZonedDateTime.now())
      .name(name)
      .operation(DataOperation.CREATE)
      .status(VersionStatus.New)
      .updatedBy(USER_NAME + "2")
      .build();
  }

  public static Region constructNewRegionWithMandatoryValues(Long id, String name) {
    return Region.builder()
      .createdBy(USER_NAME)
      .id(id)
      .name(name)
      .operation(DataOperation.CREATE)
      .status(VersionStatus.New)
      .build();
  }

  public static RegionDto constructNewRegionDtoWithAllValues(Long id, String name) {
    return RegionDto.builder()
      .createdBy(USER_NAME)
      .id(id)
      .lastUpdated(ZonedDateTime.now())
      .name(name)
      .status(VersionStatus.New.name())
      .updatedBy(USER_NAME + "2")
      .build();
  }

  public static RegionDto constructNewRegionDtoWithMandatoryValues(Long id, String name) {
    return RegionDto.builder()
      .createdBy(USER_NAME)
      .id(id)
      .name(name)
      .status(VersionStatus.New.name())
      .build();
  }
}
