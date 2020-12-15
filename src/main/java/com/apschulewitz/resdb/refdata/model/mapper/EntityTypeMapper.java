package com.apschulewitz.resdb.refdata.model.mapper;

import com.apschulewitz.resdb.common.model.entity.VersionStatus;
import com.apschulewitz.resdb.common.model.mapper.VersionableEntityDtoMapper;
import com.apschulewitz.resdb.refdata.model.dto.DeityTypeDto;
import com.apschulewitz.resdb.refdata.model.dto.EntityTypeDto;
import com.apschulewitz.resdb.refdata.model.entity.DeityType;
import com.apschulewitz.resdb.refdata.model.entity.EntityType;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
public class EntityTypeMapper implements VersionableEntityDtoMapper<EntityType, EntityTypeDto> {


  @Override
  public EntityType toEntity(EntityTypeDto dto) {

    return EntityType.builder()
      .createdBy(dto.getCreatedBy())
      .id(dto.getId())
      .lastUpdated(dto.getLastUpdated())
      .name(dto.getName())
      .status(VersionStatus.getInstance(dto.getStatus()))
      .build();
  }

  @Override
  public EntityTypeDto toDto(EntityType entity) {
    if (entity == null) {
      throw new IllegalArgumentException("Null entity type cannot be mapped to dto");
    }

    return EntityTypeDto.builder()
      .createdBy(entity.getCreatedBy())
      .id(entity.getId())
      .lastUpdated(entity.getLastUpdated())
      .name(entity.getName())
      .status(entity.getStatus().name())
      .build();
  }

  @Override
  public EntityTypeDto toDto(EntityType entity, boolean onlyActive) {
    if (VersionStatus.getLiveStatuses().contains(entity.getStatus()) || !onlyActive) {
      return toDto(entity);
    }
    return null;
  }

  @Override
  public EntityType toEntity(EntityTypeDto dto, boolean onlyActive) {
    return null;
  }

}
