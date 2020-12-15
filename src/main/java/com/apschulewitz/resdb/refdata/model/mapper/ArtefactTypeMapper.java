package com.apschulewitz.resdb.refdata.model.mapper;

import com.apschulewitz.resdb.common.model.entity.VersionStatus;
import com.apschulewitz.resdb.common.model.mapper.VersionableEntityDtoMapper;
import com.apschulewitz.resdb.refdata.model.dto.ArtefactTypeDto;
import com.apschulewitz.resdb.refdata.model.entity.ArtefactType;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
public class ArtefactTypeMapper implements VersionableEntityDtoMapper<ArtefactType, ArtefactTypeDto> {


  @Override
  public ArtefactType toEntity(ArtefactTypeDto dto) {

    return ArtefactType.builder()
      .createdBy(dto.getCreatedBy())
      .id(dto.getId())
      .lastUpdated(dto.getLastUpdated())
      .name(dto.getName())
      .status(VersionStatus.getInstance(dto.getStatus()))
      .build();
  }

  @Override
  public ArtefactTypeDto toDto(ArtefactType entity) {
    if (entity == null) {
      throw new IllegalArgumentException("Null artefact type cannot be mapped to dto");
    }

    return ArtefactTypeDto.builder()
      .createdBy(entity.getCreatedBy())
      .id(entity.getId())
      .lastUpdated(entity.getLastUpdated())
      .name(entity.getName())
      .status(entity.getStatus().name())
      .build();
  }

  @Override
  public ArtefactTypeDto toDto(ArtefactType entity, boolean onlyActive) {
    if (VersionStatus.getLiveStatuses().contains(entity.getStatus()) || !onlyActive) {
      return toDto(entity);
    }
    return null;
  }

  @Override
  public ArtefactType toEntity(ArtefactTypeDto dto, boolean onlyActive) {
    return null;
  }

}
