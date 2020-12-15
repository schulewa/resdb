package com.apschulewitz.resdb.refdata.model.mapper;

import com.apschulewitz.resdb.common.model.entity.VersionStatus;
import com.apschulewitz.resdb.common.model.mapper.VersionableEntityDtoMapper;
import com.apschulewitz.resdb.refdata.model.dto.CalendarTypeDto;
import com.apschulewitz.resdb.refdata.model.entity.CalendarType;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
public class CalendarTypeMapper implements VersionableEntityDtoMapper<CalendarType, CalendarTypeDto> {


  @Override
  public CalendarType toEntity(CalendarTypeDto dto) {

    return CalendarType.builder()
      .createdBy(dto.getCreatedBy())
      .id(dto.getId())
      .lastUpdated(dto.getLastUpdated())
      .name(dto.getName())
      .status(VersionStatus.getInstance(dto.getStatus()))
      .build();
  }

  @Override
  public CalendarTypeDto toDto(CalendarType entity) {
    if (entity == null) {
      throw new IllegalArgumentException("Null calenar type cannot be mapped to dto");
    }

    return CalendarTypeDto.builder()
      .createdBy(entity.getCreatedBy())
      .id(entity.getId())
      .lastUpdated(entity.getLastUpdated())
      .name(entity.getName())
      .status(entity.getStatus().name())
      .build();
  }

  @Override
  public CalendarTypeDto toDto(CalendarType entity, boolean onlyActive) {
    if (VersionStatus.getLiveStatuses().contains(entity.getStatus()) || !onlyActive) {
      return toDto(entity);
    }
    return null;
  }

  @Override
  public CalendarType toEntity(CalendarTypeDto dto, boolean onlyActive) {
    return null;
  }

}
