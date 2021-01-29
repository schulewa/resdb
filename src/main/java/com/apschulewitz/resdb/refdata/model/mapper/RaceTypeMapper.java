package com.apschulewitz.resdb.refdata.model.mapper;

import com.apschulewitz.resdb.common.model.entity.VersionStatus;
import com.apschulewitz.resdb.common.model.mapper.VersionableEntityDtoMapper;
import com.apschulewitz.resdb.refdata.model.dto.RaceTypeDto;
import com.apschulewitz.resdb.refdata.model.entity.RaceType;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
@NoArgsConstructor
public class RaceTypeMapper implements VersionableEntityDtoMapper<RaceType, RaceTypeDto> {

  @Override
  public RaceType toEntity(RaceTypeDto dto) {
    if (dto == null) {
      throw new IllegalArgumentException("Null race type cannot be mapped to entity");
    }

    VersionStatus status = StringUtils.isEmpty(dto.getStatus()) ? null : VersionStatus.getInstance(dto.getStatus());
    return RaceType.builder()
      .createdBy(dto.getCreatedBy())
      .id(dto.getId())
      .lastUpdated(dto.getLastUpdated())
      .name(dto.getName())
      .status(status)
      .updatedBy(dto.getUpdatedBy())
      .versionNumber(dto.getVersionNumber())
      .build();
  }

  @Override
  public RaceTypeDto toDto(RaceType entity) {
    if (entity == null) {
      throw new IllegalArgumentException("Null race type cannot be mapped to dto");
    }

    String status = entity.getStatus() == null ? null : entity.getStatus().name();
    return RaceTypeDto.builder()
      .createdBy(entity.getCreatedBy())
      .id(entity.getId())
      .lastUpdated(entity.getLastUpdated())
      .name(entity.getName())
      .status(status)
      .updatedBy(entity.getUpdatedBy())
      .versionNumber(entity.getVersionNumber())
      .build();
  }

  @Override
  public RaceTypeDto toDto(RaceType entity, boolean onlyActive) {
    if (entity == null) {
      throw new IllegalArgumentException("Null race type cannot be mapped to dto");
    }

    if (VersionStatus.getLiveStatuses().contains(entity.getStatus()) || !onlyActive) {
      return toDto(entity);
    }
    return null;
  }

  @Override
  public RaceType toEntity(RaceTypeDto dto, boolean onlyActive) {
    if (dto == null) {
      throw new IllegalArgumentException("Null race type cannot be mapped to entity");
    }

    if (VersionStatus.getLiveStatuses().contains(dto.getStatus()) || !onlyActive) {
      return toEntity(dto);
    }
    return null;
  }

}
