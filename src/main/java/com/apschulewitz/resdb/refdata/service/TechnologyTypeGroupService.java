package com.apschulewitz.resdb.refdata.service;

import com.apschulewitz.resdb.common.model.EntityTypeEnum;
import com.apschulewitz.resdb.common.service.AbstractService;
import com.apschulewitz.resdb.common.service.DataService;
import com.apschulewitz.resdb.refdata.model.dao.TechnologyTypeGroupDao;
import com.apschulewitz.resdb.refdata.model.dto.TechnologyTypeGroupDto;
import com.apschulewitz.resdb.refdata.model.entity.TechnologyTypeGroup;
import com.apschulewitz.resdb.refdata.model.mapper.TechnologyTypeGroupMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class TechnologyTypeGroupService
  extends AbstractService<TechnologyTypeGroupDto, TechnologyTypeGroup, Long, TechnologyTypeGroupMapper, TechnologyTypeGroupDao>
  implements DataService<TechnologyTypeGroupDto, TechnologyTypeGroup, Long> {

  private TechnologyTypeGroupDao technologyTypeGroupDao;

  private TechnologyTypeGroupMapper technologyTypeGroupMapper;

  public TechnologyTypeGroupService(TechnologyTypeGroupDao technologyTypeGroupDao, TechnologyTypeGroupMapper technologyTypeGroupMapper) {
    this.technologyTypeGroupDao = technologyTypeGroupDao;
    this.technologyTypeGroupMapper = technologyTypeGroupMapper;
  }

  public TechnologyTypeGroupDto add(TechnologyTypeGroupDto dto) {
    return add(EntityTypeEnum.TECHNOLOGY_TYPE_GROUP, technologyTypeGroupMapper, technologyTypeGroupDao, dto);
  }

  public TechnologyTypeGroupDto deleteById(Long id) {
    return deleteById(EntityTypeEnum.TECHNOLOGY_TYPE_GROUP, technologyTypeGroupMapper, technologyTypeGroupDao, id);
  }

  public List<TechnologyTypeGroupDto> findAll(boolean onlyActive) {
    return findAll(EntityTypeEnum.TECHNOLOGY_TYPE_GROUP, technologyTypeGroupMapper, technologyTypeGroupDao, onlyActive);
  }

  public TechnologyTypeGroupDto findById(Long id) {
    return findById(EntityTypeEnum.TECHNOLOGY_TYPE_GROUP, technologyTypeGroupMapper, technologyTypeGroupDao, id);
  }

  public TechnologyTypeGroupDto update(TechnologyTypeGroupDto dto) {
    return update(EntityTypeEnum.TECHNOLOGY_TYPE_GROUP, technologyTypeGroupMapper, technologyTypeGroupDao, dto);
  }
}
