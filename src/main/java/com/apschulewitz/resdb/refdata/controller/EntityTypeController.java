package com.apschulewitz.resdb.refdata.controller;

import com.apschulewitz.resdb.common.controller.AbstractController;
import com.apschulewitz.resdb.common.model.entity.VersionStatus;
import com.apschulewitz.resdb.config.RestUrlPaths;
import com.apschulewitz.resdb.refdata.model.dao.EntityTypeDao;
import com.apschulewitz.resdb.refdata.model.entity.DeityType;
import com.apschulewitz.resdb.refdata.model.entity.EntityType;
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
public class EntityTypeController extends AbstractController<EntityType, Long> {

  private EntityTypeDao entityTypeDao;

  public EntityTypeController(EntityTypeDao entityTypeDao) {
    this.entityTypeDao = entityTypeDao;
  }

  @RequestMapping(value = RestUrlPaths.ENTITY_TYPE_CONTROLLER_BASE_URL, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<List<EntityType>> findAll() {

    List<EntityType> entityTypes = new ArrayList<>();
    Iterable<EntityType> iter = entityTypeDao.findByStatusIn(VersionStatus.getLiveStatuses());
    StreamSupport.stream(iter.spliterator(), false)
      .forEach(at -> entityTypes.add(at));
    log.info("findAll: {} entity types found", entityTypes.size());
    return new ResponseEntity<>(entityTypes, HttpStatus.OK);
  }

  @RequestMapping(value = RestUrlPaths.ENTITY_TYPE_CONTROLLER_BASE_URL, method = RequestMethod.POST)
  public ResponseEntity<EntityType> add(HttpServletRequest request, @RequestBody EntityType toBeSaved) {
    log.info("Save new entity type: {}", toBeSaved);
    EntityType saved = entityTypeDao.save(toBeSaved);
    return new ResponseEntity<>(saved, HttpStatus.CREATED);
  }

  @RequestMapping(value = RestUrlPaths.ENTITY_TYPE_CONTROLLER_BASE_URL + "/{id}", method = RequestMethod.DELETE)
  public ResponseEntity<EntityType> delete(@PathVariable long id) {
    log.info("Marking entity type [{}] for deletion", id);
    Optional<EntityType> existing = entityTypeDao.findById(id);

    if (existing.isEmpty()) {
      log.error("No existing entity type found for id {} - unable to mark for deletion", id);
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    existing.get().setStatus(VersionStatus.Cancel);
    EntityType saved = entityTypeDao.save(existing.get());
    return new ResponseEntity<>(saved, HttpStatus.OK);
  }

  @RequestMapping(value = RestUrlPaths.ENTITY_TYPE_CONTROLLER_BASE_URL, method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<EntityType> update(@RequestBody EntityType toBeSaved) {
    log.info("Update existing entity type: {}", toBeSaved);
    Optional<EntityType> existing = entityTypeDao.findById(toBeSaved.getId());

    if (existing.isEmpty()) {
      log.error("No existing entity type found for id {} - update aborted for: {}", toBeSaved.getId(), toBeSaved);
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    EntityType saved = entityTypeDao.save(toBeSaved);
    return new ResponseEntity<>(saved, HttpStatus.OK);
  }

}
