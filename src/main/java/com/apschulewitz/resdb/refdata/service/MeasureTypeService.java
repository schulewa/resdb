package com.apschulewitz.resdb.refdata.service;

import com.apschulewitz.resdb.common.model.EntityTypeEnum;
import com.apschulewitz.resdb.common.service.AbstractService;
import com.apschulewitz.resdb.common.service.DataService;
import com.apschulewitz.resdb.refdata.model.dao.MeasureTypeDao;
import com.apschulewitz.resdb.refdata.model.dto.MeasureTypeDto;
import com.apschulewitz.resdb.refdata.model.entity.MeasureType;
import com.apschulewitz.resdb.refdata.model.mapper.MeasureTypeMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class MeasureTypeService
  extends AbstractService<MeasureTypeDto, MeasureType, Long, MeasureTypeMapper, MeasureTypeDao>
  implements DataService<MeasureTypeDto, MeasureType, Long> {

  private MeasureTypeDao measureTypeDao;

  private MeasureTypeMapper measureTypeMapper;

  public MeasureTypeService(MeasureTypeDao measureTypeDao, MeasureTypeMapper measureTypeMapper) {
    this.measureTypeDao = measureTypeDao;
    this.measureTypeMapper = measureTypeMapper;
  }

  public MeasureTypeDto add(MeasureTypeDto dto) {
    return add(EntityTypeEnum.MEASURE_TYPE, measureTypeMapper, measureTypeDao, dto);
  }

  public MeasureTypeDto deleteById(Long id) {
    return deleteById(EntityTypeEnum.MEASURE_TYPE, measureTypeMapper, measureTypeDao, id);
  }

  public List<MeasureTypeDto> findAll(boolean onlyActive) {
    return findAll(EntityTypeEnum.MEASURE_TYPE, measureTypeMapper, measureTypeDao, onlyActive);
  }

  public MeasureTypeDto findById(Long id) {
    return findById(EntityTypeEnum.MEASURE_TYPE, measureTypeMapper, measureTypeDao, id);
  }

  public MeasureTypeDto update(MeasureTypeDto dto) {
    return update(EntityTypeEnum.MEASURE_TYPE, measureTypeMapper, measureTypeDao, dto);
  }
}
