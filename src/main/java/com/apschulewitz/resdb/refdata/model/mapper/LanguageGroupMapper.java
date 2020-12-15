package com.apschulewitz.resdb.refdata.model.mapper;

import com.apschulewitz.resdb.common.model.entity.VersionStatus;
import com.apschulewitz.resdb.common.model.mapper.VersionableEntityDtoMapper;
import com.apschulewitz.resdb.refdata.model.dto.LanguageGroupDto;
import com.apschulewitz.resdb.refdata.model.entity.LanguageGroup;
import com.apschulewitz.resdb.refdata.model.entity.Region;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
public class LanguageGroupMapper implements VersionableEntityDtoMapper<LanguageGroup, LanguageGroupDto> {


  @Override
  public LanguageGroup toEntity(LanguageGroupDto dto) {
    Region region = Region.builder()
      .createdBy(dto.getCreatedBy())
      .id(dto.getId())
      .lastUpdated(dto.getLastUpdated())
      .name(dto.getName())
      .status(VersionStatus.getInstance(dto.getStatus()))
      .build();

    return LanguageGroup.builder()
      .createdBy(dto.getCreatedBy())
      .id(dto.getId())
      .lastUpdated(dto.getLastUpdated())
      .name(dto.getName())
      .region(region)
      .status(VersionStatus.getInstance(dto.getStatus()))
      .build();
  }

  @Override
  public LanguageGroupDto toDto(LanguageGroup entity) {
    if (entity == null) {
      throw new IllegalArgumentException("Null language group cannot be mapped to dto");
    }

    return LanguageGroupDto.builder()
      .createdBy(entity.getCreatedBy())
      .id(entity.getId())
      .lastUpdated(entity.getLastUpdated())
      .name(entity.getName())
      .status(entity.getStatus().name())
      .build();
  }

  @Override
  public LanguageGroupDto toDto(LanguageGroup entity, boolean onlyActive) {
    if (VersionStatus.getLiveStatuses().contains(entity.getStatus()) || !onlyActive) {
      return toDto(entity);
    }
    return null;
  }

  @Override
  public LanguageGroup toEntity(LanguageGroupDto dto, boolean onlyActive) {
    return null;
  }

}
