package com.apschulewitz.resdb.refdata.model.mapper;

import com.apschulewitz.resdb.common.model.entity.VersionStatus;
import com.apschulewitz.resdb.common.model.mapper.VersionableEntityDtoMapper;
import com.apschulewitz.resdb.refdata.model.dto.HierarchyTypeDto;
import com.apschulewitz.resdb.refdata.model.entity.HierarchyType;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
public class HierarchyTypeMapper implements VersionableEntityDtoMapper<HierarchyType, HierarchyTypeDto> {


  @Override
  public HierarchyType toEntity(HierarchyTypeDto dto) {
    if (dto == null) {
      throw new IllegalArgumentException("Null hierarchy type cannot be mapped to entity");
    }

    return HierarchyType.builder()
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
  public HierarchyTypeDto toDto(HierarchyType entity) {
    if (entity == null) {
      throw new IllegalArgumentException("Null hierarchy type cannot be mapped to dto");
    }

    return HierarchyTypeDto.builder()
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
  public HierarchyTypeDto toDto(HierarchyType entity, boolean onlyActive) {
    if (entity == null) {
      throw new IllegalArgumentException("Null hierarchy type cannot be mapped to dto");
    }

    if (VersionStatus.getLiveStatuses().contains(entity.getStatus()) || !onlyActive) {
      return toDto(entity);
    }

    return null;
  }

  @Override
  public HierarchyType toEntity(HierarchyTypeDto dto, boolean onlyActive) {
    if (dto == null) {
      throw new IllegalArgumentException("Null hierarchy type cannot be mapped to entity");
    }

    if (VersionStatus.getLiveStatuses().contains(dto.getStatus()) || !onlyActive) {
      return toEntity(dto);
    }

    return null;
  }

}
