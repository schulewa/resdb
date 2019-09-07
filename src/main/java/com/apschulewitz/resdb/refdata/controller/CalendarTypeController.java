package com.apschulewitz.resdb.refdata.controller;

import com.apschulewitz.resdb.common.controller.AbstractController;
import com.apschulewitz.resdb.common.model.entity.VersionStatus;
import com.apschulewitz.resdb.config.RestUrlPaths;
import com.apschulewitz.resdb.refdata.model.dao.CalendarTypeDao;
import com.apschulewitz.resdb.refdata.model.entity.CalendarType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

/**
 * Created by adrianschulewitz on 22/04/2017.
 */
@RestController()
@Slf4j
public class CalendarTypeController extends AbstractController<CalendarType, Long> {

  private CalendarTypeDao calendarTypeDao;

  public CalendarTypeController(CalendarTypeDao calendarTypeDao) {
    this.calendarTypeDao = calendarTypeDao;
  }

  @RequestMapping(value = RestUrlPaths.CALENDAR_TYPE_CONTROLLER_BASE_URL, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<List<CalendarType>> findAll() {

    List<CalendarType> calendarTypes = new ArrayList<>();
    Iterable<CalendarType> iter = calendarTypeDao.findByStatusIn(VersionStatus.getLiveStatuses());
    StreamSupport.stream(iter.spliterator(), false)
      .forEach(calendarTypes::add);
    log.info("findAll: {} calendar types found", calendarTypes.size());
    return new ResponseEntity<>(calendarTypes, HttpStatus.OK);
  }

  @RequestMapping(value = RestUrlPaths.CALENDAR_TYPE_CONTROLLER_BASE_URL, method = RequestMethod.POST)
  public ResponseEntity<CalendarType> add(HttpServletRequest request, @RequestBody CalendarType toBeSaved) {
    log.info("Save new calendar type: {}", toBeSaved);
    CalendarType saved = calendarTypeDao.save(toBeSaved);
    return new ResponseEntity<>(saved, HttpStatus.CREATED);
  }

  @RequestMapping(value = RestUrlPaths.CALENDAR_TYPE_CONTROLLER_BASE_URL + "/{id}", method = RequestMethod.DELETE)
  public ResponseEntity<CalendarType> delete(@PathVariable long id) {
    log.info("Marking calendar type [{}] for deletion", id);
    Optional<CalendarType> existing = calendarTypeDao.findById(id);

    if (existing.isEmpty()) {
      log.error("No existing calendar type found for id {} - unable to mark for deletion", id);
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    existing.get().setStatus(VersionStatus.Cancel);
    CalendarType saved = calendarTypeDao.save(existing.get());
    return new ResponseEntity<>(saved, HttpStatus.OK);
  }

  @RequestMapping(value = RestUrlPaths.CALENDAR_TYPE_CONTROLLER_BASE_URL, method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<CalendarType> update(@RequestBody CalendarType toBeSaved) {
    log.info("Update existing calendar type: {}", toBeSaved);
    Optional<CalendarType> existing = calendarTypeDao.findById(toBeSaved.getId());

    if (existing.isEmpty()) {
      log.error("No existing calendar type found for id {} - update aborted for: {}", toBeSaved.getId(), toBeSaved);
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    CalendarType saved = calendarTypeDao.save(toBeSaved);
    return new ResponseEntity<>(saved, HttpStatus.OK);
  }

}
