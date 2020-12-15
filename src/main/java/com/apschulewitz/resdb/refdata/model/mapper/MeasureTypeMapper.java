package com.apschulewitz.resdb.refdata.model.mapper;

import com.apschulewitz.resdb.common.model.entity.VersionStatus;
import com.apschulewitz.resdb.common.model.mapper.VersionableEntityDtoMapper;
import com.apschulewitz.resdb.refdata.model.dto.MeasureTypeDto;
import com.apschulewitz.resdb.refdata.model.entity.MeasureType;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
public class MeasureTypeMapper implements VersionableEntityDtoMapper<MeasureType, MeasureTypeDto> {


  @Override
  public MeasureType toEntity(MeasureTypeDto dto) {
    return MeasureType.builder()
      .createdBy(dto.getCreatedBy())
      .id(dto.getId())
      .lastUpdated(dto.getLastUpdated())
      .name(dto.getName())
      .status(VersionStatus.getInstance(dto.getStatus()))
      .build();
  }

  @Override
  public MeasureTypeDto toDto(MeasureType entity) {
    if (entity == null) {
      throw new IllegalArgumentException("Null measure type cannot be mapped to dto");
    }

    return MeasureTypeDto.builder()
      .createdBy(entity.getCreatedBy())
      .id(entity.getId())
      .lastUpdated(entity.getLastUpdated())
      .name(entity.getName())
      .status(entity.getStatus().name())
      .build();
  }

  @Override
  public MeasureTypeDto toDto(MeasureType entity, boolean onlyActive) {
    if (VersionStatus.getLiveStatuses().contains(entity.getStatus()) || !onlyActive) {
      return toDto(entity);
    }
    return null;
  }

  @Override
  public MeasureType toEntity(MeasureTypeDto dto, boolean onlyActive) {
    return null;
  }

}
