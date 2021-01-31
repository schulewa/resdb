package com.apschulewitz.resdb.refdata.service;

import com.apschulewitz.resdb.common.model.EntityTypeEnum;
import com.apschulewitz.resdb.common.service.AbstractService;
import com.apschulewitz.resdb.common.service.DataService;
import com.apschulewitz.resdb.refdata.model.dao.TaleTypeDao;
import com.apschulewitz.resdb.refdata.model.dto.TaleTypeDto;
import com.apschulewitz.resdb.refdata.model.entity.TaleType;
import com.apschulewitz.resdb.refdata.model.mapper.TaleTypeMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class TaleTypeService
  extends AbstractService<TaleTypeDto, TaleType, Long, TaleTypeMapper, TaleTypeDao>
  implements DataService<TaleTypeDto, TaleType, Long> {

  private TaleTypeDao taleTypeDao;

  private TaleTypeMapper taleTypeMapper;

  public TaleTypeService(TaleTypeDao taleTypeDao, TaleTypeMapper taleTypeMapper) {
    this.taleTypeDao = taleTypeDao;
    this.taleTypeMapper = taleTypeMapper;
  }

  public TaleTypeDto add(TaleTypeDto dto) {
    return add(EntityTypeEnum.TALE_TYPE, taleTypeMapper, taleTypeDao, dto);
  }

  public TaleTypeDto deleteById(Long id) {
    return deleteById(EntityTypeEnum.TALE_TYPE, taleTypeMapper, taleTypeDao, id);
  }

  public List<TaleTypeDto> findAll(boolean onlyActive) {
    return findAll(EntityTypeEnum.TALE_TYPE, taleTypeMapper, taleTypeDao, onlyActive);
  }

  public TaleTypeDto findById(Long id) {
    return findById(EntityTypeEnum.TALE_TYPE, taleTypeMapper, taleTypeDao, id);
  }

  public TaleTypeDto update(TaleTypeDto dto) {
    return update(EntityTypeEnum.TALE_TYPE, taleTypeMapper, taleTypeDao, dto);
  }
}
