package com.apschulewitz.resdb.refdata.model.mapper;

import com.apschulewitz.resdb.common.model.entity.VersionStatus;
import com.apschulewitz.resdb.common.model.mapper.VersionableEntityDtoMapper;
import com.apschulewitz.resdb.refdata.model.dto.PublicationTypeDto;
import com.apschulewitz.resdb.refdata.model.entity.PublicationType;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
public class PublicationTypeMapper implements VersionableEntityDtoMapper<PublicationType, PublicationTypeDto> {


  @Override
  public PublicationType toEntity(PublicationTypeDto dto) {
    return PublicationType.builder()
      .createdBy(dto.getCreatedBy())
      .id(dto.getId())
      .lastUpdated(dto.getLastUpdated())
      .name(dto.getName())
      .status(VersionStatus.getInstance(dto.getStatus()))
      .build();
  }

  @Override
  public PublicationTypeDto toDto(PublicationType entity) {
    if (entity == null) {
      throw new IllegalArgumentException("Null publication type cannot be mapped to dto");
    }

    return PublicationTypeDto.builder()
      .createdBy(entity.getCreatedBy())
      .id(entity.getId())
      .lastUpdated(entity.getLastUpdated())
      .name(entity.getName())
      .status(entity.getStatus().name())
      .build();
  }

  @Override
  public PublicationTypeDto toDto(PublicationType entity, boolean onlyActive) {
    if (VersionStatus.getLiveStatuses().contains(entity.getStatus()) || !onlyActive) {
      return toDto(entity);
    }
    return null;
  }

  @Override
  public PublicationType toEntity(PublicationTypeDto dto, boolean onlyActive) {
    return null;
  }

}
