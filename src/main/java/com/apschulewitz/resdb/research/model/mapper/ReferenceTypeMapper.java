package com.apschulewitz.resdb.research.model.mapper;

import com.apschulewitz.resdb.common.model.entity.VersionStatus;
import com.apschulewitz.resdb.common.model.mapper.VersionableEntityDtoMapper;
import com.apschulewitz.resdb.research.model.dto.ReferenceTypeDto;
import com.apschulewitz.resdb.research.model.entity.ReferenceType;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
public class ReferenceTypeMapper implements VersionableEntityDtoMapper<ReferenceType, ReferenceTypeDto> {


  @Override
  public ReferenceType toEntity(ReferenceTypeDto dto) {
    if (dto == null) {
      throw new IllegalArgumentException("Null reference type cannot be mapped to entity");
    }

    return ReferenceType.builder()
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
  public ReferenceTypeDto toDto(ReferenceType entity) {
    if (entity == null) {
      throw new IllegalArgumentException("Null reference type cannot be mapped to dto");
    }

    String status = null;
    if (entity.getStatus() != null) {
      status = entity.getStatus().name();
    }

    return ReferenceTypeDto.builder()
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
  public ReferenceTypeDto toDto(ReferenceType entity, boolean onlyActive) {
    if (entity == null) {
      throw new IllegalArgumentException("Null reference type cannot be mapped to dto");
    }

    if (VersionStatus.getLiveStatuses().contains(entity.getStatus()) || !onlyActive) {
      return toDto(entity);
    }
    return null;
  }

  @Override
  public ReferenceType toEntity(ReferenceTypeDto dto, boolean onlyActive) {
    if (dto == null) {
      throw new IllegalArgumentException("Null reference type cannot be mapped to entity");
    }

    VersionStatus status = VersionStatus.getInstance(dto.getStatus());
    if (VersionStatus.getLiveStatuses().contains(status) || !onlyActive) {
      return toEntity(dto);
    }

    return null;
  }

}
