package com.apschulewitz.resdb.refdata.controller;

import com.apschulewitz.resdb.common.controller.AbstractController;
import com.apschulewitz.resdb.common.model.entity.VersionStatus;
import com.apschulewitz.resdb.config.RestUrlPaths;
import com.apschulewitz.resdb.refdata.model.dao.ReferenceTypeDao;
import com.apschulewitz.resdb.refdata.model.entity.RaceType;
import com.apschulewitz.resdb.refdata.model.entity.ReferenceType;
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
public class ReferenceTypeController extends AbstractController<ReferenceType, Long> {

  private ReferenceTypeDao referenceTypeDao;

  public ReferenceTypeController(ReferenceTypeDao referenceTypeDao) {
    this.referenceTypeDao = referenceTypeDao;
  }

  @RequestMapping(value = RestUrlPaths.REFERENCE_TYPE_CONTROLLER_BASE_URL, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<List<ReferenceType>> findAll() {

    List<ReferenceType> referenceTypes = new ArrayList<>();
    Iterable<ReferenceType> iter = referenceTypeDao.findByStatusIn(VersionStatus.getLiveStatuses());
    StreamSupport.stream(iter.spliterator(), false)
      .forEach(referenceTypes::add);

    return new ResponseEntity<>(referenceTypes, HttpStatus.OK);
  }

  @RequestMapping(value = RestUrlPaths.REFERENCE_TYPE_CONTROLLER_BASE_URL, method = RequestMethod.POST)
  public ResponseEntity<ReferenceType> add(HttpServletRequest request, @RequestBody ReferenceType toBeSaved) {
    log.info("Save new reference type: {}", toBeSaved);
    ReferenceType saved = referenceTypeDao.save(toBeSaved);
    return new ResponseEntity<>(saved, HttpStatus.CREATED);
  }

  @RequestMapping(value = RestUrlPaths.RACE_TYPE_CONTROLLER_BASE_URL + "/{id}", method = RequestMethod.DELETE)
  public ResponseEntity<ReferenceType> delete(@PathVariable long id) {
    log.info("Marking reference type [{}] for deletion", id);
    Optional<ReferenceType> existing = referenceTypeDao.findById(id);

    if (existing.isEmpty()) {
      log.error("No existing reference type found for id {} - unable to mark for deletion", id);
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    existing.get().setStatus(VersionStatus.Cancel);
    ReferenceType saved = referenceTypeDao.save(existing.get());
    return new ResponseEntity<>(saved, HttpStatus.OK);
  }

  @RequestMapping(value = RestUrlPaths.REFERENCE_TYPE_CONTROLLER_BASE_URL, method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<ReferenceType> update(ReferenceType toBeSaved) {
    log.info("Update existing reference type: {}", toBeSaved);
    Optional<ReferenceType> existing = referenceTypeDao.findById(toBeSaved.getId());

    if (existing.isEmpty()) {
      log.error("No existing reference type found for id {} - update aborted for: {}", toBeSaved.getId(), toBeSaved);
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    ReferenceType saved = referenceTypeDao.save(toBeSaved);
    return new ResponseEntity<>(saved, HttpStatus.OK);
  }

}
