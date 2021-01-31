package com.apschulewitz.resdb.refdata.service;

import com.apschulewitz.resdb.common.model.EntityTypeEnum;
import com.apschulewitz.resdb.common.service.AbstractService;
import com.apschulewitz.resdb.common.service.DataService;
import com.apschulewitz.resdb.refdata.model.dao.EntityTypeDao;
import com.apschulewitz.resdb.refdata.model.dto.EntityTypeDto;
import com.apschulewitz.resdb.refdata.model.entity.EntityType;
import com.apschulewitz.resdb.refdata.model.mapper.EntityTypeMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class EntityTypeService
  extends AbstractService<EntityTypeDto, EntityType, Long, EntityTypeMapper, EntityTypeDao>
  implements DataService<EntityTypeDto, EntityType, Long> {

  private EntityTypeDao entityTypeDao;

  private EntityTypeMapper entityTypeMapper;

  public EntityTypeService(EntityTypeDao entityTypeDao, EntityTypeMapper entityTypeMapper) {
    this.entityTypeDao = entityTypeDao;
    this.entityTypeMapper = entityTypeMapper;
  }

  public EntityTypeDto add(EntityTypeDto dto) {
    return add(EntityTypeEnum.EVENT_TYPE, entityTypeMapper, entityTypeDao, dto);
  }

  public EntityTypeDto deleteById(Long id) {
    return deleteById(EntityTypeEnum.EVENT_TYPE, entityTypeMapper, entityTypeDao, id);
  }

  public List<EntityTypeDto> findAll(boolean onlyActive) {
    return findAll(EntityTypeEnum.EVENT_TYPE, entityTypeMapper, entityTypeDao, onlyActive);
  }

  public EntityTypeDto findById(Long id) {
    return findById(EntityTypeEnum.EVENT_TYPE, entityTypeMapper, entityTypeDao, id);
  }

  public EntityTypeDto update(EntityTypeDto dto) {
    return update(EntityTypeEnum.EVENT_TYPE, entityTypeMapper, entityTypeDao, dto);
  }
}
