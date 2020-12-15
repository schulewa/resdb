package com.apschulewitz.resdb.refdata.model.mapper;

import com.apschulewitz.resdb.common.model.entity.VersionStatus;
import com.apschulewitz.resdb.common.model.mapper.VersionableEntityDtoMapper;
import com.apschulewitz.resdb.refdata.model.dto.TaleTypeDto;
import com.apschulewitz.resdb.refdata.model.entity.TaleType;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
public class TaleTypeMapper implements VersionableEntityDtoMapper<TaleType, TaleTypeDto> {


  @Override
  public TaleType toEntity(TaleTypeDto dto) {

    return TaleType.builder()
      .createdBy(dto.getCreatedBy())
      .id(dto.getId())
      .lastUpdated(dto.getLastUpdated())
      .name(dto.getName())
      .status(VersionStatus.getInstance(dto.getStatus()))
      .updatedBy(dto.getUpdatedBy())
      .build();
  }

  @Override
  public TaleTypeDto toDto(TaleType entity) {
    if (entity == null) {
      throw new IllegalArgumentException("Null reference type group cannot be mapped to dto");
    }

    return TaleTypeDto.builder()
      .createdBy(entity.getCreatedBy())
      .id(entity.getId())
      .lastUpdated(entity.getLastUpdated())
      .name(entity.getName())
      .status(entity.getStatus().name())
      .updatedBy(entity.getUpdatedBy())
      .build();
  }

  @Override
  public TaleTypeDto toDto(TaleType entity, boolean onlyActive) {
    if (VersionStatus.getLiveStatuses().contains(entity.getStatus()) || !onlyActive) {
      return toDto(entity);
    }
    return null;
  }

  @Override
  public TaleType toEntity(TaleTypeDto dto, boolean onlyActive) {
    return null;
  }

//  @Override
//  public Person toDto(PersonDto entity) {
//    return null;
//  }
}
