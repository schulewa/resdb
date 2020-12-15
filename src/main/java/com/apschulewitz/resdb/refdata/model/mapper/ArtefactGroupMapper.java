package com.apschulewitz.resdb.refdata.model.mapper;

import com.apschulewitz.resdb.common.model.entity.VersionStatus;
import com.apschulewitz.resdb.common.model.mapper.VersionableEntityDtoMapper;
import com.apschulewitz.resdb.refdata.model.dto.ArtefactGroupDto;
import com.apschulewitz.resdb.refdata.model.dto.ReferenceTypeDto;
import com.apschulewitz.resdb.refdata.model.entity.ArtefactGroup;
import com.apschulewitz.resdb.refdata.model.entity.ReferenceType;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
public class ArtefactGroupMapper implements VersionableEntityDtoMapper<ArtefactGroup, ArtefactGroupDto> {


  @Override
  public ArtefactGroup toEntity(ArtefactGroupDto dto) {

    return ArtefactGroup.builder()
      .createdBy(dto.getCreatedBy())
      .id(dto.getId())
      .lastUpdated(dto.getLastUpdated())
      .name(dto.getName())
      .status(VersionStatus.getInstance(dto.getStatus()))
      .build();
  }

  @Override
  public ArtefactGroupDto toDto(ArtefactGroup entity) {
    if (entity == null) {
      throw new IllegalArgumentException("Null artefact group cannot be mapped to dto");
    }

    return ArtefactGroupDto.builder()
      .createdBy(entity.getCreatedBy())
      .id(entity.getId())
      .lastUpdated(entity.getLastUpdated())
      .name(entity.getName())
      .status(entity.getStatus().name())
      .build();
  }

  @Override
  public ArtefactGroupDto toDto(ArtefactGroup entity, boolean onlyActive) {
    if (VersionStatus.getLiveStatuses().contains(entity.getStatus()) || !onlyActive) {
      return toDto(entity);
    }
    return null;
  }

  @Override
  public ArtefactGroup toEntity(ArtefactGroupDto dto, boolean onlyActive) {
    return null;
  }

//  @Override
//  public Person toDto(PersonDto entity) {
//    return null;
//  }
}
