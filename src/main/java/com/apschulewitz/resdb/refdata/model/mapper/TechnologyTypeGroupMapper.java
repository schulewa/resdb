package com.apschulewitz.resdb.refdata.model.mapper;

import com.apschulewitz.resdb.common.model.dto.HistoricalDateDto;
import com.apschulewitz.resdb.common.model.entity.Gender;
import com.apschulewitz.resdb.common.model.entity.HistoricalDate;
import com.apschulewitz.resdb.common.model.entity.Title;
import com.apschulewitz.resdb.common.model.entity.VersionStatus;
import com.apschulewitz.resdb.common.model.mapper.TitleMapper;
import com.apschulewitz.resdb.common.model.mapper.VersionableEntityDtoMapper;
import com.apschulewitz.resdb.refdata.model.dto.PersonDto;
import com.apschulewitz.resdb.refdata.model.dto.PlaceDto;
import com.apschulewitz.resdb.refdata.model.dto.TechnologyTypeGroupDto;
import com.apschulewitz.resdb.refdata.model.dto.TitleDto;
import com.apschulewitz.resdb.refdata.model.entity.Person;
import com.apschulewitz.resdb.refdata.model.entity.Place;
import com.apschulewitz.resdb.refdata.model.entity.TechnologyTypeGroup;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
@NoArgsConstructor
public class TechnologyTypeGroupMapper implements VersionableEntityDtoMapper<TechnologyTypeGroup, TechnologyTypeGroupDto> {


  @Override
  public TechnologyTypeGroup toEntity(TechnologyTypeGroupDto dto) {
    if (dto == null) {
      throw new IllegalArgumentException("Null technology type group cannot be mapped to entity");
    }

    VersionStatus status = null;
    if (!StringUtils.isEmpty(dto.getStatus())) {
      status = VersionStatus.getInstance(dto.getStatus());
    }

    return TechnologyTypeGroup.builder()
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
  public TechnologyTypeGroupDto toDto(TechnologyTypeGroup entity) {
    if (entity == null) {
      throw new IllegalArgumentException("Null technology type group cannot be mapped to dto");
    }

    String status = null;
    if (entity.getStatus() != null) {
      status = entity.getStatus().name();
    }
    return TechnologyTypeGroupDto.builder()
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
  public TechnologyTypeGroupDto toDto(TechnologyTypeGroup entity, boolean onlyActive) {
    if (entity == null) {
      throw new IllegalArgumentException("Null technology type group cannot be mapped to dto");
    }

    if (VersionStatus.getLiveStatuses().contains(entity.getStatus()) || !onlyActive) {
      return toDto(entity);
    }
    return null;
  }

  @Override
  public TechnologyTypeGroup toEntity(TechnologyTypeGroupDto dto, boolean onlyActive) {
    if (dto == null) {
      throw new IllegalArgumentException("Null technology type group cannot be mapped to entity");
    }

    VersionStatus status = VersionStatus.getInstance(dto.getStatus());
    if (VersionStatus.getLiveStatuses().contains(status) || !onlyActive) {
      return toEntity(dto);
    }
    return null;
  }

}
