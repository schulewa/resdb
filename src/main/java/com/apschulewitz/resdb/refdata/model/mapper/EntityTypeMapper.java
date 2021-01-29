package com.apschulewitz.resdb.refdata.model.mapper;

import com.apschulewitz.resdb.common.model.entity.VersionStatus;
import com.apschulewitz.resdb.common.model.mapper.VersionableEntityDtoMapper;
import com.apschulewitz.resdb.refdata.model.dto.EntityTypeDto;
import com.apschulewitz.resdb.refdata.model.entity.EntityType;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
public class EntityTypeMapper implements VersionableEntityDtoMapper<EntityType, EntityTypeDto> {


  @Override
  public EntityType toEntity(EntityTypeDto dto) {

    if (dto == null) {
      throw new IllegalArgumentException("Null entity type cannot be mapped to entity");
    }

    return EntityType.builder()
      .createdBy(dto.getCreatedBy())
      .id(dto.getId())
      .lastUpdated(dto.getLastUpdated())
      .name(dto.getName())
      .status(VersionStatus.getInstance(dto.getStatus()))
      .updatedBy(dto.getUpdatedBy())
      .versionNumber(dto.getVersionNumber())
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
      .updatedBy(entity.getUpdatedBy())
      .versionNumber(entity.getVersionNumber())
      .build();
  }

  @Override
  public EntityTypeDto toDto(EntityType entity, boolean onlyActive) {
    if (entity == null) {
      throw new IllegalArgumentException("Null entity type cannot be mapped to dto");
    }

    if (VersionStatus.getLiveStatuses().contains(entity.getStatus()) || !onlyActive) {
      return toDto(entity);
    }
    return null;
  }

  @Override
  public EntityType toEntity(EntityTypeDto dto, boolean onlyActive) {
    if (dto == null) {
      throw new IllegalArgumentException("Null entity type cannot be mapped to entity");
    }

    VersionStatus status = VersionStatus.getInstance(dto.getStatus());
    if (VersionStatus.getLiveStatuses().contains(status) || !onlyActive) {
      return toEntity(dto);
    }

    return null;
  }

}
