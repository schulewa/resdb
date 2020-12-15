package com.apschulewitz.resdb.refdata.model.mapper;

import com.apschulewitz.resdb.common.model.entity.VersionStatus;
import com.apschulewitz.resdb.common.model.mapper.VersionableEntityDtoMapper;
import com.apschulewitz.resdb.refdata.model.dto.EventTypeGroupDto;
import com.apschulewitz.resdb.refdata.model.entity.EventTypeGroup;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
public class EventTypeGroupMapper implements VersionableEntityDtoMapper<EventTypeGroup, EventTypeGroupDto> {


  @Override
  public EventTypeGroup toEntity(EventTypeGroupDto dto) {

    return EventTypeGroup.builder()
      .createdBy(dto.getCreatedBy())
      .id(dto.getId())
      .lastUpdated(dto.getLastUpdated())
      .name(dto.getName())
      .status(VersionStatus.getInstance(dto.getStatus()))
      .build();
  }

  @Override
  public EventTypeGroupDto toDto(EventTypeGroup entity) {
    if (entity == null) {
      throw new IllegalArgumentException("Null event type group cannot be mapped to dto");
    }

    return EventTypeGroupDto.builder()
      .createdBy(entity.getCreatedBy())
      .id(entity.getId())
      .lastUpdated(entity.getLastUpdated())
      .name(entity.getName())
      .status(entity.getStatus().name())
      .build();
  }

  @Override
  public EventTypeGroupDto toDto(EventTypeGroup entity, boolean onlyActive) {
    if (VersionStatus.getLiveStatuses().contains(entity.getStatus()) || !onlyActive) {
      return toDto(entity);
    }
    return null;
  }

  @Override
  public EventTypeGroup toEntity(EventTypeGroupDto dto, boolean onlyActive) {
    return null;
  }

}
