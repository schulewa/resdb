package com.apschulewitz.resdb.research.controller;

import com.apschulewitz.resdb.common.controller.AbstractController;
import com.apschulewitz.resdb.config.RestUrlPaths;
import com.apschulewitz.resdb.research.model.dto.ClassificationCollectionDto;
import com.apschulewitz.resdb.research.model.entity.ClassificationCollection;
import com.apschulewitz.resdb.research.service.ClassificationService;
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

/**
 * Created by adrianschulewitz on 22/04/2017.
 */
@RestController()
@Slf4j
public class ClassificationController extends AbstractController<ClassificationCollection, Long> {

  private ClassificationService classificationService;

  public ClassificationController(ClassificationService classificationService) {
    this.classificationService = classificationService;
  }

  @RequestMapping(value = RestUrlPaths.CLASSIFICATION_CONTROLLER_BASE_URL, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<List<ClassificationCollectionDto>> findAll() {
    log.info("Processing request to find all classification collections");
    List<ClassificationCollectionDto> liveEntries = classificationService.findAllLiveCollections();
    log.info("Found {} classification collections", liveEntries.size());
    return new ResponseEntity<>(liveEntries, HttpStatus.OK);
  }

  @RequestMapping(value = RestUrlPaths.CLASSIFICATION_CONTROLLER_BASE_URL, method = RequestMethod.POST)
  public ResponseEntity<ClassificationCollectionDto> add(@RequestBody ClassificationCollectionDto toBeSaved) {
    log.info("Processing request to save new classification collection: {}", toBeSaved);
    ClassificationCollectionDto saved = classificationService.save(toBeSaved);
    log.info("New classification collection saved: {}", saved);
    return new ResponseEntity<>(saved, HttpStatus.CREATED);
  }

  @RequestMapping(value = RestUrlPaths.CLASSIFICATION_CONTROLLER_BASE_URL + "/{id}", method = RequestMethod.DELETE)
  public ResponseEntity<Boolean> delete(@PathVariable long id) {
    log.info("Processing request to mark classification collection for identifier {} as deleted", id);
    boolean markedAsDeleted = classificationService.delete(id);
    log.info("Classification collection for identifier {} marked as deleted: {}", id, markedAsDeleted);
    return new ResponseEntity<>(markedAsDeleted, HttpStatus.OK);
  }

  @RequestMapping(value = RestUrlPaths.CLASSIFICATION_CONTROLLER_BASE_URL, method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<ClassificationCollectionDto> update(@RequestBody ClassificationCollectionDto toBeSaved) {
    log.info("Processing request to update existing classification: {}", toBeSaved);
    ClassificationCollectionDto saved = classificationService.save(toBeSaved);
    log.info("Request to update classification collection [{}] processed", toBeSaved);
    return new ResponseEntity<>(saved, HttpStatus.OK);
  }

}
