package com.apschulewitz.resdb.refdata.service;

import com.apschulewitz.resdb.common.model.EntityTypeEnum;
import com.apschulewitz.resdb.common.model.entity.VersionStatus;
import com.apschulewitz.resdb.common.utils.LoggingUtils;
import com.apschulewitz.resdb.refdata.model.dao.RaceTypeDao;
import com.apschulewitz.resdb.refdata.model.dto.RaceTypeDto;
import com.apschulewitz.resdb.refdata.model.entity.RaceType;
import com.apschulewitz.resdb.refdata.model.mapper.RaceTypeMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

@Service
@Slf4j
public class RaceTypeService {

  private RaceTypeDao raceTypeDao;

  private RaceTypeMapper raceTypeMapper;

  public RaceTypeService(RaceTypeDao raceTypeDao, RaceTypeMapper raceTypeMapper) {
    this.raceTypeDao = raceTypeDao;
    this.raceTypeMapper = raceTypeMapper;
  }

  public RaceTypeDto add(RaceTypeDto dto) {
    LoggingUtils.logStartOfAddRequest(EntityTypeEnum.RACE_TYPE, dto);

    RaceType entity = raceTypeMapper.toEntity(dto);
    RaceType saved = raceTypeDao.save(entity);

    LoggingUtils.logAddedEntity(EntityTypeEnum.RACE_TYPE, saved);

    return raceTypeMapper.toDto(saved);
  }

  public RaceTypeDto deleteById(Long id) {
    LoggingUtils.logAttemptingToMarkEntityAsDeleted(EntityTypeEnum.RACE_TYPE, id);

    Optional<RaceType> found = raceTypeDao.findById(id);
    if (found.isEmpty()) {
      LoggingUtils.logNoEntityFoundForId(EntityTypeEnum.RACE_TYPE, id);
      return null;
    }

    if (!VersionStatus.isActive(found.get().getStatus())) {
      LoggingUtils.logUnableToMarkInactiveEntityAsDeleted(EntityTypeEnum.RACE_TYPE, id);
      return null;
    }

    RaceType toBeDeleted = found.get();
    toBeDeleted.setStatus(VersionStatus.Cancel);
    toBeDeleted.setLastUpdated(LocalDateTime.now());
    // TODO set lastUpdatedBY to current logged in user
    RaceType deleted = raceTypeDao.save(toBeDeleted);

    LoggingUtils.logEntityMarkedAsDeleted(EntityTypeEnum.RACE_TYPE, id);

    return raceTypeMapper.toDto(deleted);
  }

  public List<RaceTypeDto> findAll(boolean onlyActive) {
    LoggingUtils.logStartOfFindAllRequest(EntityTypeEnum.RACE_TYPE);
    List<RaceTypeDto> raceTypes = new ArrayList<>();
    Iterable<RaceType> iter;
    if (onlyActive) {
      iter = raceTypeDao.findByStatusIn(VersionStatus.getLiveStatuses());
    } else {
      iter = raceTypeDao.findAll();
    }
    StreamSupport.stream(iter.spliterator(), false)
      .forEach(e -> raceTypes.add(raceTypeMapper.toDto(e)));
      LoggingUtils.logFoundCountEntities(EntityTypeEnum.RACE_TYPE, onlyActive, raceTypes.size());

    return raceTypes;
  }

  public RaceTypeDto findById(Long id) {
    LoggingUtils.logAttemptingToFindEntityForId(EntityTypeEnum.RACE_TYPE, id);

    Optional<RaceType> found = raceTypeDao.findById(id);
    if (found.isPresent()) {
      LoggingUtils.logFoundEntityForId(EntityTypeEnum.RACE_TYPE, id);
      return raceTypeMapper.toDto(found.get());
    }

    LoggingUtils.logFoundNoEntityForId(EntityTypeEnum.RACE_TYPE, id);

    return null;
  }

  public RaceTypeDto update(RaceTypeDto dto) {
    LoggingUtils.logAttemptingToUpdateEntity(EntityTypeEnum.RACE_TYPE, dto);

    if (dto.getId() == null) {
      LoggingUtils.logUnableToUpdateEntityWithNoId(EntityTypeEnum.RACE_TYPE, dto);
      return null;
    }

    dto.setLastUpdated(LocalDateTime.now());
    // TODO set lastUpdatedBy to currrent logged in user
    VersionStatus status = VersionStatus.getInstance(dto.getStatus());
    if (VersionStatus.New.equals(status)) {
      dto.setStatus(VersionStatus.Amend.name());
    }

    RaceType entity = raceTypeMapper.toEntity(dto);
    RaceType saved = raceTypeDao.save(entity);

    RaceTypeDto updated = raceTypeMapper.toDto(saved);

    LoggingUtils.logUpdatedEntity(EntityTypeEnum.RACE_TYPE, updated);

    return updated;
  }
}
