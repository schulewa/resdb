package com.apschulewitz.resdb.refdata.controller;

import com.apschulewitz.resdb.common.controller.AbstractController;
import com.apschulewitz.resdb.common.model.entity.VersionStatus;
import com.apschulewitz.resdb.config.RestUrlPaths;
import com.apschulewitz.resdb.refdata.model.dao.ArtefactGroupDao;
import com.apschulewitz.resdb.refdata.model.entity.ArtefactGroup;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * Created by adrianschulewitz on 22/04/2017.
 */
@RestController()
@Slf4j
public class ArtefactGroupController extends AbstractController<ArtefactGroup, Long> {

  @Autowired
  private ArtefactGroupDao artefactGroupDao;

  @RequestMapping(value = RestUrlPaths.ARTEFACT_GROUP_CONTROLLER_BASE_URL, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<List<ArtefactGroup>> findAll() {

    Iterable<ArtefactGroup> liveEntries = artefactGroupDao.findByStatusIn(VersionStatus.getLiveStatuses());
    List<ArtefactGroup> artefactGroups = StreamSupport.stream(liveEntries.spliterator(), false).collect(Collectors.toList());

    return new ResponseEntity<>(artefactGroups, HttpStatus.OK);
  }

  @RequestMapping(value = RestUrlPaths.ARTEFACT_GROUP_CONTROLLER_BASE_URL, method = RequestMethod.POST)
  public ResponseEntity<ArtefactGroup> add(HttpServletRequest request, @RequestBody ArtefactGroup toBeSaved) {
    log.info("Save new artefact group: {}", toBeSaved);
    ArtefactGroup saved = artefactGroupDao.save(toBeSaved);
    return new ResponseEntity<>(saved, HttpStatus.CREATED);
  }

  @RequestMapping(value = RestUrlPaths.ARTEFACT_GROUP_CONTROLLER_BASE_URL + "/{id}", method = RequestMethod.DELETE)
  public ResponseEntity<ArtefactGroup> delete(@PathVariable long id) {
    log.info("Marking artefact group [{}] for deletion", id);
    Optional<ArtefactGroup> existing = artefactGroupDao.findById(id);

    if (existing.isEmpty()) {
      log.error("No existing artefact group found for id {} - unable to mark for deletion", id);
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    existing.get().setStatus(VersionStatus.Cancel);
    ArtefactGroup saved = artefactGroupDao.save(existing.get());
    return new ResponseEntity<>(saved, HttpStatus.OK);
  }

  @RequestMapping(value = RestUrlPaths.ARTEFACT_GROUP_CONTROLLER_BASE_URL, method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<ArtefactGroup> update(@RequestBody ArtefactGroup toBeSaved) {
    log.info("Update existing artefact group: {}", toBeSaved);
    Optional<ArtefactGroup> existing = artefactGroupDao.findById(toBeSaved.getId());

    if (existing.isEmpty()) {
      log.error("No existing artefact group found for id {} - update aborted for: {}", toBeSaved.getId(), toBeSaved);
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    ArtefactGroup saved = artefactGroupDao.save(toBeSaved);
    return new ResponseEntity<>(saved, HttpStatus.OK);
  }

}
