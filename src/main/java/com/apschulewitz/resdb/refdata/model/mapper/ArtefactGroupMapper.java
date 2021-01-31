package com.apschulewitz.resdb.refdata.model.mapper;

import com.apschulewitz.resdb.common.model.entity.VersionStatus;
import com.apschulewitz.resdb.common.model.mapper.VersionableEntityDtoMapper;
import com.apschulewitz.resdb.refdata.model.dto.ArtefactGroupDto;
import com.apschulewitz.resdb.refdata.model.entity.ArtefactGroup;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
@NoArgsConstructor
public class ArtefactGroupMapper implements VersionableEntityDtoMapper<ArtefactGroup, ArtefactGroupDto> {


  @Override
  public ArtefactGroup toEntity(ArtefactGroupDto dto) {
    if (dto == null) {
      throw new IllegalArgumentException("Null artefact group cannot be mapped to entity");
    }

    VersionStatus status = null;
    if (!StringUtils.isEmpty(dto.getStatus())) {
      status = VersionStatus.getInstance(dto.getStatus());
    }

    return ArtefactGroup.builder()
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
  public ArtefactGroupDto toDto(ArtefactGroup entity) {
    if (entity == null) {
      throw new IllegalArgumentException("Null artefact group cannot be mapped to dto");
    }

    String status = entity.getStatus() == null ? null : entity.getStatus().name();
    return ArtefactGroupDto.builder()
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
  public ArtefactGroupDto toDto(ArtefactGroup entity, boolean onlyActive) {
    if (entity == null) {
      throw new IllegalArgumentException("Null artefact group cannot be mapped to dto");
    }

    if (VersionStatus.getLiveStatuses().contains(entity.getStatus()) || !onlyActive) {
      return toDto(entity);
    }
    return null;
  }

  @Override
  public ArtefactGroup toEntity(ArtefactGroupDto dto, boolean onlyActive) {
    if (dto == null) {
      throw new IllegalArgumentException("Null artefact group cannot be mapped to entity");
    }

    VersionStatus status = StringUtils.isEmpty(dto.getStatus()) ? null : VersionStatus.getInstance(dto.getStatus());
    if (VersionStatus.getLiveStatuses().contains(status) || !onlyActive) {
      return toEntity(dto);
    }
    return null;
  }

}
