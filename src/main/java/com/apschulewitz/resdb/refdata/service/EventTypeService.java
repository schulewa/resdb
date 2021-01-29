package com.apschulewitz.resdb.refdata.service;

import com.apschulewitz.resdb.common.model.EntityTypeEnum;
import com.apschulewitz.resdb.common.service.AbstractService;
import com.apschulewitz.resdb.common.service.DataService;
import com.apschulewitz.resdb.refdata.model.dao.EventTypeDao;
import com.apschulewitz.resdb.refdata.model.dto.EventTypeDto;
import com.apschulewitz.resdb.refdata.model.entity.EventType;
import com.apschulewitz.resdb.refdata.model.mapper.EventTypeMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class EventTypeService
  extends AbstractService<EventTypeDto, EventType, Long, EventTypeMapper, EventTypeDao>
  implements DataService<EventTypeDto, EventType, Long> {

  private EventTypeDao eventTypeDao;

  private EventTypeMapper eventTypeMapper;

  public EventTypeService(EventTypeDao eventTypeDao, EventTypeMapper eventTypeMapper) {
    this.eventTypeDao = eventTypeDao;
    this.eventTypeMapper = eventTypeMapper;
  }

  @Override
  public EventTypeDto add(EventTypeDto dto) {
    return add(EntityTypeEnum.EVENT_TYPE, eventTypeMapper, eventTypeDao, dto);
  }

  @Override
  public EventTypeDto deleteById(Long id) {
    return deleteById(EntityTypeEnum.EVENT_TYPE, eventTypeMapper, eventTypeDao, id);
  }

  @Override
  public List<EventTypeDto> findAll(boolean onlyActive) {
    return findAll(EntityTypeEnum.EVENT_TYPE, eventTypeMapper, eventTypeDao, onlyActive);
  }

  public EventTypeDto findById(Long id) {
    return findById(EntityTypeEnum.EVENT_TYPE, eventTypeMapper, eventTypeDao, id);
  }

  @Override
  public EventTypeDto update(EventTypeDto dto) {
    return update(EntityTypeEnum.EVENT_TYPE, eventTypeMapper, eventTypeDao, dto);
  }
}
