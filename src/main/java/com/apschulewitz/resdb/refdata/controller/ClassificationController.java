package com.apschulewitz.resdb.refdata.controller;

import com.apschulewitz.resdb.common.controller.AbstractController;
import com.apschulewitz.resdb.common.model.entity.VersionStatus;
import com.apschulewitz.resdb.config.RestUrlPaths;
import com.apschulewitz.resdb.refdata.model.dao.ClassificationDao;
import com.apschulewitz.resdb.refdata.model.entity.ClassificationCollection;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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
public class ClassificationController extends AbstractController<ClassificationCollection, Long> {

  private ClassificationDao classificationDao;

  public ClassificationController(ClassificationDao classificationDao) {
    this.classificationDao = classificationDao;
  }

  @RequestMapping(value = RestUrlPaths.CLASSIFICATION_CONTROLLER_BASE_URL, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<List<ClassificationCollection>> findAll() {

    Iterable<ClassificationCollection> liveEntries = classificationDao.findByStatusIn(VersionStatus.getLiveStatuses());
    List<ClassificationCollection> classificationCollections = StreamSupport.stream(liveEntries.spliterator(), false).collect(Collectors.toList());

    return new ResponseEntity<>(classificationCollections, HttpStatus.OK);
  }

  @RequestMapping(value = RestUrlPaths.CLASSIFICATION_CONTROLLER_BASE_URL, method = RequestMethod.POST)
  public ResponseEntity<ClassificationCollection> add(HttpServletRequest request, @RequestBody ClassificationCollection toBeSaved) {
    log.info("Save new classification: {}", toBeSaved);
      ClassificationCollection saved = classificationDao.save(toBeSaved);
    return new ResponseEntity<>(saved, HttpStatus.CREATED);
  }

  @RequestMapping(value = RestUrlPaths.CLASSIFICATION_CONTROLLER_BASE_URL + "/{id}", method = RequestMethod.DELETE)
  public ResponseEntity<ClassificationCollection> delete(@PathVariable long id) {
    log.info("Marking classification [{}] for deletion", id);
    Optional<ClassificationCollection> existing = classificationDao.findById(id);

    if (existing.isEmpty()) {
      log.error("No existing classification found for id {} - unable to mark for deletion", id);
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    existing.get().setStatus(VersionStatus.Cancel);
      ClassificationCollection saved = classificationDao.save(existing.get());
    return new ResponseEntity<>(saved, HttpStatus.OK);
  }

  @RequestMapping(value = RestUrlPaths.CLASSIFICATION_CONTROLLER_BASE_URL, method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<ClassificationCollection> update(@RequestBody ClassificationCollection toBeSaved) {
    log.info("Update existing classification: {}", toBeSaved);
    Optional<ClassificationCollection> existing = classificationDao.findById(toBeSaved.getId());

    if (existing.isEmpty()) {
      log.error("No existing classification found for id {} - update aborted for: {}", toBeSaved.getId(), toBeSaved);
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

      ClassificationCollection saved = classificationDao.save(toBeSaved);
    return new ResponseEntity<>(saved, HttpStatus.OK);
  }

}
