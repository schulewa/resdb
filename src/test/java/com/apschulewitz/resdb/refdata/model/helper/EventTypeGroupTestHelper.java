package com.apschulewitz.resdb.refdata.model.helper;

import com.apschulewitz.resdb.common.model.entity.VersionStatus;
import com.apschulewitz.resdb.refdata.model.dto.EventTypeGroupDto;
import com.apschulewitz.resdb.refdata.model.entity.EventTypeGroup;
import com.apschulewitz.resdb.research.model.AbstractTestHelper;
import org.springframework.stereotype.Component;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.List;

@Component
public class EventTypeGroupTestHelper extends AbstractTestHelper<EventTypeGroup, EventTypeGroupDto> {

  @Override
  public EventTypeGroup constructUnsavedMinimalEntity() {
    return EventTypeGroup.builder()
      .name(EventTypeGroup.class.getSimpleName())
      .build();
  }

  @Override
  public EventTypeGroup constructNewEntityWithAllValues() {
    return EventTypeGroup.builder()
      .createdBy(USER_NAME)
      .id(ID.getAndIncrement())
      .lastUpdated(ZonedDateTime.now(ZoneOffset.UTC))
      .name(EventTypeGroup.class.getSimpleName())
      .status(VersionStatus.Amend)
      .updatedBy(USER_NAME1)
      .versionNumber(VERSION_NUMBER.getAndIncrement())
      .build();
  }

  @Override
  public EventTypeGroupDto constructUnsavedMinimalDto() {
    return EventTypeGroupDto.builder()
      .name(EventTypeGroupDto.class.getSimpleName())
      .build();
  }

  @Override
  public EventTypeGroupDto constructNewDtoWithAllValues() {
    return EventTypeGroupDto.builder()
      .createdBy(USER_NAME)
      .id(ID.getAndIncrement())
      .lastUpdated(ZonedDateTime.now(ZoneOffset.UTC))
      .name(EventTypeGroupDto.class.getSimpleName())
      .status(VersionStatus.Amend.name())
      .updatedBy(USER_NAME1)
      .versionNumber(VERSION_NUMBER.getAndIncrement())
      .build();
  }

  @Override
  public List<EventTypeGroupDto> constructListOfUnsavedMinimalDto() {
    EventTypeGroupDto dto1 = constructUnsavedMinimalDto();
    EventTypeGroupDto dto2 = constructUnsavedMinimalDto();
    dto2.setName(dto2.getName()+"2");
    return Arrays.asList(dto1, dto2);
  }
}
