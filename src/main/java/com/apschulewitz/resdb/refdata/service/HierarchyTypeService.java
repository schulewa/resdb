package com.apschulewitz.resdb.refdata.service;

import com.apschulewitz.resdb.common.model.EntityTypeEnum;
import com.apschulewitz.resdb.common.service.AbstractService;
import com.apschulewitz.resdb.common.service.DataService;
import com.apschulewitz.resdb.refdata.model.dao.HierarchyTypeDao;
import com.apschulewitz.resdb.refdata.model.dto.HierarchyTypeDto;
import com.apschulewitz.resdb.refdata.model.entity.HierarchyType;
import com.apschulewitz.resdb.refdata.model.mapper.HierarchyTypeMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class HierarchyTypeService
  extends AbstractService<HierarchyTypeDto, HierarchyType, Long, HierarchyTypeMapper, HierarchyTypeDao>
  implements DataService<HierarchyTypeDto, HierarchyType, Long> {

  private HierarchyTypeDao hierarchyTypeDao;

  private HierarchyTypeMapper hierarchyTypeMapper;

  public HierarchyTypeService(HierarchyTypeDao hierarchyTypeDao, HierarchyTypeMapper hierarchyTypeMapper) {
    this.hierarchyTypeDao = hierarchyTypeDao;
    this.hierarchyTypeMapper = hierarchyTypeMapper;
  }

  public HierarchyTypeDto add(HierarchyTypeDto dto) {
    return add(EntityTypeEnum.HIERARCHY_TYPE, hierarchyTypeMapper, hierarchyTypeDao, dto);
  }

  public HierarchyTypeDto deleteById(Long id) {
    return deleteById(EntityTypeEnum.HIERARCHY_TYPE, hierarchyTypeMapper, hierarchyTypeDao, id);
  }

  public List<HierarchyTypeDto> findAll(boolean onlyActive) {
    return findAll(EntityTypeEnum.HIERARCHY_TYPE, hierarchyTypeMapper, hierarchyTypeDao, onlyActive);
  }

  public HierarchyTypeDto findById(Long id) {
    return findById(EntityTypeEnum.HIERARCHY_TYPE, hierarchyTypeMapper, hierarchyTypeDao, id);
  }

  public HierarchyTypeDto update(HierarchyTypeDto dto) {
    return update(EntityTypeEnum.HIERARCHY_TYPE, hierarchyTypeMapper, hierarchyTypeDao, dto);
  }
}
