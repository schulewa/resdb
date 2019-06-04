package com.apschulewitz.resdb.refdata.controller;

import com.apschulewitz.resdb.common.controller.AbstractController;
import com.apschulewitz.resdb.config.RestUrlPaths;
import com.apschulewitz.resdb.refdata.model.dao.EventTypeGroupDao;
import com.apschulewitz.resdb.refdata.model.entity.EventTypeGroup;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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
public class EventTypeGroupController extends AbstractController<EventTypeGroup, Long> {

  @Autowired
  private EventTypeGroupDao eventTypeGroupDao;

  @RequestMapping(value = RestUrlPaths.EVENT_TYPE_GROUP_CONTROLLER_BASE_URL, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<List<EventTypeGroup>> findAll() {

    List<EventTypeGroup> eventTypeGroups = new ArrayList<>();
    Iterable<EventTypeGroup> iter = eventTypeGroupDao.findAll();
    StreamSupport.stream(iter.spliterator(), false)
      .forEach(at -> eventTypeGroups.add(at));
    log.info("findAll: {} event type groups found", eventTypeGroups.size());
    return new ResponseEntity<>(eventTypeGroups, HttpStatus.OK);
  }

  @RequestMapping(value = RestUrlPaths.EVENT_TYPE_GROUP_CONTROLLER_BASE_URL, method = RequestMethod.POST)
  public ResponseEntity<EventTypeGroup> add(HttpServletRequest request, @RequestBody EventTypeGroup toBeSaved) {
    log.info("Save new event type group: {}", toBeSaved);
    EventTypeGroup saved = eventTypeGroupDao.save(toBeSaved);
    return new ResponseEntity<>(saved, HttpStatus.CREATED);
  }

  @RequestMapping(value = RestUrlPaths.EVENT_TYPE_GROUP_CONTROLLER_BASE_URL, method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<EventTypeGroup> update(EventTypeGroup toBeSaved) {
    log.info("Update existing event type group: {}", toBeSaved);
    Optional<EventTypeGroup> existing = eventTypeGroupDao.findById(toBeSaved.getId());

    if (existing.isEmpty()) {
      log.error("No existing event type group found for id {} - update aborted for: {}", toBeSaved.getId(), toBeSaved);
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    EventTypeGroup saved = eventTypeGroupDao.save(toBeSaved);
    return new ResponseEntity<>(saved, HttpStatus.OK);
  }

}
