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

@Component
@NoArgsConstructor
public class TechnologyTypeGroupMapper implements VersionableEntityDtoMapper<TechnologyTypeGroup, TechnologyTypeGroupDto> {


  @Override
  public TechnologyTypeGroup toEntity(TechnologyTypeGroupDto dto) {

    return TechnologyTypeGroup.builder()
      .createdBy(dto.getCreatedBy())
      .id(dto.getId())
      .lastUpdated(dto.getLastUpdated())
      .name(dto.getName())
      .status(VersionStatus.getInstance(dto.getStatus()))
      .build();
  }

  @Override
  public TechnologyTypeGroupDto toDto(TechnologyTypeGroup entity) {
    if (entity == null) {
      throw new IllegalArgumentException("Null technology type group cannot be mapped to dto");
    }

    return TechnologyTypeGroupDto.builder()
      .createdBy(entity.getCreatedBy())
      .id(entity.getId())
      .lastUpdated(entity.getLastUpdated())
      .name(entity.getName())
      .status(entity.getStatus().name())
      .build();
  }

  @Override
  public TechnologyTypeGroupDto toDto(TechnologyTypeGroup entity, boolean onlyActive) {
    if (VersionStatus.getLiveStatuses().contains(entity.getStatus()) || !onlyActive) {
      return toDto(entity);
    }
    return null;
  }

  @Override
  public TechnologyTypeGroup toEntity(TechnologyTypeGroupDto dto, boolean onlyActive) {
    return null;
  }

//  @Override
//  public Person toDto(PersonDto entity) {
//    return null;
//  }
}
