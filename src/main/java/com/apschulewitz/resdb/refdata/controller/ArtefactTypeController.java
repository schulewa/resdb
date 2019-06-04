package com.apschulewitz.resdb.refdata.controller;

import com.apschulewitz.resdb.common.controller.AbstractController;
import com.apschulewitz.resdb.config.RestUrlPaths;
import com.apschulewitz.resdb.refdata.model.dao.ArtefactGroupDao;
import com.apschulewitz.resdb.refdata.model.dao.ArtefactTypeDao;
import com.apschulewitz.resdb.refdata.model.entity.ArtefactType;
import com.apschulewitz.resdb.refdata.model.entity.ArtefactType;
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
public class ArtefactTypeController extends AbstractController<ArtefactType, Long> {

  @Autowired
  private ArtefactTypeDao artefactTypeDao;

  @RequestMapping(value = RestUrlPaths.ARTEFACT_TYPE_CONTROLLER_BASE_URL, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<List<ArtefactType>> findAll() {

    List<ArtefactType> artefactTypes = new ArrayList<>();
    Iterable<ArtefactType> iter = artefactTypeDao.findAll();
    StreamSupport.stream(iter.spliterator(), false)
      .forEach(at -> artefactTypes.add(at));
    log.info("findAll: {} artefact types founs", artefactTypes.size());
    return new ResponseEntity<>(artefactTypes, HttpStatus.OK);
  }

  @RequestMapping(value = RestUrlPaths.ARTEFACT_TYPE_CONTROLLER_BASE_URL, method = RequestMethod.POST)
  public ResponseEntity<ArtefactType> add(HttpServletRequest request, @RequestBody ArtefactType toBeSaved) {
    log.info("Save new artefact type: {}", toBeSaved);
    ArtefactType saved = artefactTypeDao.save(toBeSaved);
    return new ResponseEntity<>(saved, HttpStatus.CREATED);
  }

  @RequestMapping(value = RestUrlPaths.ARTEFACT_TYPE_CONTROLLER_BASE_URL, method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<ArtefactType> update(ArtefactType toBeSaved) {
    log.info("Update existing artefact type: {}", toBeSaved);
    Optional<ArtefactType> existing = artefactTypeDao.findById(toBeSaved.getId());

    if (existing.isEmpty()) {
      log.error("No existing artefact type found for id {} - update aborted for: {}", toBeSaved.getId(), toBeSaved);
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    ArtefactType saved = artefactTypeDao.save(toBeSaved);
    return new ResponseEntity<>(saved, HttpStatus.OK);
  }

}
