package com.apschulewitz.resdb.refdata.service;

import com.apschulewitz.resdb.common.model.EntityTypeEnum;
import com.apschulewitz.resdb.common.service.AbstractService;
import com.apschulewitz.resdb.common.service.DataService;
import com.apschulewitz.resdb.refdata.model.dao.RegionDao;
import com.apschulewitz.resdb.refdata.model.dto.RegionDto;
import com.apschulewitz.resdb.refdata.model.entity.Region;
import com.apschulewitz.resdb.refdata.model.mapper.RegionMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class RegionService extends AbstractService<RegionDto, Region, Long, RegionMapper, RegionDao>
                            implements DataService<RegionDto, Region, Long> {

  private final RegionDao regionDao;

  private final RegionMapper regionMapper;

  public RegionService(RegionDao regionDao, RegionMapper regionMapper) {
    this.regionDao = regionDao;
    this.regionMapper = regionMapper;
  }

  public RegionDto add(RegionDto dto) {
    return add(EntityTypeEnum.REGION, regionMapper, regionDao, dto);
  }

  public RegionDto deleteById(Long id) {
    return deleteById(EntityTypeEnum.REGION, regionMapper, regionDao, id);
  }

  public List<RegionDto> findAll(boolean onlyActive) {
    return findAll(EntityTypeEnum.REGION, regionMapper, regionDao, onlyActive);
  }

  public RegionDto findById(Long id) {
    return findById(EntityTypeEnum.REGION, regionMapper, regionDao, id);
  }

  public RegionDto update(RegionDto dto) {
    return update(EntityTypeEnum.REGION, regionMapper, regionDao, dto);
  }
}
