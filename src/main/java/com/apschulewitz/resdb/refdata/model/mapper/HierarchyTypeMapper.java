package com.apschulewitz.resdb.refdata.model.mapper;

import com.apschulewitz.resdb.common.model.entity.VersionStatus;
import com.apschulewitz.resdb.common.model.mapper.VersionableEntityDtoMapper;
import com.apschulewitz.resdb.refdata.model.dto.HierarchyTypeDto;
import com.apschulewitz.resdb.refdata.model.entity.HierarchyType;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
@NoArgsConstructor
public class HierarchyTypeMapper implements VersionableEntityDtoMapper<HierarchyType, HierarchyTypeDto> {


  @Override
  public HierarchyType toEntity(HierarchyTypeDto dto) {
    if (dto == null) {
      throw new IllegalArgumentException("Null hierarchy type cannot be mapped to entity");
    }

    VersionStatus status = null;
    if (!StringUtils.isEmpty(dto.getStatus())) {
      status = VersionStatus.getInstance(dto.getStatus());
    }

    return HierarchyType.builder()
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
  public HierarchyTypeDto toDto(HierarchyType entity) {
    if (entity == null) {
      throw new IllegalArgumentException("Null hierarchy type cannot be mapped to dto");
    }

    String status = null;
    if (entity.getStatus() != null) {
      status = entity.getStatus().name();
    }

    return HierarchyTypeDto.builder()
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
