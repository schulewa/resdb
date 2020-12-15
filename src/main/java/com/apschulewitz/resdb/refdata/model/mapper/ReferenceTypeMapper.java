package com.apschulewitz.resdb.refdata.model.mapper;

import com.apschulewitz.resdb.common.model.entity.VersionStatus;
import com.apschulewitz.resdb.common.model.mapper.VersionableEntityDtoMapper;
import com.apschulewitz.resdb.refdata.model.dao.ReferenceTypeDao;
import com.apschulewitz.resdb.refdata.model.dto.ReferenceTypeDto;
import com.apschulewitz.resdb.refdata.model.dto.TechnologyTypeGroupDto;
import com.apschulewitz.resdb.refdata.model.entity.ReferenceType;
import com.apschulewitz.resdb.refdata.model.entity.TechnologyTypeGroup;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
public class ReferenceTypeMapper implements VersionableEntityDtoMapper<ReferenceType, ReferenceTypeDto> {


  @Override
  public ReferenceType toEntity(ReferenceTypeDto dto) {

    return ReferenceType.builder()
      .createdBy(dto.getCreatedBy())
      .id(dto.getId())
      .lastUpdated(dto.getLastUpdated())
      .name(dto.getName())
      .status(VersionStatus.getInstance(dto.getStatus()))
      .build();
  }

  @Override
  public ReferenceTypeDto toDto(ReferenceType entity) {
    if (entity == null) {
      throw new IllegalArgumentException("Null reference type group cannot be mapped to dto");
    }

    return ReferenceTypeDto.builder()
      .createdBy(entity.getCreatedBy())
      .id(entity.getId())
      .lastUpdated(entity.getLastUpdated())
      .name(entity.getName())
      .status(entity.getStatus().name())
      .build();
  }

  @Override
  public ReferenceTypeDto toDto(ReferenceType entity, boolean onlyActive) {
    if (VersionStatus.getLiveStatuses().contains(entity.getStatus()) || !onlyActive) {
      return toDto(entity);
    }
    return null;
  }

  @Override
  public ReferenceType toEntity(ReferenceTypeDto dto, boolean onlyActive) {
    return null;
  }

//  @Override
//  public Person toDto(PersonDto entity) {
//    return null;
//  }
}
