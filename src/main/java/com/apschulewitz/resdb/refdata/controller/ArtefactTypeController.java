package com.apschulewitz.resdb.refdata.controller;

import com.apschulewitz.resdb.common.controller.AbstractController;
import com.apschulewitz.resdb.common.model.EntityTypeEnum;
import com.apschulewitz.resdb.common.utils.LoggingUtils;
import com.apschulewitz.resdb.config.RestUrlPaths;
import com.apschulewitz.resdb.refdata.model.dto.ArtefactTypeDto;
import com.apschulewitz.resdb.refdata.service.ArtefactTypeService;
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
public class ArtefactTypeController extends AbstractController<ArtefactTypeDto, Long> {

  private ArtefactTypeService artefactTypeService;

  public ArtefactTypeController(ArtefactTypeService artefactTypeService) {
    this.artefactTypeService = artefactTypeService;
  }

  @RequestMapping(value = RestUrlPaths.ARTEFACT_TYPE_CONTROLLER_BASE_URL, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<List<ArtefactTypeDto>> findAll(@RequestBody Boolean onlyActive) {
    LoggingUtils.logStartOfFindAllRequest(EntityTypeEnum.ARTEFACT_TYPE);
    List<ArtefactTypeDto> artefactTypes = artefactTypeService.findAll(onlyActive);
    LoggingUtils.logEndOfFindAllRequest(EntityTypeEnum.ARTEFACT_TYPE);
    return new ResponseEntity<>(artefactTypes, HttpStatus.OK);
  }

//  @Override
//  public ResponseEntity<List<ArtefactType>> findAllActive() {
//    logStartOfFindAllRequest(EntityTypeEnum.ARTEFACT_TYPE);
//    List<ArtefactType> artefactTypes = new ArrayList<>();
//    Iterable<ArtefactType> iter = artefactTypeDao.findByStatusIn(VersionStatus.getLiveStatuses());
//    StreamSupport.stream(iter.spliterator(), false)
//      .forEach(artefactTypes::add);
//    logEndOfFindAllRequest(EntityTypeEnum.ARTEFACT_TYPE);
//    return new ResponseEntity<>(artefactTypes, HttpStatus.OK);
//  }

  @RequestMapping(value = RestUrlPaths.ARTEFACT_TYPE_CONTROLLER_BASE_URL, method = RequestMethod.POST)
  public ResponseEntity<ArtefactTypeDto> add(@RequestBody ArtefactTypeDto toBeSaved) {
    LoggingUtils.logStartOfAddRequest(EntityTypeEnum.ARTEFACT_TYPE, toBeSaved);
    ArtefactTypeDto saved = artefactTypeService.add(toBeSaved);
    LoggingUtils.logEndOfAddRequest(EntityTypeEnum.ARTEFACT_TYPE, saved);
    return new ResponseEntity<>(saved, HttpStatus.CREATED);
  }

  @RequestMapping(value = RestUrlPaths.ARTEFACT_TYPE_CONTROLLER_BASE_URL + "/{id}", method = RequestMethod.DELETE)
  public ResponseEntity<ArtefactTypeDto> delete(@PathVariable Long id) {
    LoggingUtils.logStartOfDeleteRequest(EntityTypeEnum.ARTEFACT_TYPE, id);
    ArtefactTypeDto deleted = artefactTypeService.deleteById(id);
    HttpStatus status;
    if (deleted == null) {
      status = HttpStatus.NOT_FOUND;
    } else {
      status = HttpStatus.OK;
    }
    LoggingUtils.logEndOfDeleteRequest(EntityTypeEnum.ARTEFACT_TYPE, deleted);
    return new ResponseEntity<>(deleted, status);
  }

  @RequestMapping(value = RestUrlPaths.ARTEFACT_TYPE_CONTROLLER_BASE_URL, method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<ArtefactTypeDto> update(@RequestBody ArtefactTypeDto toBeSaved) {
    LoggingUtils.logStartOfUpdateRequest(EntityTypeEnum.ARTEFACT_TYPE, toBeSaved);
    ArtefactTypeDto saved = artefactTypeService.update(toBeSaved);
    LoggingUtils.logEndOfUpdateRequest(EntityTypeEnum.ARTEFACT_TYPE, saved);
    return new ResponseEntity<>(saved, HttpStatus.OK);
  }

}
