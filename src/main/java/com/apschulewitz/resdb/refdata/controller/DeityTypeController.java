package com.apschulewitz.resdb.refdata.controller;

import com.apschulewitz.resdb.common.controller.AbstractController;
import com.apschulewitz.resdb.common.model.entity.VersionStatus;
import com.apschulewitz.resdb.config.RestUrlPaths;
import com.apschulewitz.resdb.refdata.model.dao.DeityTypeDao;
import com.apschulewitz.resdb.refdata.model.entity.CalendarType;
import com.apschulewitz.resdb.refdata.model.entity.DeityType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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
public class DeityTypeController extends AbstractController<DeityType, Long> {

  @Autowired
  private DeityTypeDao deityTypeDao;

  @RequestMapping(value = RestUrlPaths.DEITY_TYPE_CONTROLLER_BASE_URL, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<List<DeityType>> findAll() {

    List<DeityType> deityTypes = new ArrayList<>();
    Iterable<DeityType> iter = deityTypeDao.findByStatusIn(VersionStatus.getLiveStatuses());
    StreamSupport.stream(iter.spliterator(), false)
      .forEach(at -> deityTypes.add(at));
    log.info("findAll: {} deity types found", deityTypes.size());
    return new ResponseEntity<>(deityTypes, HttpStatus.OK);
  }

  @RequestMapping(value = RestUrlPaths.DEITY_TYPE_CONTROLLER_BASE_URL, method = RequestMethod.POST)
  public ResponseEntity<DeityType> add(HttpServletRequest request, @RequestBody DeityType toBeSaved) {
    log.info("Save new deity type: {}", toBeSaved);
    DeityType saved = deityTypeDao.save(toBeSaved);
    return new ResponseEntity<>(saved, HttpStatus.CREATED);
  }

  @RequestMapping(value = RestUrlPaths.DEITY_TYPE_CONTROLLER_BASE_URL + "/{id}", method = RequestMethod.DELETE)
  public ResponseEntity<DeityType> delete(@PathVariable long id) {
    log.info("Marking deity type [{}] for deletion", id);
    Optional<DeityType> existing = deityTypeDao.findById(id);

    if (existing.isEmpty()) {
      log.error("No existing deity type found for id {} - unable to mark for deletion", id);
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    existing.get().setStatus(VersionStatus.Cancel);
    DeityType saved = deityTypeDao.save(existing.get());
    return new ResponseEntity<>(saved, HttpStatus.OK);
  }

  @RequestMapping(value = RestUrlPaths.DEITY_TYPE_CONTROLLER_BASE_URL, method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<DeityType> update(@RequestBody DeityType toBeSaved) {
    log.info("Update existing deity type: {}", toBeSaved);
    Optional<DeityType> existing = deityTypeDao.findById(toBeSaved.getId());

    if (existing.isEmpty()) {
      log.error("No existing deity type found for id {} - update aborted for: {}", toBeSaved.getId(), toBeSaved);
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    DeityType saved = deityTypeDao.save(toBeSaved);
    return new ResponseEntity<>(saved, HttpStatus.OK);
  }

}
