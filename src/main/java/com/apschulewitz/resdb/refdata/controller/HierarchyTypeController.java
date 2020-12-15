package com.apschulewitz.resdb.refdata.controller;

import com.apschulewitz.resdb.common.controller.AbstractController;
import com.apschulewitz.resdb.common.model.EntityTypeEnum;
import com.apschulewitz.resdb.config.RestUrlPaths;
import com.apschulewitz.resdb.refdata.model.dto.HierarchyTypeDto;
import com.apschulewitz.resdb.refdata.service.HierarchyTypeService;
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
public class HierarchyTypeController extends AbstractController<HierarchyTypeDto, Long> {

  private HierarchyTypeService hierarchyTypeService;

  public HierarchyTypeController(HierarchyTypeService hierarchyTypeService) {
    this.hierarchyTypeService = hierarchyTypeService;
  }

  @RequestMapping(value = RestUrlPaths.HIERARCHY_TYPE_CONTROLLER_BASE_URL, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<List<HierarchyTypeDto>> findAll(@RequestBody Boolean onlyActive) {
    logStartOfFindAllRequest(EntityTypeEnum.HIERARCHY_TYPE);
    List<HierarchyTypeDto> hierarchyTypes = hierarchyTypeService.findAll(onlyActive);
    logEndOfFindAllRequest(EntityTypeEnum.HIERARCHY_TYPE);
    return new ResponseEntity<>(hierarchyTypes, HttpStatus.OK);
  }

//  @Override
//  public ResponseEntity<List<HierarchyType>> findAllActive() {
//    logStartOfFindAllActiveRequest(EntityTypeEnum.HIERARCHY_TYPE);
//    List<HierarchyType> hierarchyTypes = new ArrayList<>();
//    Iterable<HierarchyType> iter = hierarchyTypeDao.findByStatusIn(VersionStatus.getLiveStatuses());
//    StreamSupport.stream(iter.spliterator(), false)
//      .forEach(hierarchyTypes::add);
//    logEndOfFindAllActiveRequest(EntityTypeEnum.HIERARCHY_TYPE);
//    return new ResponseEntity<>(hierarchyTypes, HttpStatus.OK);
//  }

  @RequestMapping(value = RestUrlPaths.HIERARCHY_TYPE_CONTROLLER_BASE_URL, method = RequestMethod.POST)
  public ResponseEntity<HierarchyTypeDto> add(@RequestBody HierarchyTypeDto toBeSaved) {
    logStartOfAddRequest(EntityTypeEnum.HIERARCHY_TYPE, toBeSaved);
    HierarchyTypeDto saved = hierarchyTypeService.add(toBeSaved);
    logEndOfAddRequest(EntityTypeEnum.HIERARCHY_TYPE, saved);
    return new ResponseEntity<>(saved, HttpStatus.CREATED);
  }

  @RequestMapping(value = RestUrlPaths.HIERARCHY_TYPE_CONTROLLER_BASE_URL + "/{id}", method = RequestMethod.DELETE)
  public ResponseEntity<HierarchyTypeDto> delete(@PathVariable Long id) {
    logStartOfDeleteRequest(EntityTypeEnum.HIERARCHY_TYPE, id);
    HierarchyTypeDto deleted = hierarchyTypeService.deleteById(id);
    HttpStatus status;
    if (deleted == null) {
      status = HttpStatus.NOT_FOUND;
    } else {
      status = HttpStatus.OK;
    }
    logEndOfDeleteRequest(EntityTypeEnum.HIERARCHY_TYPE, deleted);
    return new ResponseEntity<>(deleted, status);
  }

  @RequestMapping(value = RestUrlPaths.HIERARCHY_TYPE_CONTROLLER_BASE_URL, method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<HierarchyTypeDto> update(@RequestBody HierarchyTypeDto toBeSaved) {
    logStartOfUpdateRequest(EntityTypeEnum.HIERARCHY_TYPE, toBeSaved);
    HierarchyTypeDto updated = hierarchyTypeService.update(toBeSaved);
    HttpStatus status;
    if (updated == null) {
      status = HttpStatus.NOT_FOUND;
    } else {
      status = HttpStatus.OK;
    }
    logEndOfUpdateRequest(EntityTypeEnum.HIERARCHY_TYPE, updated);
    return new ResponseEntity<>(updated, status);
  }

}
