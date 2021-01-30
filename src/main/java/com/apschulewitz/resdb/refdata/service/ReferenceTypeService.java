package com.apschulewitz.resdb.refdata.service;

import com.apschulewitz.resdb.common.model.EntityTypeEnum;
import com.apschulewitz.resdb.common.service.AbstractService;
import com.apschulewitz.resdb.common.service.DataService;
import com.apschulewitz.resdb.refdata.model.dao.ReferenceTypeDao;
import com.apschulewitz.resdb.research.model.dto.ReferenceTypeDto;
import com.apschulewitz.resdb.research.model.entity.ReferenceType;
import com.apschulewitz.resdb.research.model.mapper.ReferenceTypeMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class ReferenceTypeService
  extends AbstractService<ReferenceTypeDto, ReferenceType, Long, ReferenceTypeMapper, ReferenceTypeDao>
  implements DataService<ReferenceTypeDto, ReferenceType, Long> {

  private ReferenceTypeDao referenceTypeDao;

  private ReferenceTypeMapper referenceTypeMapper;

  public ReferenceTypeService(ReferenceTypeDao referenceTypeDao, ReferenceTypeMapper referenceTypeMapper) {
    this.referenceTypeDao = referenceTypeDao;
    this.referenceTypeMapper = referenceTypeMapper;
  }

  public ReferenceTypeDto add(ReferenceTypeDto dto) {
    return add(EntityTypeEnum.REFERENCE_TYPE, referenceTypeMapper, referenceTypeDao, dto);
  }

  public ReferenceTypeDto deleteById(Long id) {
    return deleteById(EntityTypeEnum.REFERENCE_TYPE, referenceTypeMapper, referenceTypeDao, id);
  }

  public List<ReferenceTypeDto> findAll(boolean onlyActive) {
    return findAll(EntityTypeEnum.REFERENCE_TYPE, referenceTypeMapper, referenceTypeDao, onlyActive);
  }

  public ReferenceTypeDto findById(Long id) {
    return findById(EntityTypeEnum.REFERENCE_TYPE, referenceTypeMapper, referenceTypeDao, id);
  }

  public ReferenceTypeDto update(ReferenceTypeDto dto) {
    return update(EntityTypeEnum.REFERENCE_TYPE, referenceTypeMapper, referenceTypeDao, dto);
  }
}
