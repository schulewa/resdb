package com.apschulewitz.resdb.refdata.service;

import com.apschulewitz.resdb.common.model.EntityTypeEnum;
import com.apschulewitz.resdb.common.model.entity.VersionStatus;
import com.apschulewitz.resdb.common.utils.LoggingUtils;
import com.apschulewitz.resdb.refdata.model.dao.DeityTypeDao;
import com.apschulewitz.resdb.refdata.model.dao.EventTypeGroupDao;
import com.apschulewitz.resdb.refdata.model.dto.DeityTypeDto;
import com.apschulewitz.resdb.refdata.model.dto.EventTypeGroupDto;
import com.apschulewitz.resdb.refdata.model.entity.DeityType;
import com.apschulewitz.resdb.refdata.model.entity.EventTypeGroup;
import com.apschulewitz.resdb.refdata.model.mapper.DeityTypeMapper;
import com.apschulewitz.resdb.refdata.model.mapper.EventTypeGroupMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

@Service
@Slf4j
public class EventTypeGroupService {

  private EventTypeGroupDao eventTypeGroupDao;

  private EventTypeGroupMapper eventTypeGroupMapper;

  public EventTypeGroupService(EventTypeGroupDao eventTypeGroupDao, EventTypeGroupMapper eventTypeGroupMapper) {
    this.eventTypeGroupDao = eventTypeGroupDao;
    this.eventTypeGroupMapper = eventTypeGroupMapper;
  }

  public EventTypeGroupDto add(EventTypeGroupDto dto) {
    LoggingUtils.logStartOfAddRequest(EntityTypeEnum.EVENT_TYPE_GROUP, dto);

    EventTypeGroup entity = eventTypeGroupMapper.toEntity(dto);
    EventTypeGroup saved = eventTypeGroupDao.save(entity);

    LoggingUtils.logAddedEntity(EntityTypeEnum.EVENT_TYPE_GROUP, saved);

    return eventTypeGroupMapper.toDto(saved);
  }

  public EventTypeGroupDto deleteById(Long id) {
    LoggingUtils.logAttemptingToMarkEntityAsDeleted(EntityTypeEnum.EVENT_TYPE_GROUP, id);

    Optional<EventTypeGroup> found = eventTypeGroupDao.findById(id);
    if (found.isEmpty()) {
      LoggingUtils.logNoEntityFoundForId(EntityTypeEnum.EVENT_TYPE_GROUP, id);
      return null;
    }

    if (!VersionStatus.isActive(found.get().getStatus())) {
      LoggingUtils.logUnableToMarkInactiveEntityAsDeleted(EntityTypeEnum.EVENT_TYPE_GROUP, id);
      return null;
    }

    EventTypeGroup toBeDeleted = found.get();
    toBeDeleted.setStatus(VersionStatus.Cancel);
    toBeDeleted.setLastUpdated(LocalDateTime.now());
    // TODO set lastUpdatedBY to current logged in user
    EventTypeGroup deleted = eventTypeGroupDao.save(toBeDeleted);

    LoggingUtils.logEntityMarkedAsDeleted(EntityTypeEnum.EVENT_TYPE_GROUP, id);

    return eventTypeGroupMapper.toDto(deleted);
  }

  public List<EventTypeGroupDto> findAll(boolean onlyActive) {
    LoggingUtils.logStartOfFindAllRequest(EntityTypeEnum.EVENT_TYPE_GROUP);
    List<EventTypeGroupDto> eventTypeGroups = new ArrayList<>();
    Iterable<EventTypeGroup> iter;
    if (onlyActive) {
      iter = eventTypeGroupDao.findByStatusIn(VersionStatus.getLiveStatuses());
    } else {
      iter = eventTypeGroupDao.findAll();
    }
    StreamSupport.stream(iter.spliterator(), false)
      .forEach(e -> eventTypeGroups.add(eventTypeGroupMapper.toDto(e)));
      LoggingUtils.logFoundCountEntities(EntityTypeEnum.EVENT_TYPE_GROUP, onlyActive, eventTypeGroups.size());

    return eventTypeGroups;
  }

  public EventTypeGroupDto findById(Long id) {
    LoggingUtils.logAttemptingToFindEntityForId(EntityTypeEnum.EVENT_TYPE_GROUP, id);

    Optional<EventTypeGroup> found = eventTypeGroupDao.findById(id);
    if (found.isPresent()) {
      LoggingUtils.logFoundEntityForId(EntityTypeEnum.EVENT_TYPE_GROUP, id);
      return eventTypeGroupMapper.toDto(found.get());
    }

    LoggingUtils.logFoundNoEntityForId(EntityTypeEnum.EVENT_TYPE_GROUP, id);

    return null;
  }

  public EventTypeGroupDto update(EventTypeGroupDto dto) {
    LoggingUtils.logAttemptingToUpdateEntity(EntityTypeEnum.EVENT_TYPE_GROUP, dto);

    if (dto.getId() == null) {
      LoggingUtils.logUnableToUpdateEntityWithNoId(EntityTypeEnum.EVENT_TYPE_GROUP, dto);
      return null;
    }

    dto.setLastUpdated(LocalDateTime.now());
    // TODO set lastUpdatedBy to currrent logged in user
    VersionStatus status = VersionStatus.getInstance(dto.getStatus());
    if (VersionStatus.New.equals(status)) {
      dto.setStatus(VersionStatus.Amend.name());
    }

    EventTypeGroup entity = eventTypeGroupMapper.toEntity(dto);
    EventTypeGroup saved = eventTypeGroupDao.save(entity);

    EventTypeGroupDto updated = eventTypeGroupMapper.toDto(saved);

    LoggingUtils.logUpdatedEntity(EntityTypeEnum.EVENT_TYPE_GROUP, updated);

    return updated;
  }
}
