package com.apschulewitz.resdb.refdata.model.mapper;

import com.apschulewitz.resdb.common.model.entity.VersionStatus;
import com.apschulewitz.resdb.common.model.mapper.VersionableEntityDtoMapper;
import com.apschulewitz.resdb.common.utils.StringUtils;
import com.apschulewitz.resdb.refdata.model.dto.LanguageGroupDto;
import com.apschulewitz.resdb.refdata.model.dto.RegionDto;
import com.apschulewitz.resdb.refdata.model.entity.LanguageGroup;
import com.apschulewitz.resdb.refdata.model.entity.Region;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
public class LanguageGroupMapper implements VersionableEntityDtoMapper<LanguageGroup, LanguageGroupDto> {


  @Override
  public LanguageGroup toEntity(LanguageGroupDto dto) {
    if (dto == null)
      return null;

    Region region = null;
    if (dto.getRegion() != null) {
      VersionStatus status = StringUtils.isEmpty(dto.getRegion().getStatus()) ? null : VersionStatus.getInstance(dto.getStatus());
      Region.builder()
        .createdBy(dto.getRegion().getCreatedBy())
        .id(dto.getRegion().getId())
        .lastUpdated(dto.getRegion().getLastUpdated())
        .name(dto.getRegion().getName())
        .status(status)
        .updatedBy(dto.getRegion().getUpdatedBy())
        .versionNumber(dto.getRegion().getVersionNumber())
        .build();
    }

    return LanguageGroup.builder()
      .createdBy(dto.getCreatedBy())
      .id(dto.getId())
      .lastUpdated(dto.getLastUpdated())
      .name(dto.getName())
      .region(region)
      .status(VersionStatus.getInstance(dto.getStatus()))
      .updatedBy(dto.getUpdatedBy())
      .versionNumber(dto.getVersionNumber())
      .build();
  }

  @Override
  public LanguageGroupDto toDto(LanguageGroup entity) {
    if (entity == null) {
      throw new IllegalArgumentException("Null language group cannot be mapped to dto");
    }

    RegionDto region = null;
    if (entity.getRegion() != null) {
      String status = entity.getRegion().getStatus() == null ? null : entity.getRegion().getStatus().name();
      region = RegionDto.builder()
        .createdBy(entity.getRegion().getCreatedBy())
        .id(entity.getRegion().getId())
        .lastUpdated(entity.getRegion().getLastUpdated())
        .name(entity.getRegion().getName())
        .status(status)
        .updatedBy(entity.getRegion().getUpdatedBy())
        .versionNumber(entity.getRegion().getVersionNumber())
        .build();
    }
    String status = entity.getStatus() == null ? null : entity.getStatus().name();
    return LanguageGroupDto.builder()
      .createdBy(entity.getCreatedBy())
      .id(entity.getId())
      .lastUpdated(entity.getLastUpdated())
      .name(entity.getName())
      .region(region)
      .status(status)
      .updatedBy(entity.getUpdatedBy())
      .versionNumber(entity.getVersionNumber())
      .build();
  }

  @Override
  public LanguageGroupDto toDto(LanguageGroup entity, boolean onlyActive) {
    if (entity == null) {
      throw new IllegalArgumentException("Null language group cannot be mapped to dto");
    }

    if (VersionStatus.getLiveStatuses().contains(entity.getStatus()) || !onlyActive) {
      return toDto(entity);
    }
    return null;
  }

  @Override
  public LanguageGroup toEntity(LanguageGroupDto dto, boolean onlyActive) {
    if (dto == null) {
      throw new IllegalArgumentException("Null language group cannot be mapped to entity");
    }

    if (VersionStatus.getLiveStatuses().contains(dto.getStatus()) || !onlyActive) {
      return toEntity(dto);
    }

    return null;
  }

}
