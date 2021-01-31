package com.apschulewitz.resdb.refdata.service;

import com.apschulewitz.resdb.common.model.EntityTypeEnum;
import com.apschulewitz.resdb.common.service.AbstractService;
import com.apschulewitz.resdb.common.service.DataService;
import com.apschulewitz.resdb.refdata.model.dao.RaceTypeDao;
import com.apschulewitz.resdb.refdata.model.dto.RaceTypeDto;
import com.apschulewitz.resdb.refdata.model.entity.RaceType;
import com.apschulewitz.resdb.refdata.model.mapper.RaceTypeMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class RaceTypeService
  extends AbstractService<RaceTypeDto, RaceType, Long, RaceTypeMapper, RaceTypeDao>
  implements DataService<RaceTypeDto, RaceType, Long> {

  private RaceTypeDao raceTypeDao;

  private RaceTypeMapper raceTypeMapper;

  public RaceTypeService(RaceTypeDao raceTypeDao, RaceTypeMapper raceTypeMapper) {
    this.raceTypeDao = raceTypeDao;
    this.raceTypeMapper = raceTypeMapper;
  }

  public RaceTypeDto add(RaceTypeDto dto) {
    return add(EntityTypeEnum.RACE_TYPE, raceTypeMapper, raceTypeDao, dto);
  }

  public RaceTypeDto deleteById(Long id) {
    return deleteById(EntityTypeEnum.RACE_TYPE, raceTypeMapper, raceTypeDao, id);
  }

  public List<RaceTypeDto> findAll(boolean onlyActive) {
    return findAll(EntityTypeEnum.RACE_TYPE, raceTypeMapper, raceTypeDao, onlyActive);
  }

  public RaceTypeDto findById(Long id) {
    return findById(EntityTypeEnum.RACE_TYPE, raceTypeMapper, raceTypeDao, id);
  }

  public RaceTypeDto update(RaceTypeDto dto) {
    return update(EntityTypeEnum.RACE_TYPE, raceTypeMapper, raceTypeDao, dto);
  }
}
