package com.apschulewitz.resdb.refdata.model.mapper;

import com.apschulewitz.resdb.common.model.entity.VersionStatus;
import com.apschulewitz.resdb.common.model.mapper.VersionableEntityDtoMapper;
import com.apschulewitz.resdb.refdata.model.dto.CountryDto;
import com.apschulewitz.resdb.refdata.model.entity.Country;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
public class CountryMapper implements VersionableEntityDtoMapper<Country, CountryDto> {


  @Override
  public Country toEntity(CountryDto dto) {

    return Country.builder()
      .createdBy(dto.getCreatedBy())
      .id(dto.getId())
      .lastUpdated(dto.getLastUpdated())
      .name(dto.getName())
      .status(VersionStatus.getInstance(dto.getStatus()))
      .build();
  }

  @Override
  public CountryDto toDto(Country entity) {
    if (entity == null) {
      throw new IllegalArgumentException("Null country type cannot be mapped to dto");
    }

    return CountryDto.builder()
      .createdBy(entity.getCreatedBy())
      .id(entity.getId())
      .lastUpdated(entity.getLastUpdated())
      .name(entity.getName())
      .status(entity.getStatus().name())
      .build();
  }

  @Override
  public CountryDto toDto(Country entity, boolean onlyActive) {
    if (VersionStatus.getLiveStatuses().contains(entity.getStatus()) || !onlyActive) {
      return toDto(entity);
    }
    return null;
  }

  @Override
  public Country toEntity(CountryDto dto, boolean onlyActive) {
    return null;
  }

}
