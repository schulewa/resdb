package com.apschulewitz.resdb.refdata.service;

import com.apschulewitz.resdb.common.model.EntityTypeEnum;
import com.apschulewitz.resdb.common.model.entity.VersionStatus;
import com.apschulewitz.resdb.common.utils.LoggingUtils;
import com.apschulewitz.resdb.refdata.model.dao.MeasureTypeDao;
import com.apschulewitz.resdb.refdata.model.dto.MeasureTypeDto;
import com.apschulewitz.resdb.refdata.model.entity.MeasureType;
import com.apschulewitz.resdb.refdata.model.mapper.MeasureTypeMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

@Service
@Slf4j
public class MeasureTypeService {

  private MeasureTypeDao measureTypeDao;

  private MeasureTypeMapper measureTypeMapper;

  public MeasureTypeService(MeasureTypeDao measureTypeDao, MeasureTypeMapper measureTypeMapper) {
    this.measureTypeDao = measureTypeDao;
    this.measureTypeMapper = measureTypeMapper;
  }

  public MeasureTypeDto add(MeasureTypeDto dto) {
    LoggingUtils.logStartOfAddRequest(EntityTypeEnum.MEASURE_TYPE, dto);

    MeasureType entity = measureTypeMapper.toEntity(dto);
    MeasureType saved = measureTypeDao.save(entity);

    LoggingUtils.logAddedEntity(EntityTypeEnum.MEASURE_TYPE, saved);

    return measureTypeMapper.toDto(saved);
  }

  public MeasureTypeDto deleteById(Long id) {
    LoggingUtils.logAttemptingToMarkEntityAsDeleted(EntityTypeEnum.MEASURE_TYPE, id);

    Optional<MeasureType> found = measureTypeDao.findById(id);
    if (found.isEmpty()) {
      LoggingUtils.logNoEntityFoundForId(EntityTypeEnum.MEASURE_TYPE, id);
      return null;
    }

    if (!VersionStatus.isActive(found.get().getStatus())) {
      LoggingUtils.logUnableToMarkInactiveEntityAsDeleted(EntityTypeEnum.MEASURE_TYPE, id);
      return null;
    }

    MeasureType toBeDeleted = found.get();
    toBeDeleted.setStatus(VersionStatus.Cancel);
    toBeDeleted.setLastUpdated(LocalDateTime.now());
    // TODO set lastUpdatedBY to current logged in user
    MeasureType deleted = measureTypeDao.save(toBeDeleted);

    LoggingUtils.logEntityMarkedAsDeleted(EntityTypeEnum.MEASURE_TYPE, id);

    return measureTypeMapper.toDto(deleted);
  }

  public List<MeasureTypeDto> findAll(boolean onlyActive) {
    LoggingUtils.logStartOfFindAllRequest(EntityTypeEnum.MEASURE_TYPE);
    List<MeasureTypeDto> measureTypes = new ArrayList<>();
    Iterable<MeasureType> iter;
    if (onlyActive) {
      iter = measureTypeDao.findByStatusIn(VersionStatus.getLiveStatuses());
    } else {
      iter = measureTypeDao.findAll();
    }
    StreamSupport.stream(iter.spliterator(), false)
      .forEach(e -> measureTypes.add(measureTypeMapper.toDto(e)));
      LoggingUtils.logFoundCountEntities(EntityTypeEnum.MEASURE_TYPE, onlyActive, measureTypes.size());

    return measureTypes;
  }

  public MeasureTypeDto findById(Long id) {
    LoggingUtils.logAttemptingToFindEntityForId(EntityTypeEnum.MEASURE_TYPE, id);

    Optional<MeasureType> found = measureTypeDao.findById(id);
    if (found.isPresent()) {
      LoggingUtils.logFoundEntityForId(EntityTypeEnum.MEASURE_TYPE, id);
      return measureTypeMapper.toDto(found.get());
    }

    LoggingUtils.logFoundNoEntityForId(EntityTypeEnum.MEASURE_TYPE, id);

    return null;
  }

  public MeasureTypeDto update(MeasureTypeDto dto) {
    LoggingUtils.logAttemptingToUpdateEntity(EntityTypeEnum.MEASURE_TYPE, dto);

    if (dto.getId() == null) {
      LoggingUtils.logUnableToUpdateEntityWithNoId(EntityTypeEnum.MEASURE_TYPE, dto);
      return null;
    }

    dto.setLastUpdated(LocalDateTime.now());
    // TODO set lastUpdatedBy to currrent logged in user
    VersionStatus status = VersionStatus.getInstance(dto.getStatus());
    if (VersionStatus.New.equals(status)) {
      dto.setStatus(VersionStatus.Amend.name());
    }

    MeasureType entity = measureTypeMapper.toEntity(dto);
    MeasureType saved = measureTypeDao.save(entity);

    MeasureTypeDto updated = measureTypeMapper.toDto(saved);

    LoggingUtils.logUpdatedEntity(EntityTypeEnum.MEASURE_TYPE, updated);

    return updated;
  }
}
