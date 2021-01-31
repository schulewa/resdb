package com.apschulewitz.resdb.refdata.service;

import com.apschulewitz.resdb.common.model.EntityTypeEnum;
import com.apschulewitz.resdb.common.service.AbstractService;
import com.apschulewitz.resdb.common.service.DataService;
import com.apschulewitz.resdb.refdata.model.dao.ArtefactTypeDao;
import com.apschulewitz.resdb.refdata.model.dto.ArtefactTypeDto;
import com.apschulewitz.resdb.refdata.model.entity.ArtefactType;
import com.apschulewitz.resdb.refdata.model.mapper.ArtefactTypeMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class ArtefactTypeService
  extends AbstractService<ArtefactTypeDto, ArtefactType, Long, ArtefactTypeMapper, ArtefactTypeDao>
  implements DataService<ArtefactTypeDto, ArtefactType, Long> {

  private ArtefactTypeDao artefactTypeDao;

  private ArtefactTypeMapper artefactTypeMapper;

  public ArtefactTypeService(ArtefactTypeDao artefactTypeDao, ArtefactTypeMapper artefactTypeMapper) {
    this.artefactTypeDao = artefactTypeDao;
    this.artefactTypeMapper = artefactTypeMapper;
  }

  public ArtefactTypeDto add(ArtefactTypeDto dto) {
    return add(EntityTypeEnum.ARTEFACT_TYPE, artefactTypeMapper, artefactTypeDao, dto);
  }

  public ArtefactTypeDto deleteById(Long id) {
    return deleteById(EntityTypeEnum.ARTEFACT_TYPE, artefactTypeMapper, artefactTypeDao, id);
  }

  public List<ArtefactTypeDto> findAll(boolean onlyActive) {
    return findAll(EntityTypeEnum.ARTEFACT_TYPE, artefactTypeMapper, artefactTypeDao, onlyActive);
  }

  public ArtefactTypeDto findById(Long id) {
    return findById(EntityTypeEnum.ARTEFACT_TYPE, artefactTypeMapper, artefactTypeDao, id);
  }

  public ArtefactTypeDto update(ArtefactTypeDto dto) {
    return update(EntityTypeEnum.ARTEFACT_TYPE, artefactTypeMapper, artefactTypeDao, dto);
  }
}
