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

    return HierarchyType.builder()
      .createdBy(dto.getCreatedBy())
      .id(dto.getId())
      .lastUpdated(dto.getLastUpdated())
      .name(dto.getName())
      .status(VersionStatus.getInstance(dto.getStatus()))
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
      .build();
  }

  @Override
  public HierarchyTypeDto toDto(HierarchyType entity, boolean onlyActive) {
    if (VersionStatus.getLiveStatuses().contains(entity.getStatus()) || !onlyActive) {
      return toDto(entity);
    }
    return null;
  }

  @Override
  public HierarchyType toEntity(HierarchyTypeDto dto, boolean onlyActive) {
    return null;
  }

}
