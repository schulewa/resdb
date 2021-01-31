package com.apschulewitz.resdb.refdata.model.helper;

import com.apschulewitz.resdb.common.model.entity.VersionStatus;
import com.apschulewitz.resdb.refdata.model.dto.EventTypeDto;
import com.apschulewitz.resdb.refdata.model.entity.EventType;
import com.apschulewitz.resdb.research.model.AbstractTestHelper;
import org.springframework.stereotype.Component;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.List;

@Component
public class EventTypeTestHelper extends AbstractTestHelper<EventType, EventTypeDto> {

  @Override
  public EventType constructUnsavedMinimalEntity() {
    return EventType.builder()
      .name(EventType.class.getSimpleName())
      .build();
  }

  @Override
  public EventType constructNewEntityWithAllValues() {
    return EventType.builder()
      .createdBy(USER_NAME)
      .id(ID.getAndIncrement())
      .lastUpdated(ZonedDateTime.now(ZoneOffset.UTC))
      .name(EventType.class.getSimpleName())
      .status(VersionStatus.New)
      .updatedBy(USER_NAME1)
      .versionNumber(VERSION_NUMBER.getAndIncrement())
      .build();
  }

  @Override
  public EventTypeDto constructUnsavedMinimalDto() {
    return EventTypeDto.builder()
      .name(EventTypeDto.class.getSimpleName())
      .build();
  }

  @Override
  public EventTypeDto constructNewDtoWithAllValues() {
    return EventTypeDto.builder()
      .createdBy(USER_NAME)
      .id(ID.getAndIncrement())
      .lastUpdated(ZonedDateTime.now(ZoneOffset.UTC))
      .name(EventTypeDto.class.getSimpleName())
      .status(VersionStatus.Amend.name())
      .updatedBy(USER_NAME1)
      .versionNumber(VERSION_NUMBER.getAndIncrement())
      .build();
  }

  @Override
  public List<EventTypeDto> constructListOfUnsavedMinimalDto() {
    EventTypeDto dto1 = constructUnsavedMinimalDto();
    EventTypeDto dto2 = constructUnsavedMinimalDto();
    dto2.setName(dto2.getName()+"2");
    return Arrays.asList(dto1, dto2);
  }
}
