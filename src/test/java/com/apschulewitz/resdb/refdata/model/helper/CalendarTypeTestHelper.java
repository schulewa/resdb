package com.apschulewitz.resdb.refdata.model.helper;

import com.apschulewitz.resdb.common.model.entity.VersionStatus;
import com.apschulewitz.resdb.refdata.model.dto.CalendarTypeDto;
import com.apschulewitz.resdb.refdata.model.entity.CalendarType;
import com.apschulewitz.resdb.research.model.AbstractTestHelper;
import org.springframework.stereotype.Component;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.List;

@Component
public class CalendarTypeTestHelper extends AbstractTestHelper<CalendarType, CalendarTypeDto> {

  @Override
  public CalendarType constructUnsavedMinimalEntity() {
    return CalendarType.builder()
      .name(CalendarType.class.getSimpleName())
      .build();
  }

  @Override
  public CalendarType constructNewEntityWithAllValues() {
    return CalendarType.builder()
      .createdBy(USER_NAME)
      .id(ID.getAndIncrement())
      .lastUpdated(ZonedDateTime.now(ZoneOffset.UTC))
      .name(CalendarType.class.getSimpleName())
      .status(VersionStatus.Amend)
      .updatedBy(USER_NAME1)
      .versionNumber(VERSION_NUMBER.getAndIncrement())
      .build();
  }

  @Override
  public CalendarTypeDto constructUnsavedMinimalDto() {
    return CalendarTypeDto.builder()
      .name(CalendarTypeDto.class.getSimpleName())
      .build();
  }

  @Override
  public CalendarTypeDto constructNewDtoWithAllValues() {
    return CalendarTypeDto.builder()
      .createdBy(USER_NAME)
      .id(ID.getAndIncrement())
      .lastUpdated(ZonedDateTime.now(ZoneOffset.UTC))
      .name(CalendarTypeDto.class.getSimpleName())
      .status(VersionStatus.Amend.name())
      .updatedBy(USER_NAME1)
      .versionNumber(VERSION_NUMBER.getAndIncrement())
      .build();
  }

  @Override
  public List<CalendarTypeDto> constructListOfUnsavedMinimalDto() {
    CalendarTypeDto unsavedDto1 = constructUnsavedMinimalDto();
    CalendarTypeDto unsavedDto2 = constructUnsavedMinimalDto();
    unsavedDto2.setName(unsavedDto2.getName()+"2");
    return Arrays.asList(unsavedDto1, unsavedDto2);
  }
}
