package com.apschulewitz.resdb.refdata.controller;

import com.apschulewitz.resdb.common.controller.AbstractController;
import com.apschulewitz.resdb.common.model.EntityTypeEnum;
import com.apschulewitz.resdb.config.RestUrlPaths;
import com.apschulewitz.resdb.refdata.model.dto.TaleTypeDto;
import com.apschulewitz.resdb.refdata.service.TaleTypeService;
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
public class TaleTypeController extends AbstractController<TaleTypeDto, Long> {

  private TaleTypeService taleTypeService;

  public TaleTypeController(TaleTypeService taleTypeService) {
    this.taleTypeService = taleTypeService;
  }

  @RequestMapping(value = RestUrlPaths.TALE_TYPE_CONTROLLER_BASE_URL, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<List<TaleTypeDto>> findAll(@RequestBody Boolean onlyActive) {
    logStartOfFindAllRequest(EntityTypeEnum.TALE_TYPE);
    List<TaleTypeDto> taleTypes = taleTypeService.findAll(onlyActive);
    logEndOfFindAllRequest(EntityTypeEnum.TALE_TYPE);
    return new ResponseEntity<>(taleTypes, HttpStatus.OK);
  }

//  @Override
//  public ResponseEntity<List<TaleType>> findAllActive() {
//    logStartOfFindAllActiveRequest(EntityTypeEnum.TALE_TYPE);
//    List<TaleType> taleTypes = new ArrayList<>();
//    Iterable<TaleType> iter = taleTypeDao.findByStatusIn(VersionStatus.getLiveStatuses());
//    StreamSupport.stream(iter.spliterator(), false)
//      .forEach(taleTypes::add);
//    logEndOfFindAllActiveRequest(EntityTypeEnum.TALE_TYPE);
//    return new ResponseEntity<>(taleTypes, HttpStatus.OK);
//  }

  @RequestMapping(value = RestUrlPaths.TALE_TYPE_CONTROLLER_BASE_URL, method = RequestMethod.POST)
  public ResponseEntity<TaleTypeDto> add(@RequestBody TaleTypeDto toBeSaved) {
    logStartOfAddRequest(EntityTypeEnum.TALE_TYPE, toBeSaved);
    TaleTypeDto saved = taleTypeService.add(toBeSaved);
    logEndOfAddRequest(EntityTypeEnum.TALE_TYPE, saved);
    return new ResponseEntity<>(saved, HttpStatus.CREATED);
  }

  @RequestMapping(value = RestUrlPaths.TALE_TYPE_CONTROLLER_BASE_URL + "/{id}", method = RequestMethod.DELETE)
  public ResponseEntity<TaleTypeDto> delete(@PathVariable Long id) {
    logStartOfDeleteRequest(EntityTypeEnum.TALE_TYPE, id);
    TaleTypeDto deleted = taleTypeService.deleteById(id);
    HttpStatus status;
    if (deleted == null) {
      status = HttpStatus.NOT_FOUND;
    } else {
      status = HttpStatus.OK;
    }
    logEndOfDeleteRequest(EntityTypeEnum.TALE_TYPE, deleted);
    return new ResponseEntity<>(deleted, status);
  }


  @RequestMapping(value = RestUrlPaths.TALE_TYPE_CONTROLLER_BASE_URL, method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<TaleTypeDto> update(@RequestBody TaleTypeDto toBeSaved) {
    logStartOfUpdateRequest(EntityTypeEnum.TALE_TYPE, toBeSaved);
    TaleTypeDto updated = taleTypeService.update(toBeSaved);
    HttpStatus status;
    if (updated == null) {
      status = HttpStatus.NOT_FOUND;
    } else {
      status = HttpStatus.OK;
    }
    logEndOfUpdateRequest(EntityTypeEnum.TALE_TYPE, updated);
    return new ResponseEntity<>(updated, status);
  }

}
