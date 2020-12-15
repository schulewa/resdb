package com.apschulewitz.resdb.refdata.model.mapper;

import com.apschulewitz.resdb.common.model.dto.RiverDto;
import com.apschulewitz.resdb.common.model.mapper.VersionableEntityDtoMapper;
import com.apschulewitz.resdb.refdata.model.entity.River;
import org.springframework.stereotype.Component;

@Component
public class RiverMapper implements VersionableEntityDtoMapper<River, RiverDto> {

  @Override
  public RiverDto toDto(River entity, boolean onlyActive) {
    return null;
  }

  @Override
  public River toEntity(RiverDto dto, boolean onlyActive) {
    return null;
  }
}
