package com.apschulewitz.resdb.refdata.controller;

import com.apschulewitz.resdb.common.controller.AbstractController;
import com.apschulewitz.resdb.common.model.entity.VersionStatus;
import com.apschulewitz.resdb.config.RestUrlPaths;
import com.apschulewitz.resdb.refdata.model.dao.ArtefactGroupDao;
import com.apschulewitz.resdb.refdata.model.dao.ArtefactTypeDao;
import com.apschulewitz.resdb.refdata.model.entity.ArtefactGroup;
import com.apschulewitz.resdb.refdata.model.entity.ArtefactType;
import com.apschulewitz.resdb.refdata.model.entity.ArtefactType;
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
public class ArtefactTypeController extends AbstractController<ArtefactType, Long> {

  private ArtefactTypeDao artefactTypeDao;

  public ArtefactTypeController(ArtefactTypeDao artefactTypeDao) {
    this.artefactTypeDao = artefactTypeDao;
  }

  @RequestMapping(value = RestUrlPaths.ARTEFACT_TYPE_CONTROLLER_BASE_URL, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<List<ArtefactType>> findAll() {

    List<ArtefactType> artefactTypes = new ArrayList<>();
    Iterable<ArtefactType> iter = artefactTypeDao.findByStatusIn(VersionStatus.getLiveStatuses());
    StreamSupport.stream(iter.spliterator(), false)
      .forEach(artefactTypes::add);
    log.info("findAll: {} artefact types founs", artefactTypes.size());
    return new ResponseEntity<>(artefactTypes, HttpStatus.OK);
  }

  @RequestMapping(value = RestUrlPaths.ARTEFACT_TYPE_CONTROLLER_BASE_URL, method = RequestMethod.POST)
  public ResponseEntity<ArtefactType> add(HttpServletRequest request, @RequestBody ArtefactType toBeSaved) {
    log.info("Save new artefact type: {}", toBeSaved);
    ArtefactType saved = artefactTypeDao.save(toBeSaved);
    return new ResponseEntity<>(saved, HttpStatus.CREATED);
  }

  @RequestMapping(value = RestUrlPaths.ARTEFACT_TYPE_CONTROLLER_BASE_URL + "/{id}", method = RequestMethod.DELETE)
  public ResponseEntity<ArtefactType> delete(@PathVariable long id) {
    log.info("Marking artefact type [{}] for deletion", id);
    Optional<ArtefactType> existing = artefactTypeDao.findById(id);

    if (existing.isEmpty()) {
      log.error("No existing artefact type found for id {} - unable to mark for deletion", id);
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    existing.get().setStatus(VersionStatus.Cancel);
    ArtefactType saved = artefactTypeDao.save(existing.get());
    return new ResponseEntity<>(saved, HttpStatus.OK);
  }

  @RequestMapping(value = RestUrlPaths.ARTEFACT_TYPE_CONTROLLER_BASE_URL, method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<ArtefactType> update(@RequestBody ArtefactType toBeSaved) {
    log.info("Update existing artefact type: {}", toBeSaved);
    Optional<ArtefactType> existing = artefactTypeDao.findById(toBeSaved.getId());

    if (existing.isEmpty()) {
      log.error("No existing artefact type found for id {} - update aborted for: {}", toBeSaved.getId(), toBeSaved);
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    ArtefactType saved;
    String errMsg;

    try {
      saved = artefactTypeDao.save(toBeSaved);
    } catch (Exception e) {
      String msg = e.getCause() != null ? e.getCause().getMessage() : e.getMessage();
      errMsg = "Error saving artefact type [" + toBeSaved.getName() + "]: " + msg;
      log.error(errMsg, e);
      return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }

    return new ResponseEntity<>(saved, HttpStatus.OK);
  }

}
