package com.apschulewitz.resdb.refdata.controller;

import com.apschulewitz.resdb.common.controller.AbstractController;
import com.apschulewitz.resdb.common.model.EntityTypeEnum;
import com.apschulewitz.resdb.common.model.entity.VersionStatus;
import com.apschulewitz.resdb.config.RestUrlPaths;
import com.apschulewitz.resdb.refdata.model.dao.EntityTypeDao;
import com.apschulewitz.resdb.refdata.model.dto.EntityTypeDto;
import com.apschulewitz.resdb.refdata.model.entity.DeityType;
import com.apschulewitz.resdb.refdata.model.entity.EntityType;
import com.apschulewitz.resdb.refdata.service.EntityTypeService;
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
public class EntityTypeController extends AbstractController<EntityTypeDto, Long> {

  private EntityTypeService entityTypeService;

  public EntityTypeController(EntityTypeService entityTypeService) {
    this.entityTypeService = entityTypeService;
  }

  @RequestMapping(value = RestUrlPaths.ENTITY_TYPE_CONTROLLER_BASE_URL, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<List<EntityTypeDto>> findAll(@RequestBody Boolean onlyActive) {
    logStartOfFindAllRequest(EntityTypeEnum.ENTITY_TYPE);
    List<EntityTypeDto> entityTypes = entityTypeService.findAll(onlyActive);
    logEndOfFindAllRequest(EntityTypeEnum.ENTITY_TYPE);
    return new ResponseEntity<>(entityTypes, HttpStatus.OK);
  }

//  @Override
//  public ResponseEntity<List<EntityType>> findAllActive() {
//    logStartOfFindAllActiveRequest(EntityTypeEnum.ENTITY_TYPE);
//    List<EntityType> entityTypes = new ArrayList<>();
//    Iterable<EntityType> iter = entityTypeDao.findByStatusIn(VersionStatus.getLiveStatuses());
//    StreamSupport.stream(iter.spliterator(), false)
//      .forEach(at -> entityTypes.add(at));
//    logEndOfFindAllActiveRequest(EntityTypeEnum.ENTITY_TYPE);
//    return new ResponseEntity<>(entityTypes, HttpStatus.OK);
//  }

  @RequestMapping(value = RestUrlPaths.ENTITY_TYPE_CONTROLLER_BASE_URL, method = RequestMethod.POST)
  public ResponseEntity<EntityTypeDto> add(@RequestBody EntityTypeDto toBeSaved) {
    logStartOfAddRequest(EntityTypeEnum.ENTITY_TYPE, toBeSaved);
    EntityTypeDto saved = entityTypeService.add(toBeSaved);
    logEndOfAddRequest(EntityTypeEnum.ENTITY_TYPE, saved);
    return new ResponseEntity<>(saved, HttpStatus.CREATED);
  }

  @RequestMapping(value = RestUrlPaths.ENTITY_TYPE_CONTROLLER_BASE_URL + "/{id}", method = RequestMethod.DELETE)
  public ResponseEntity<EntityTypeDto> delete(@PathVariable Long id) {
    logStartOfDeleteRequest(EntityTypeEnum.ENTITY_TYPE, id);
    EntityTypeDto deleted = entityTypeService.deleteById(id);
    HttpStatus status;
    if (deleted == null) {
      status = HttpStatus.NOT_FOUND;
    } else {
      status = HttpStatus.OK;
    }
    logEndOfDeleteRequest(EntityTypeEnum.ENTITY_TYPE, deleted);
    return new ResponseEntity<>(deleted, HttpStatus.OK);
  }

  @RequestMapping(value = RestUrlPaths.ENTITY_TYPE_CONTROLLER_BASE_URL, method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<EntityTypeDto> update(@RequestBody EntityTypeDto toBeSaved) {
    logStartOfUpdateRequest(EntityTypeEnum.ENTITY_TYPE, toBeSaved);
    EntityTypeDto updated = entityTypeService.update(toBeSaved);
    HttpStatus status;
    if (updated == null) {
      status = HttpStatus.NOT_FOUND;
    } else {
      status = HttpStatus.OK;
    }
    logEndOfUpdateRequest(EntityTypeEnum.ENTITY_TYPE, updated);
    return new ResponseEntity<>(updated, status);
  }

}
