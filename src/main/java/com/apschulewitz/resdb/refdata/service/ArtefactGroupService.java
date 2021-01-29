package com.apschulewitz.resdb.refdata.service;

import com.apschulewitz.resdb.common.model.EntityTypeEnum;
import com.apschulewitz.resdb.common.service.AbstractService;
import com.apschulewitz.resdb.common.service.DataService;
import com.apschulewitz.resdb.refdata.model.dao.ArtefactGroupDao;
import com.apschulewitz.resdb.refdata.model.dto.ArtefactGroupDto;
import com.apschulewitz.resdb.refdata.model.entity.ArtefactGroup;
import com.apschulewitz.resdb.refdata.model.mapper.ArtefactGroupMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class ArtefactGroupService
  extends AbstractService<ArtefactGroupDto, ArtefactGroup, Long, ArtefactGroupMapper, ArtefactGroupDao>
  implements DataService<ArtefactGroupDto, ArtefactGroup, Long> {

  private ArtefactGroupDao artefactGroupDao;

  private ArtefactGroupMapper artefactGroupMapper;

  public ArtefactGroupService(ArtefactGroupDao artefactGroupDao, ArtefactGroupMapper artefactGroupMapper) {
    this.artefactGroupDao = artefactGroupDao;
    this.artefactGroupMapper = artefactGroupMapper;
  }

  public ArtefactGroupDto add(ArtefactGroupDto dto) {
    return add(EntityTypeEnum.ARTEFACT_GROUP, artefactGroupMapper, artefactGroupDao, dto);
  }

  public ArtefactGroupDto deleteById(Long id) {
    return deleteById(EntityTypeEnum.ARTEFACT_GROUP, artefactGroupMapper, artefactGroupDao, id);
  }

  public List<ArtefactGroupDto> findAll(boolean onlyActive) {
    return findAll(EntityTypeEnum.ARTEFACT_GROUP, artefactGroupMapper, artefactGroupDao, onlyActive);
  }

  public ArtefactGroupDto findById(Long id) {
    return findById(EntityTypeEnum.ARTEFACT_GROUP, artefactGroupMapper, artefactGroupDao, id);
  }

  public ArtefactGroupDto update(ArtefactGroupDto dto) {
    return update(EntityTypeEnum.ARTEFACT_GROUP, artefactGroupMapper, artefactGroupDao, dto);
  }
}
