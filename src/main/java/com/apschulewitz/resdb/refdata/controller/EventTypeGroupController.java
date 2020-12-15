package com.apschulewitz.resdb.refdata.controller;

import com.apschulewitz.resdb.common.controller.AbstractController;
import com.apschulewitz.resdb.common.model.EntityTypeEnum;
import com.apschulewitz.resdb.config.RestUrlPaths;
import com.apschulewitz.resdb.refdata.model.dto.EventTypeGroupDto;
import com.apschulewitz.resdb.refdata.service.EventTypeGroupService;
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
public class EventTypeGroupController extends AbstractController<EventTypeGroupDto, Long> {

  private EventTypeGroupService eventTypeGroupService;

  public EventTypeGroupController(EventTypeGroupService eventTypeGroupService) {
    this.eventTypeGroupService = eventTypeGroupService;
  }

  @RequestMapping(value = RestUrlPaths.EVENT_TYPE_GROUP_CONTROLLER_BASE_URL, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<List<EventTypeGroupDto>> findAll(@RequestBody Boolean onlyActive) {
    logStartOfFindAllRequest(EntityTypeEnum.EVENT_TYPE_GROUP);
    List<EventTypeGroupDto> eventTypeGroups = eventTypeGroupService.findAll(onlyActive);
    logEndOfFindAllRequest(EntityTypeEnum.EVENT_TYPE_GROUP);
    return new ResponseEntity<>(eventTypeGroups, HttpStatus.OK);
  }

//  @Override
//  public ResponseEntity<List<EventTypeGroup>> findAllActive() {
//    logStartOfFindAllRequest(EntityTypeEnum.EVENT_TYPE_GROUP);
//    List<EventTypeGroup> eventTypeGroups = new ArrayList<>();
//    Iterable<EventTypeGroup> iter = eventTypeGroupDao.findByStatusIn(VersionStatus.getLiveStatuses());
//    StreamSupport.stream(iter.spliterator(), false)
//      .forEach(at -> eventTypeGroups.add(at));
//    logEndOfFindAllRequest(EntityTypeEnum.EVENT_TYPE_GROUP);
//    return new ResponseEntity<>(eventTypeGroups, HttpStatus.OK);
//  }

  @RequestMapping(value = RestUrlPaths.EVENT_TYPE_GROUP_CONTROLLER_BASE_URL, method = RequestMethod.POST)
  public ResponseEntity<EventTypeGroupDto> add(@RequestBody EventTypeGroupDto toBeSaved) {
    logStartOfAddRequest(EntityTypeEnum.EVENT_TYPE_GROUP, toBeSaved);
    EventTypeGroupDto saved = eventTypeGroupService.add(toBeSaved);
    logEndOfAddRequest(EntityTypeEnum.EVENT_TYPE_GROUP, saved);
    return new ResponseEntity<>(saved, HttpStatus.CREATED);
  }

  @RequestMapping(value = RestUrlPaths.EVENT_TYPE_GROUP_CONTROLLER_BASE_URL + "/{id}", method = RequestMethod.DELETE)
  public ResponseEntity<EventTypeGroupDto> delete(@PathVariable Long id) {
    logStartOfDeleteRequest(EntityTypeEnum.EVENT_TYPE_GROUP, id);
    EventTypeGroupDto deleted = eventTypeGroupService.deleteById(id);
    HttpStatus status;
    if (deleted == null) {
      status = HttpStatus.NOT_FOUND;
    } else {
      status = HttpStatus.OK;
    }
    logEndOfDeleteRequest(EntityTypeEnum.EVENT_TYPE_GROUP, deleted);
    return new ResponseEntity<>(deleted, status);
  }

  @RequestMapping(value = RestUrlPaths.EVENT_TYPE_GROUP_CONTROLLER_BASE_URL, method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<EventTypeGroupDto> update(@RequestBody EventTypeGroupDto toBeSaved) {
    logStartOfUpdateRequest(EntityTypeEnum.EVENT_TYPE_GROUP, toBeSaved);
    EventTypeGroupDto updated = eventTypeGroupService.update(toBeSaved);
    HttpStatus status;
    if (updated == null) {
      status = HttpStatus.NOT_FOUND;
    } else {
      status = HttpStatus.OK;
    }
    logEndOfUpdateRequest(EntityTypeEnum.EVENT_TYPE_GROUP, updated);
    return new ResponseEntity<>(updated, status);
  }

}
