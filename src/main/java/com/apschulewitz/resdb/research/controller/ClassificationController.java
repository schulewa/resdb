package com.apschulewitz.resdb.research.controller;

import com.apschulewitz.resdb.common.controller.AbstractController;
import com.apschulewitz.resdb.common.model.EntityTypeEnum;
import com.apschulewitz.resdb.config.RestUrlPaths;
import com.apschulewitz.resdb.research.model.dto.ClassificationCollectionDto;
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

import java.util.List;

/**
 * Created by adrianschulewitz on 22/04/2017.
 */
@RestController()
@Slf4j
public class ClassificationController extends AbstractController<ClassificationCollectionDto, Long> {

  private ClassificationService classificationService;

  public ClassificationController(ClassificationService classificationService) {
    this.classificationService = classificationService;
  }

//  @Override
//  public ResponseEntity<List<ClassificationCollectionDto>> findAllActive() {
//    logStartOfFindAllRequest(EntityTypeEnum.CLASSIFICATION_COLLECTION);
//    List<ClassificationCollectionDto> liveEntries = classificationService.findAllActive();
//    logEndOfFindAllRequest(EntityTypeEnum.CLASSIFICATION_COLLECTION);
//    return new ResponseEntity<>(liveEntries, HttpStatus.OK);
//  }

  @RequestMapping(value = RestUrlPaths.CLASSIFICATION_CONTROLLER_BASE_URL, method = RequestMethod.POST)
  public ResponseEntity<ClassificationCollectionDto> add(@RequestBody ClassificationCollectionDto toBeSaved) {
    logStartOfAddRequest(EntityTypeEnum.CLASSIFICATION_COLLECTION, toBeSaved);
    ClassificationCollectionDto saved = classificationService.add(toBeSaved);
    logEndOfAddRequest(EntityTypeEnum.CLASSIFICATION_COLLECTION, saved);
    return new ResponseEntity<>(saved, HttpStatus.CREATED);
  }

  @RequestMapping(value = RestUrlPaths.CLASSIFICATION_CONTROLLER_BASE_URL + "/{id}", method = RequestMethod.DELETE)
  public ResponseEntity<ClassificationCollectionDto> delete(@PathVariable Long id) {
    logStartOfDeleteRequest(EntityTypeEnum.CLASSIFICATION_COLLECTION, id);
    ClassificationCollectionDto saved = classificationService.deleteById(id);
    logEndOfDeleteRequest(EntityTypeEnum.CLASSIFICATION_COLLECTION, saved);
    return new ResponseEntity<>(saved, HttpStatus.OK);
  }


  @RequestMapping(value = RestUrlPaths.CLASSIFICATION_CONTROLLER_BASE_URL, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<List<ClassificationCollectionDto>> findAll(@RequestBody Boolean onlyActive) {
    logStartOfFindAllRequest(EntityTypeEnum.CLASSIFICATION_COLLECTION);
    List<ClassificationCollectionDto> classificationCollections = classificationService.findAll(onlyActive);
    logEndOfFindAllRequest(EntityTypeEnum.CLASSIFICATION_COLLECTION);
    return new ResponseEntity<>(classificationCollections, HttpStatus.OK);
  }

  @RequestMapping(value = RestUrlPaths.CLASSIFICATION_CONTROLLER_BASE_URL, method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<ClassificationCollectionDto> update(@RequestBody ClassificationCollectionDto toBeSaved) {
    logStartOfUpdateRequest(EntityTypeEnum.CLASSIFICATION_COLLECTION, toBeSaved);
    ClassificationCollectionDto saved = classificationService.update(toBeSaved);
    logEndOfUpdateRequest(EntityTypeEnum.CLASSIFICATION_COLLECTION, saved);
    return new ResponseEntity<>(saved, HttpStatus.OK);
  }

}
