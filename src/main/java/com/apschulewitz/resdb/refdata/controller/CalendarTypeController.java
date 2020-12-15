package com.apschulewitz.resdb.refdata.controller;

import com.apschulewitz.resdb.common.controller.AbstractController;
import com.apschulewitz.resdb.common.model.EntityTypeEnum;
import com.apschulewitz.resdb.common.utils.LoggingUtils;
import com.apschulewitz.resdb.config.RestUrlPaths;
import com.apschulewitz.resdb.refdata.model.dto.CalendarTypeDto;
import com.apschulewitz.resdb.refdata.service.CalendarTypeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by adrianschulewitz on 22/04/2017.
 */
@RestController()
@Slf4j
public class CalendarTypeController extends AbstractController<CalendarTypeDto, Long> {

  private CalendarTypeService calendarTypeService;

  public CalendarTypeController(CalendarTypeService calendarTypeService) {
    this.calendarTypeService = calendarTypeService;
  }

  @RequestMapping(value = RestUrlPaths.CALENDAR_TYPE_CONTROLLER_BASE_URL, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<List<CalendarTypeDto>> findAll(@RequestBody Boolean onlyActive) {
    LoggingUtils.logStartOfFindAllRequest(EntityTypeEnum.CALENDAR_TYPE);
    List<CalendarTypeDto> calendarTypes = calendarTypeService.findAll(onlyActive);
    LoggingUtils.logEndOfFindAllRequest(EntityTypeEnum.CALENDAR_TYPE);
    return new ResponseEntity<>(calendarTypes, HttpStatus.OK);
  }

//  @Override
//  public ResponseEntity<List<CalendarType>> findAllActive() {
//    logStartOfFindAllActiveRequest(EntityTypeEnum.CALENDAR_TYPE);
//    List<CalendarType> calendarTypes = new ArrayList<>();
//    Iterable<CalendarType> iter = calendarTypeDao.findByStatusIn(VersionStatus.getLiveStatuses());
//    StreamSupport.stream(iter.spliterator(), false)
//      .forEach(calendarTypes::add);
//    logEndOfFindAllActiveRequest(EntityTypeEnum.CALENDAR_TYPE);
//    return new ResponseEntity<>(calendarTypes, HttpStatus.OK);
//  }

  @RequestMapping(value = RestUrlPaths.CALENDAR_TYPE_CONTROLLER_BASE_URL, method = RequestMethod.POST)
  public ResponseEntity<CalendarTypeDto> add(@RequestBody CalendarTypeDto toBeSaved) {
    LoggingUtils.logStartOfAddRequest(EntityTypeEnum.CALENDAR_TYPE, toBeSaved);
    CalendarTypeDto saved = calendarTypeService.add(toBeSaved);
    LoggingUtils.logEndOfAddRequest(EntityTypeEnum.CALENDAR_TYPE, saved);
    return new ResponseEntity<>(saved, HttpStatus.CREATED);
  }

  @RequestMapping(value = RestUrlPaths.CALENDAR_TYPE_CONTROLLER_BASE_URL + "/{id}", method = RequestMethod.DELETE)
  public ResponseEntity<CalendarTypeDto> delete(@PathVariable Long id) {
    LoggingUtils.logStartOfDeleteRequest(EntityTypeEnum.CALENDAR_TYPE, id);
    CalendarTypeDto deleted = calendarTypeService.deleteById(id);
    HttpStatus status;
    if (deleted == null) {
      status = HttpStatus.NOT_FOUND;
    } else {
      status = HttpStatus.OK;
    }
    LoggingUtils.logEndOfDeleteRequest(EntityTypeEnum.CALENDAR_TYPE, deleted);
    return new ResponseEntity<>(deleted, status);
  }

  @RequestMapping(value = RestUrlPaths.CALENDAR_TYPE_CONTROLLER_BASE_URL, method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<CalendarTypeDto> update(@RequestBody CalendarTypeDto toBeSaved) {
    LoggingUtils.logStartOfUpdateRequest(EntityTypeEnum.CALENDAR_TYPE, toBeSaved);
    CalendarTypeDto saved = calendarTypeService.update(toBeSaved);
    LoggingUtils.logEndOfUpdateRequest(EntityTypeEnum.CALENDAR_TYPE, saved);
    return new ResponseEntity<>(saved, HttpStatus.OK);
  }

}
