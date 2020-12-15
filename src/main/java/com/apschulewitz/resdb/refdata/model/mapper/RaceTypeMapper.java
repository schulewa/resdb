package com.apschulewitz.resdb.refdata.model.mapper;

import com.apschulewitz.resdb.common.model.entity.VersionStatus;
import com.apschulewitz.resdb.common.model.mapper.VersionableEntityDtoMapper;
import com.apschulewitz.resdb.refdata.model.dto.RaceTypeDto;
import com.apschulewitz.resdb.refdata.model.entity.RaceType;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
public class RaceTypeMapper implements VersionableEntityDtoMapper<RaceType, RaceTypeDto> {

  @Override
  public RaceType toEntity(RaceTypeDto dto) {
    return RaceType.builder()
      .createdBy(dto.getCreatedBy())
      .id(dto.getId())
      .lastUpdated(dto.getLastUpdated())
      .name(dto.getName())
      .status(VersionStatus.getInstance(dto.getStatus()))
      .build();
  }

  @Override
  public RaceTypeDto toDto(RaceType entity) {
    if (entity == null) {
      throw new IllegalArgumentException("Null race type cannot be mapped to dto");
    }

    return RaceTypeDto.builder()
      .createdBy(entity.getCreatedBy())
      .id(entity.getId())
      .lastUpdated(entity.getLastUpdated())
      .name(entity.getName())
      .status(entity.getStatus().name())
      .build();
  }

  @Override
  public RaceTypeDto toDto(RaceType entity, boolean onlyActive) {
    if (VersionStatus.getLiveStatuses().contains(entity.getStatus()) || !onlyActive) {
      return toDto(entity);
    }
    return null;
  }

  @Override
  public RaceType toEntity(RaceTypeDto dto, boolean onlyActive) {
    return null;
  }

}
