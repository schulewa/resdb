package com.apschulewitz.resdb.refdata.service;

import com.apschulewitz.resdb.common.model.EntityTypeEnum;
import com.apschulewitz.resdb.common.model.entity.VersionStatus;
import com.apschulewitz.resdb.common.utils.LoggingUtils;
import com.apschulewitz.resdb.refdata.model.dao.DeityTypeDao;
import com.apschulewitz.resdb.refdata.model.dao.EntityTypeDao;
import com.apschulewitz.resdb.refdata.model.dto.DeityTypeDto;
import com.apschulewitz.resdb.refdata.model.dto.EntityTypeDto;
import com.apschulewitz.resdb.refdata.model.entity.DeityType;
import com.apschulewitz.resdb.refdata.model.entity.EntityType;
import com.apschulewitz.resdb.refdata.model.mapper.DeityTypeMapper;
import com.apschulewitz.resdb.refdata.model.mapper.EntityTypeMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

@Service
@Slf4j
public class EntityTypeService {

  private EntityTypeDao entityTypeDao;

  private EntityTypeMapper entityTypeMapper;

  public EntityTypeService(EntityTypeDao entityTypeDao, EntityTypeMapper entityTypeMapper) {
    this.entityTypeDao = entityTypeDao;
    this.entityTypeMapper = entityTypeMapper;
  }

  public EntityTypeDto add(EntityTypeDto dto) {
    LoggingUtils.logStartOfAddRequest(EntityTypeEnum.DEITY_TYPE, dto);

    EntityType entity = entityTypeMapper.toEntity(dto);
    EntityType saved = entityTypeDao.save(entity);

    LoggingUtils.logAddedEntity(EntityTypeEnum.ENTITY_TYPE, saved);

    return entityTypeMapper.toDto(saved);
  }

  public EntityTypeDto deleteById(Long id) {
    LoggingUtils.logAttemptingToMarkEntityAsDeleted(EntityTypeEnum.ENTITY_TYPE, id);

    Optional<EntityType> found = entityTypeDao.findById(id);
    if (found.isEmpty()) {
      LoggingUtils.logNoEntityFoundForId(EntityTypeEnum.ENTITY_TYPE, id);
      return null;
    }

    if (!VersionStatus.isActive(found.get().getStatus())) {
      LoggingUtils.logUnableToMarkInactiveEntityAsDeleted(EntityTypeEnum.ENTITY_TYPE, id);
      return null;
    }

    EntityType toBeDeleted = found.get();
    toBeDeleted.setStatus(VersionStatus.Cancel);
    toBeDeleted.setLastUpdated(LocalDateTime.now());
    // TODO set lastUpdatedBY to current logged in user
    EntityType deleted = entityTypeDao.save(toBeDeleted);

    LoggingUtils.logEntityMarkedAsDeleted(EntityTypeEnum.ENTITY_TYPE, id);

    return entityTypeMapper.toDto(deleted);
  }

  public List<EntityTypeDto> findAll(boolean onlyActive) {
    LoggingUtils.logStartOfFindAllRequest(EntityTypeEnum.ENTITY_TYPE);
    List<EntityTypeDto> entityTypes = new ArrayList<>();
    Iterable<EntityType> iter;
    if (onlyActive) {
      iter = entityTypeDao.findByStatusIn(VersionStatus.getLiveStatuses());
    } else {
      iter = entityTypeDao.findAll();
    }
    StreamSupport.stream(iter.spliterator(), false)
      .forEach(e -> entityTypes.add(entityTypeMapper.toDto(e)));
      LoggingUtils.logFoundCountEntities(EntityTypeEnum.ENTITY_TYPE, onlyActive, entityTypes.size());

    return entityTypes;
  }

  public EntityTypeDto findById(Long id) {
    LoggingUtils.logAttemptingToFindEntityForId(EntityTypeEnum.ENTITY_TYPE, id);

    Optional<EntityType> found = entityTypeDao.findById(id);
    if (found.isPresent()) {
      LoggingUtils.logFoundEntityForId(EntityTypeEnum.ENTITY_TYPE, id);
      return entityTypeMapper.toDto(found.get());
    }

    LoggingUtils.logFoundNoEntityForId(EntityTypeEnum.ENTITY_TYPE, id);

    return null;
  }

  public EntityTypeDto update(EntityTypeDto dto) {
    LoggingUtils.logAttemptingToUpdateEntity(EntityTypeEnum.ENTITY_TYPE, dto);

    if (dto.getId() == null) {
      LoggingUtils.logUnableToUpdateEntityWithNoId(EntityTypeEnum.ENTITY_TYPE, dto);
      return null;
    }

    dto.setLastUpdated(LocalDateTime.now());
    // TODO set lastUpdatedBy to currrent logged in user
    VersionStatus status = VersionStatus.getInstance(dto.getStatus());
    if (VersionStatus.New.equals(status)) {
      dto.setStatus(VersionStatus.Amend.name());
    }

    EntityType entity = entityTypeMapper.toEntity(dto);
    EntityType saved = entityTypeDao.save(entity);

    EntityTypeDto updated = entityTypeMapper.toDto(saved);

    LoggingUtils.logUpdatedEntity(EntityTypeEnum.ENTITY_TYPE, updated);

    return updated;
  }
}
