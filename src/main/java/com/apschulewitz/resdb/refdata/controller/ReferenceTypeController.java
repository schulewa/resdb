package com.apschulewitz.resdb.refdata.controller;

import com.apschulewitz.resdb.common.controller.AbstractController;
import com.apschulewitz.resdb.config.RestUrlPaths;
import com.apschulewitz.resdb.refdata.model.dao.ReferenceTypeDao;
import com.apschulewitz.resdb.refdata.model.entity.ReferenceType;
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
public class ReferenceTypeController extends AbstractController<ReferenceType, Long> {

  @Autowired
  private ReferenceTypeDao referenceTypeDao;

  @RequestMapping(value = RestUrlPaths.REFERENCE_TYPE_CONTROLLER_BASE_URL, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<List<ReferenceType>> findAll() {

    List<ReferenceType> referenceTypes = new ArrayList<>();
    Iterable<ReferenceType> iter = referenceTypeDao.findAll();
    StreamSupport.stream(iter.spliterator(), false)
      .forEach(at -> referenceTypes.add(at));

    return new ResponseEntity<>(referenceTypes, HttpStatus.OK);
  }

  @RequestMapping(value = RestUrlPaths.REFERENCE_TYPE_CONTROLLER_BASE_URL, method = RequestMethod.POST)
  public ResponseEntity<ReferenceType> add(HttpServletRequest request, @RequestBody ReferenceType toBeSaved) {
    log.info("Save new reference type: {}", toBeSaved);
    ReferenceType saved = referenceTypeDao.save(toBeSaved);
    return new ResponseEntity<>(saved, HttpStatus.CREATED);
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
