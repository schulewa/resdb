package com.apschulewitz.resdb.refdata.service;

import com.apschulewitz.resdb.common.model.entity.VersionStatus;
import com.apschulewitz.resdb.refdata.model.dao.CalendarTypeDao;
import com.apschulewitz.resdb.refdata.model.dto.CalendarTypeDto;
import com.apschulewitz.resdb.refdata.model.entity.CalendarType;
import com.apschulewitz.resdb.refdata.model.mapper.CalendarTypeMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

@Service
@Slf4j
public class CalendarTypeService {

  private CalendarTypeDao calendarTypeDao;

  private CalendarTypeMapper calendarTypeMapper;

  public CalendarTypeService(CalendarTypeDao calendarTypeDao, CalendarTypeMapper calendarTypeMapper) {
    this.calendarTypeDao = calendarTypeDao;
    this.calendarTypeMapper = calendarTypeMapper;
  }

  public CalendarTypeDto add(CalendarTypeDto dto) {
    log.info("Adding new calendar type {}", dto);

    if (dto.getId() != null) {
      log.error(String.format("Calendar type ID [{}] is set", dto.getId()));
      return null;
    }

    CalendarType entity = calendarTypeMapper.toEntity(dto);
    CalendarType saved = calendarTypeDao.save(entity);

    log.info("Saved calendar type [{}]", saved);

    return calendarTypeMapper.toDto(saved);
  }

  public CalendarTypeDto deleteById(Long id) {
    log.info("Marking calendar type with id {} as deleted", id);

    Optional<CalendarType> found = calendarTypeDao.findById(id);
    if (found.isEmpty()) {
      log.error("No calenar type entity found for id {}", id);
      return null;
    }

    if (!VersionStatus.isActive(found.get().getStatus())) {
      log.error("Unable to mark an inactive calendar type as deleted");
      return null;
    }

    CalendarType toBeDeleted = found.get();
    toBeDeleted.setStatus(VersionStatus.Cancel);
    toBeDeleted.setLastUpdated(ZonedDateTime.now());
    // TODO set lastUpdatedBY to current logged in user
    CalendarType deleted = calendarTypeDao.save(toBeDeleted);

    log.info("Calendar type with id {} marked as deleted", id);

    return calendarTypeMapper.toDto(deleted);
  }

  public List<CalendarTypeDto> findAll(boolean onlyActive) {
    log.info("Finding all calendar types");
    List<CalendarTypeDto> calendarTypes = new ArrayList<>();
    Iterable<CalendarType> iter;
    if (onlyActive) {
      iter = calendarTypeDao.findByStatusIn(VersionStatus.getLiveStatuses());
    } else {
      iter = calendarTypeDao.findAll();
    }
    StreamSupport.stream(iter.spliterator(), false)
      .forEach(e -> calendarTypes.add(calendarTypeMapper.toDto(e)));
    log.info("Found {} calendar types", calendarTypes.size());
    return calendarTypes;
  }

  public CalendarTypeDto findById(Long id) {
    log.info("Finding calendar type for id {}", id);

    Optional<CalendarType> found = calendarTypeDao.findById(id);
    if (found.isPresent()) {
      log.debug("Found calendar type [{}]", found.get());
      return calendarTypeMapper.toDto(found.get());
    }

    log.error("No calendar type found for id {}", id);

    return null;
  }

  public CalendarTypeDto update(CalendarTypeDto dto) {
    log.info("Updating calendar type [{}]", dto);

    if (dto.getId() == null) {
      log.error("Cannot update a non-existent calendar type");
      return null;
    }

    dto.setLastUpdated(ZonedDateTime.now());
    // TODO set lastUpdatedBy to currrent logged in user
    VersionStatus status = VersionStatus.getInstance(dto.getStatus());
    if (VersionStatus.New.equals(status)) {
      dto.setStatus(VersionStatus.Amend.name());
    }

    CalendarType entity = calendarTypeMapper.toEntity(dto);
    CalendarType saved = calendarTypeDao.save(entity);

    log.info("Saved calendar type [{}]", saved);

    return calendarTypeMapper.toDto(saved);
  }
}
