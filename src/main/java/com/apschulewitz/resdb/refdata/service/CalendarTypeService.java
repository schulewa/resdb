package com.apschulewitz.resdb.refdata.service;

import com.apschulewitz.resdb.common.model.EntityTypeEnum;
import com.apschulewitz.resdb.common.service.AbstractService;
import com.apschulewitz.resdb.common.service.DataService;
import com.apschulewitz.resdb.refdata.model.dao.CalendarTypeDao;
import com.apschulewitz.resdb.refdata.model.dto.CalendarTypeDto;
import com.apschulewitz.resdb.refdata.model.entity.CalendarType;
import com.apschulewitz.resdb.refdata.model.mapper.CalendarTypeMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class CalendarTypeService
  extends AbstractService<CalendarTypeDto, CalendarType, Long, CalendarTypeMapper, CalendarTypeDao>
  implements DataService<CalendarTypeDto, CalendarType, Long> {

  private CalendarTypeDao calendarTypeDao;

  private CalendarTypeMapper calendarTypeMapper;

  public CalendarTypeService(CalendarTypeDao calendarTypeDao, CalendarTypeMapper calendarTypeMapper) {
    this.calendarTypeDao = calendarTypeDao;
    this.calendarTypeMapper = calendarTypeMapper;
  }

  public CalendarTypeDto add(CalendarTypeDto dto) {
    return add(EntityTypeEnum.CALENDAR_TYPE, calendarTypeMapper, calendarTypeDao, dto);
  }

  public CalendarTypeDto deleteById(Long id) {
    return deleteById(EntityTypeEnum.CALENDAR_TYPE, calendarTypeMapper, calendarTypeDao, id);
  }

  public List<CalendarTypeDto> findAll(boolean onlyActive) {
    return findAll(EntityTypeEnum.CALENDAR_TYPE, calendarTypeMapper, calendarTypeDao, onlyActive);
  }

  public CalendarTypeDto findById(Long id) {
    return findById(EntityTypeEnum.CALENDAR_TYPE, calendarTypeMapper, calendarTypeDao, id);
  }

  public CalendarTypeDto update(CalendarTypeDto dto) {
    return update(EntityTypeEnum.CALENDAR_TYPE, calendarTypeMapper, calendarTypeDao, dto);
  }
}
