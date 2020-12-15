package com.apschulewitz.resdb.refdata.model.mapper;

import com.apschulewitz.resdb.common.model.entity.VersionStatus;
import com.apschulewitz.resdb.common.model.mapper.VersionableEntityDtoMapper;
import com.apschulewitz.resdb.refdata.model.dto.PersonTypeDto;
import com.apschulewitz.resdb.refdata.model.entity.PersonType;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
public class PersonTypeMapper implements VersionableEntityDtoMapper<PersonType, PersonTypeDto> {


  @Override
  public PersonType toEntity(PersonTypeDto dto) {
    return PersonType.builder()
      .createdBy(dto.getCreatedBy())
      .id(dto.getId())
      .lastUpdated(dto.getLastUpdated())
      .name(dto.getName())
      .status(VersionStatus.getInstance(dto.getStatus()))
      .build();
  }

  @Override
  public PersonTypeDto toDto(PersonType entity) {
    if (entity == null) {
      throw new IllegalArgumentException("Null person type cannot be mapped to dto");
    }

    return PersonTypeDto.builder()
      .createdBy(entity.getCreatedBy())
      .id(entity.getId())
      .lastUpdated(entity.getLastUpdated())
      .name(entity.getName())
      .status(entity.getStatus().name())
      .build();
  }

  @Override
  public PersonTypeDto toDto(PersonType entity, boolean onlyActive) {
    if (VersionStatus.getLiveStatuses().contains(entity.getStatus()) || !onlyActive) {
      return toDto(entity);
    }
    return null;
  }

  @Override
  public PersonType toEntity(PersonTypeDto dto, boolean onlyActive) {
    return null;
  }

}
