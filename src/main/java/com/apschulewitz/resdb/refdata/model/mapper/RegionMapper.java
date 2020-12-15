package com.apschulewitz.resdb.refdata.model.mapper;

import com.apschulewitz.resdb.common.model.entity.VersionStatus;
import com.apschulewitz.resdb.common.model.mapper.VersionableEntityDtoMapper;
import com.apschulewitz.resdb.refdata.model.dto.RegionDto;
import com.apschulewitz.resdb.refdata.model.entity.Region;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
public class RegionMapper implements VersionableEntityDtoMapper<Region, RegionDto> {

  @Override
  public RegionDto toDto(Region entity, boolean onlyActive) {
    return RegionDto.builder()
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
  public Region toEntity(RegionDto dto, boolean onlyActive) {
    return Region.builder()
      .createdBy(dto.getCreatedBy())
      .id(dto.getId())
      .lastUpdated(dto.getLastUpdated())
      .name(dto.getName())
      .status(VersionStatus.getInstance(dto.getStatus()))
      .updatedBy(dto.getUpdatedBy())
      .versionNumber(dto.getVersionNumber())
      .build();
  }
}
