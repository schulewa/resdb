package com.apschulewitz.resdb.refdata.service;

import com.apschulewitz.resdb.common.model.EntityTypeEnum;
import com.apschulewitz.resdb.common.model.entity.VersionStatus;
import com.apschulewitz.resdb.common.utils.LoggingUtils;
import com.apschulewitz.resdb.refdata.model.dao.DeityTypeDao;
import com.apschulewitz.resdb.refdata.model.dto.DeityTypeDto;
import com.apschulewitz.resdb.refdata.model.entity.DeityType;
import com.apschulewitz.resdb.refdata.model.mapper.DeityTypeMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

@Service
@Slf4j
public class DeityTypeService {

  private DeityTypeDao deityTypeDao;

  private DeityTypeMapper deityTypeMapper;

  public DeityTypeService(DeityTypeDao deityTypeDao, DeityTypeMapper deityTypeMapper) {
    this.deityTypeDao = deityTypeDao;
    this.deityTypeMapper = deityTypeMapper;
  }

  public DeityTypeDto add(DeityTypeDto dto) {
    LoggingUtils.logStartOfAddRequest(EntityTypeEnum.DEITY_TYPE, dto);

    DeityType entity = deityTypeMapper.toEntity(dto);
    DeityType saved = deityTypeDao.save(entity);

    LoggingUtils.logAddedEntity(EntityTypeEnum.DEITY_TYPE, saved);

    return deityTypeMapper.toDto(saved);
  }

  public DeityTypeDto deleteById(Long id) {
    LoggingUtils.logAttemptingToMarkEntityAsDeleted(EntityTypeEnum.DEITY_TYPE, id);

    Optional<DeityType> found = deityTypeDao.findById(id);
    if (found.isEmpty()) {
      LoggingUtils.logNoEntityFoundForId(EntityTypeEnum.DEITY_TYPE, id);
      return null;
    }

    if (!VersionStatus.isActive(found.get().getStatus())) {
      LoggingUtils.logUnableToMarkInactiveEntityAsDeleted(EntityTypeEnum.DEITY_TYPE, id);
      return null;
    }

    DeityType toBeDeleted = found.get();
    toBeDeleted.setStatus(VersionStatus.Cancel);
    toBeDeleted.setLastUpdated(LocalDateTime.now());
    // TODO set lastUpdatedBY to current logged in user
    DeityType deleted = deityTypeDao.save(toBeDeleted);

    LoggingUtils.logEntityMarkedAsDeleted(EntityTypeEnum.DEITY_TYPE, id);

    return deityTypeMapper.toDto(deleted);
  }

  public List<DeityTypeDto> findAll(boolean onlyActive) {
    LoggingUtils.logStartOfFindAllRequest(EntityTypeEnum.DEITY_TYPE);
    List<DeityTypeDto> deityTypes = new ArrayList<>();
    Iterable<DeityType> iter;
    if (onlyActive) {
      iter = deityTypeDao.findByStatusIn(VersionStatus.getLiveStatuses());
    } else {
      iter = deityTypeDao.findAll();
    }
    StreamSupport.stream(iter.spliterator(), false)
      .forEach(e -> deityTypes.add(deityTypeMapper.toDto(e)));
      LoggingUtils.logFoundCountEntities(EntityTypeEnum.DEITY_TYPE, onlyActive, deityTypes.size());

    return deityTypes;
  }

  public DeityTypeDto findById(Long id) {
    LoggingUtils.logAttemptingToFindEntityForId(EntityTypeEnum.DEITY_TYPE, id);

    Optional<DeityType> found = deityTypeDao.findById(id);
    if (found.isPresent()) {
      LoggingUtils.logFoundEntityForId(EntityTypeEnum.DEITY_TYPE, id);
      return deityTypeMapper.toDto(found.get());
    }

    LoggingUtils.logFoundNoEntityForId(EntityTypeEnum.DEITY_TYPE, id);

    return null;
  }

  public DeityTypeDto update(DeityTypeDto dto) {
    LoggingUtils.logAttemptingToUpdateEntity(EntityTypeEnum.DEITY_TYPE, dto);

    if (dto.getId() == null) {
      LoggingUtils.logUnableToUpdateEntityWithNoId(EntityTypeEnum.DEITY_TYPE, dto);
      return null;
    }

    dto.setLastUpdated(LocalDateTime.now());
    // TODO set lastUpdatedBy to currrent logged in user
    VersionStatus status = VersionStatus.getInstance(dto.getStatus());
    if (VersionStatus.New.equals(status)) {
      dto.setStatus(VersionStatus.Amend.name());
    }

    DeityType entity = deityTypeMapper.toEntity(dto);
    DeityType saved = deityTypeDao.save(entity);

    DeityTypeDto updated = deityTypeMapper.toDto(saved);

    LoggingUtils.logUpdatedEntity(EntityTypeEnum.DEITY_TYPE, updated);

    return updated;
  }
}
