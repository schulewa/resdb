package com.apschulewitz.resdb.refdata.service;

import com.apschulewitz.resdb.common.model.EntityTypeEnum;
import com.apschulewitz.resdb.common.service.AbstractService;
import com.apschulewitz.resdb.common.service.DataService;
import com.apschulewitz.resdb.refdata.model.dao.DeityTypeDao;
import com.apschulewitz.resdb.refdata.model.dto.DeityTypeDto;
import com.apschulewitz.resdb.refdata.model.entity.DeityType;
import com.apschulewitz.resdb.refdata.model.mapper.DeityTypeMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class DeityTypeService
  extends AbstractService<DeityTypeDto, DeityType, Long, DeityTypeMapper, DeityTypeDao>
  implements DataService<DeityTypeDto, DeityType, Long> {

  private DeityTypeDao deityTypeDao;

  private DeityTypeMapper deityTypeMapper;

  public DeityTypeService(DeityTypeDao deityTypeDao, DeityTypeMapper deityTypeMapper) {
    this.deityTypeDao = deityTypeDao;
    this.deityTypeMapper = deityTypeMapper;
  }

  public DeityTypeDto add(DeityTypeDto dto) {
    return add(EntityTypeEnum.DEITY_TYPE, deityTypeMapper, deityTypeDao, dto);
  }

  public DeityTypeDto deleteById(Long id) {
    return deleteById(EntityTypeEnum.DEITY_TYPE, deityTypeMapper, deityTypeDao, id);
  }

  public List<DeityTypeDto> findAll(boolean onlyActive) {
    return findAll(EntityTypeEnum.DEITY_TYPE, deityTypeMapper, deityTypeDao, onlyActive);
  }

  public DeityTypeDto findById(Long id) {
    return findById(EntityTypeEnum.DEITY_TYPE, deityTypeMapper, deityTypeDao, id);
  }

  public DeityTypeDto update(DeityTypeDto dto) {
    return update(EntityTypeEnum.DEITY_TYPE, deityTypeMapper, deityTypeDao, dto);
  }
}
