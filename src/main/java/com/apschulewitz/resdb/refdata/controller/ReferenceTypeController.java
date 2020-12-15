package com.apschulewitz.resdb.refdata.controller;

import com.apschulewitz.resdb.common.controller.AbstractController;
import com.apschulewitz.resdb.common.model.EntityTypeEnum;
import com.apschulewitz.resdb.common.model.entity.VersionStatus;
import com.apschulewitz.resdb.config.RestUrlPaths;
import com.apschulewitz.resdb.refdata.model.dao.ReferenceTypeDao;
import com.apschulewitz.resdb.refdata.model.dto.ReferenceTypeDto;
import com.apschulewitz.resdb.refdata.model.dto.TechnologyTypeGroupDto;
import com.apschulewitz.resdb.refdata.model.entity.ReferenceType;
import com.apschulewitz.resdb.refdata.service.ReferenceTypeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

/**
 * Created by adrianschulewitz on 22/04/2017.
 */
@RestController()
@Slf4j
public class ReferenceTypeController extends AbstractController<ReferenceTypeDto, Long> {

  private ReferenceTypeService referenceTypeService;

  public ReferenceTypeController(ReferenceTypeService referenceTypeService) {
    this.referenceTypeService = referenceTypeService;
  }

  @RequestMapping(value = RestUrlPaths.REFERENCE_TYPE_CONTROLLER_BASE_URL, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<List<ReferenceTypeDto>> findAll(@RequestBody Boolean onlyActive) {
    logStartOfFindAllRequest(EntityTypeEnum.REFERENCE_TYPE);
    List<ReferenceTypeDto> referenceTypes = referenceTypeService.findAll(onlyActive);
    logEndOfFindAllRequest(EntityTypeEnum.REFERENCE_TYPE);
    return new ResponseEntity<>(referenceTypes, HttpStatus.OK);
  }

//  @Override
//  public ResponseEntity<List<ReferenceType>> findAllActive() {
//    logStartOfFindAllRequest(EntityTypeEnum.REFERENCE_TYPE);
//    List<ReferenceType> referenceTypes = new ArrayList<>();
//    Iterable<ReferenceType> iter = referenceTypeDao.findByStatusIn(VersionStatus.getLiveStatuses());
//    StreamSupport.stream(iter.spliterator(), false)
//      .forEach(referenceTypes::add);
//    logEndOfFindAllRequest(EntityTypeEnum.REFERENCE_TYPE);
//    return new ResponseEntity<>(referenceTypes, HttpStatus.OK);
//  }

  @RequestMapping(value = RestUrlPaths.REFERENCE_TYPE_CONTROLLER_BASE_URL, method = RequestMethod.POST)
  public ResponseEntity<ReferenceTypeDto> add(@RequestBody ReferenceTypeDto toBeSaved) {
    logStartOfAddRequest(EntityTypeEnum.REFERENCE_TYPE, toBeSaved);
    ReferenceTypeDto saved = referenceTypeService.add(toBeSaved);
    logEndOfAddRequest(EntityTypeEnum.REFERENCE_TYPE, saved);
    return new ResponseEntity<>(saved, HttpStatus.CREATED);
  }

  @RequestMapping(value = RestUrlPaths.REFERENCE_TYPE_CONTROLLER_BASE_URL + "/{id}", method = RequestMethod.DELETE)
  public ResponseEntity<ReferenceTypeDto> delete(@PathVariable Long id) {
    logStartOfDeleteRequest(EntityTypeEnum.REFERENCE_TYPE, id);
    ReferenceTypeDto deleted = referenceTypeService.deleteById(id);
    HttpStatus result;
    if (deleted == null)
      result = HttpStatus.NOT_FOUND;
    else
      result = HttpStatus.OK;

    logEndOfDeleteRequest(EntityTypeEnum.REFERENCE_TYPE, deleted);
    return new ResponseEntity<>(deleted, result);
  }

  @RequestMapping(value = RestUrlPaths.REFERENCE_TYPE_CONTROLLER_BASE_URL, method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<ReferenceTypeDto> update(ReferenceTypeDto toBeSaved) {
    logStartOfUpdateRequest(EntityTypeEnum.REFERENCE_TYPE, toBeSaved);
    ReferenceTypeDto saved = referenceTypeService.update(toBeSaved);
    logEndOfUpdateRequest(EntityTypeEnum.REFERENCE_TYPE, saved);
    return new ResponseEntity<>(saved, HttpStatus.OK);
  }

}
