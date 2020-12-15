package com.apschulewitz.resdb.refdata.model.mapper;

import com.apschulewitz.resdb.common.model.entity.VersionStatus;
import com.apschulewitz.resdb.common.model.mapper.VersionableEntityDtoMapper;
import com.apschulewitz.resdb.refdata.model.dto.DeityTypeDto;
import com.apschulewitz.resdb.refdata.model.entity.DeityType;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
public class DeityTypeMapper implements VersionableEntityDtoMapper<DeityType, DeityTypeDto> {


  @Override
  public DeityType toEntity(DeityTypeDto dto) {

    return DeityType.builder()
      .createdBy(dto.getCreatedBy())
      .id(dto.getId())
      .lastUpdated(dto.getLastUpdated())
      .name(dto.getName())
      .status(VersionStatus.getInstance(dto.getStatus()))
      .build();
  }

  @Override
  public DeityTypeDto toDto(DeityType entity) {
    if (entity == null) {
      throw new IllegalArgumentException("Null deity type cannot be mapped to dto");
    }

    return DeityTypeDto.builder()
      .createdBy(entity.getCreatedBy())
      .id(entity.getId())
      .lastUpdated(entity.getLastUpdated())
      .name(entity.getName())
      .status(entity.getStatus().name())
      .build();
  }

  @Override
  public DeityTypeDto toDto(DeityType entity, boolean onlyActive) {
    if (VersionStatus.getLiveStatuses().contains(entity.getStatus()) || !onlyActive) {
      return toDto(entity);
    }
    return null;
  }

  @Override
  public DeityType toEntity(DeityTypeDto dto, boolean onlyActive) {
    return null;
  }

}
