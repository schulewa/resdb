package com.apschulewitz.resdb.refdata.service;

import com.apschulewitz.resdb.common.model.EntityTypeEnum;
import com.apschulewitz.resdb.common.service.AbstractService;
import com.apschulewitz.resdb.common.service.DataService;
import com.apschulewitz.resdb.refdata.model.dao.EventTypeGroupDao;
import com.apschulewitz.resdb.refdata.model.dto.EventTypeGroupDto;
import com.apschulewitz.resdb.refdata.model.entity.EventTypeGroup;
import com.apschulewitz.resdb.refdata.model.mapper.EventTypeGroupMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class EventTypeGroupService
  extends AbstractService<EventTypeGroupDto, EventTypeGroup, Long, EventTypeGroupMapper, EventTypeGroupDao>
  implements DataService<EventTypeGroupDto, EventTypeGroup, Long> {

  private EventTypeGroupDao eventTypeGroupDao;

  private EventTypeGroupMapper eventTypeGroupMapper;

  public EventTypeGroupService(EventTypeGroupDao eventTypeGroupDao, EventTypeGroupMapper eventTypeGroupMapper) {
    this.eventTypeGroupDao = eventTypeGroupDao;
    this.eventTypeGroupMapper = eventTypeGroupMapper;
  }

  public EventTypeGroupDto add(EventTypeGroupDto dto) {
    return add(EntityTypeEnum.EVENT_TYPE_GROUP, eventTypeGroupMapper, eventTypeGroupDao, dto);
  }

  public EventTypeGroupDto deleteById(Long id) {
    return deleteById(EntityTypeEnum.EVENT_TYPE_GROUP, eventTypeGroupMapper, eventTypeGroupDao, id);
  }

  public List<EventTypeGroupDto> findAll(boolean onlyActive) {
    return findAll(EntityTypeEnum.EVENT_TYPE_GROUP, eventTypeGroupMapper, eventTypeGroupDao, onlyActive);
  }

  public EventTypeGroupDto findById(Long id) {
    return findById(EntityTypeEnum.EVENT_TYPE_GROUP, eventTypeGroupMapper, eventTypeGroupDao, id);
  }

  public EventTypeGroupDto update(EventTypeGroupDto dto) {
    return update(EntityTypeEnum.EVENT_TYPE_GROUP, eventTypeGroupMapper, eventTypeGroupDao, dto);
  }
}
