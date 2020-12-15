package com.apschulewitz.resdb.refdata.controller;

import com.apschulewitz.resdb.common.controller.AbstractController;
import com.apschulewitz.resdb.common.model.EntityTypeEnum;
import com.apschulewitz.resdb.common.utils.LoggingUtils;
import com.apschulewitz.resdb.config.RestUrlPaths;
import com.apschulewitz.resdb.refdata.model.dto.DeityTypeDto;
import com.apschulewitz.resdb.refdata.service.DeityTypeService;
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
public class DeityTypeController extends AbstractController<DeityTypeDto, Long> {

  private DeityTypeService deityTypeService;

  public DeityTypeController(DeityTypeService deityTypeService) {
    this.deityTypeService = deityTypeService;
  }

  @RequestMapping(value = RestUrlPaths.DEITY_TYPE_CONTROLLER_BASE_URL, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<List<DeityTypeDto>> findAll(@RequestBody Boolean onlyActive) {
    LoggingUtils.logStartOfFindAllRequest(EntityTypeEnum.DEITY_TYPE);
    List<DeityTypeDto> deityTypes = deityTypeService.findAll(onlyActive);
    LoggingUtils.logEndOfFindAllRequest(EntityTypeEnum.DEITY_TYPE);
    return new ResponseEntity<>(deityTypes, HttpStatus.OK);
  }

//  @Override
//  public ResponseEntity<List<DeityType>> findAllActive() {
//    logStartOfFindAllActiveRequest(EntityTypeEnum.DEITY_TYPE);
//    List<DeityType> deityTypes = new ArrayList<>();
//    Iterable<DeityType> iter = deityTypeDao.findByStatusIn(VersionStatus.getLiveStatuses());
//    StreamSupport.stream(iter.spliterator(), false)
//      .forEach(at -> deityTypes.add(at));
//    logEndOfFindAllActiveRequest(EntityTypeEnum.DEITY_TYPE);
//    return new ResponseEntity<>(deityTypes, HttpStatus.OK);
//  }

  @RequestMapping(value = RestUrlPaths.DEITY_TYPE_CONTROLLER_BASE_URL, method = RequestMethod.POST)
  public ResponseEntity<DeityTypeDto> add(@RequestBody DeityTypeDto toBeSaved) {
    LoggingUtils.logStartOfAddRequest(EntityTypeEnum.DEITY_TYPE, toBeSaved);
    DeityTypeDto saved = deityTypeService.add(toBeSaved);
    LoggingUtils.logEndOfAddRequest(EntityTypeEnum.DEITY_TYPE, saved);
    return new ResponseEntity<>(saved, HttpStatus.CREATED);
  }

  @RequestMapping(value = RestUrlPaths.DEITY_TYPE_CONTROLLER_BASE_URL + "/{id}", method = RequestMethod.DELETE)
  public ResponseEntity<DeityTypeDto> delete(@PathVariable Long id) {
    LoggingUtils.logStartOfDeleteRequest(EntityTypeEnum.DEITY_TYPE, id);
    DeityTypeDto deleted = deityTypeService.deleteById(id);
    HttpStatus status;
    if (deleted == null) {
      LoggingUtils.logFoundNoEntityForId(EntityTypeEnum.DEITY_TYPE, id);
      status = HttpStatus.NOT_FOUND;
    } else {
      status = HttpStatus.OK;
    }

    LoggingUtils.logEndOfDeleteRequest(EntityTypeEnum.DEITY_TYPE, deleted);
    return new ResponseEntity<>(deleted, status);
  }

  @RequestMapping(value = RestUrlPaths.DEITY_TYPE_CONTROLLER_BASE_URL, method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<DeityTypeDto> update(@RequestBody DeityTypeDto toBeSaved) {
    LoggingUtils.logStartOfUpdateRequest(EntityTypeEnum.DEITY_TYPE, toBeSaved);
    DeityTypeDto updated = deityTypeService.update(toBeSaved);
    HttpStatus status;
    if (updated == null) {
      status = HttpStatus.BAD_REQUEST;
    } else {
      status = HttpStatus.OK;
    }

    logEndOfUpdateRequest(EntityTypeEnum.DEITY_TYPE, updated);
    return new ResponseEntity<>(updated, status);
  }

}
