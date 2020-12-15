package com.apschulewitz.resdb.refdata.service;

import com.apschulewitz.resdb.common.model.EntityTypeEnum;
import com.apschulewitz.resdb.common.model.entity.VersionStatus;
import com.apschulewitz.resdb.common.utils.LoggingUtils;
import com.apschulewitz.resdb.refdata.model.dao.HierarchyTypeDao;
import com.apschulewitz.resdb.refdata.model.dto.HierarchyTypeDto;
import com.apschulewitz.resdb.refdata.model.entity.HierarchyType;
import com.apschulewitz.resdb.refdata.model.mapper.HierarchyTypeMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

@Service
@Slf4j
public class HierarchyTypeService {

  private HierarchyTypeDao hierarchyTypeDao;

  private HierarchyTypeMapper hierarchyTypeMapper;

  public HierarchyTypeService(HierarchyTypeDao hierarchyTypeDao, HierarchyTypeMapper hierarchyTypeMapper) {
    this.hierarchyTypeDao = hierarchyTypeDao;
    this.hierarchyTypeMapper = hierarchyTypeMapper;
  }

  public HierarchyTypeDto add(HierarchyTypeDto dto) {
    LoggingUtils.logStartOfAddRequest(EntityTypeEnum.HIERARCHY_TYPE, dto);

    HierarchyType entity = hierarchyTypeMapper.toEntity(dto);
    HierarchyType saved = hierarchyTypeDao.save(entity);

    LoggingUtils.logAddedEntity(EntityTypeEnum.HIERARCHY_TYPE, saved);

    return hierarchyTypeMapper.toDto(saved);
  }

  public HierarchyTypeDto deleteById(Long id) {
    LoggingUtils.logAttemptingToMarkEntityAsDeleted(EntityTypeEnum.HIERARCHY_TYPE, id);

    Optional<HierarchyType> found = hierarchyTypeDao.findById(id);
    if (found.isEmpty()) {
      LoggingUtils.logNoEntityFoundForId(EntityTypeEnum.HIERARCHY_TYPE, id);
      return null;
    }

    if (!VersionStatus.isActive(found.get().getStatus())) {
      LoggingUtils.logUnableToMarkInactiveEntityAsDeleted(EntityTypeEnum.HIERARCHY_TYPE, id);
      return null;
    }

    HierarchyType toBeDeleted = found.get();
    toBeDeleted.setStatus(VersionStatus.Cancel);
    toBeDeleted.setLastUpdated(LocalDateTime.now());
    // TODO set lastUpdatedBY to current logged in user
    HierarchyType deleted = hierarchyTypeDao.save(toBeDeleted);

    LoggingUtils.logEntityMarkedAsDeleted(EntityTypeEnum.HIERARCHY_TYPE, id);

    return hierarchyTypeMapper.toDto(deleted);
  }

  public List<HierarchyTypeDto> findAll(boolean onlyActive) {
    LoggingUtils.logStartOfFindAllRequest(EntityTypeEnum.HIERARCHY_TYPE);
    List<HierarchyTypeDto> hierarchyTypes = new ArrayList<>();
    Iterable<HierarchyType> iter;
    if (onlyActive) {
      iter = hierarchyTypeDao.findByStatusIn(VersionStatus.getLiveStatuses());
    } else {
      iter = hierarchyTypeDao.findAll();
    }
    StreamSupport.stream(iter.spliterator(), false)
      .forEach(e -> hierarchyTypes.add(hierarchyTypeMapper.toDto(e)));
      LoggingUtils.logFoundCountEntities(EntityTypeEnum.HIERARCHY_TYPE, onlyActive, hierarchyTypes.size());

    return hierarchyTypes;
  }

  public HierarchyTypeDto findById(Long id) {
    LoggingUtils.logAttemptingToFindEntityForId(EntityTypeEnum.HIERARCHY_TYPE, id);

    Optional<HierarchyType> found = hierarchyTypeDao.findById(id);
    if (found.isPresent()) {
      LoggingUtils.logFoundEntityForId(EntityTypeEnum.HIERARCHY_TYPE, id);
      return hierarchyTypeMapper.toDto(found.get());
    }

    LoggingUtils.logFoundNoEntityForId(EntityTypeEnum.HIERARCHY_TYPE, id);

    return null;
  }

  public HierarchyTypeDto update(HierarchyTypeDto dto) {
    LoggingUtils.logAttemptingToUpdateEntity(EntityTypeEnum.HIERARCHY_TYPE, dto);

    if (dto.getId() == null) {
      LoggingUtils.logUnableToUpdateEntityWithNoId(EntityTypeEnum.HIERARCHY_TYPE, dto);
      return null;
    }

    dto.setLastUpdated(LocalDateTime.now());
    // TODO set lastUpdatedBy to currrent logged in user
    VersionStatus status = VersionStatus.getInstance(dto.getStatus());
    if (VersionStatus.New.equals(status)) {
      dto.setStatus(VersionStatus.Amend.name());
    }

    HierarchyType entity = hierarchyTypeMapper.toEntity(dto);
    HierarchyType saved = hierarchyTypeDao.save(entity);

    HierarchyTypeDto updated = hierarchyTypeMapper.toDto(saved);

    LoggingUtils.logUpdatedEntity(EntityTypeEnum.HIERARCHY_TYPE, updated);

    return updated;
  }
}
