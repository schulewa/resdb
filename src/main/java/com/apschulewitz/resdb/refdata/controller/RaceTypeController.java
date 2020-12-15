package com.apschulewitz.resdb.refdata.controller;

import com.apschulewitz.resdb.common.controller.AbstractController;
import com.apschulewitz.resdb.common.model.EntityTypeEnum;
import com.apschulewitz.resdb.config.RestUrlPaths;
import com.apschulewitz.resdb.refdata.model.dto.RaceTypeDto;
import com.apschulewitz.resdb.refdata.service.RaceTypeService;
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
public class RaceTypeController extends AbstractController<RaceTypeDto, Long> {

  private RaceTypeService raceTypeService;

  public RaceTypeController(RaceTypeService raceTypeService) {
    this.raceTypeService = raceTypeService;
  }

  @RequestMapping(value = RestUrlPaths.RACE_TYPE_CONTROLLER_BASE_URL, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<List<RaceTypeDto>> findAll(@RequestBody Boolean onlyActive) {
    logStartOfFindAllRequest(EntityTypeEnum.RACE_TYPE);
    List<RaceTypeDto> raceTypes = raceTypeService.findAll(onlyActive);
    logEndOfFindAllRequest(EntityTypeEnum.RACE_TYPE);
    return new ResponseEntity<>(raceTypes, HttpStatus.OK);
  }

//  @Override
//  public ResponseEntity<List<RaceType>> findAllActive() {
//    logStartOfFindAllActiveRequest(EntityTypeEnum.RACE_TYPE);
//    List<RaceType> raceTypes = new ArrayList<>();
//    Iterable<RaceType> iter = raceTypeDao.findByStatusIn(VersionStatus.getLiveStatuses());
//    StreamSupport.stream(iter.spliterator(), false)
//      .forEach(raceTypes::add);
//    logEndOfFindAllActiveRequest(EntityTypeEnum.RACE_TYPE);
//    return new ResponseEntity<>(raceTypes, HttpStatus.OK);
//  }

  @RequestMapping(value = RestUrlPaths.RACE_TYPE_CONTROLLER_BASE_URL, method = RequestMethod.POST)
  public ResponseEntity<RaceTypeDto> add(@RequestBody RaceTypeDto toBeSaved) {
    logStartOfAddRequest(EntityTypeEnum.RACE_TYPE, toBeSaved);
    RaceTypeDto saved = raceTypeService.add(toBeSaved);
    logEndOfAddRequest(EntityTypeEnum.RACE_TYPE, saved);
    return new ResponseEntity<>(saved, HttpStatus.CREATED);
  }

  @RequestMapping(value = RestUrlPaths.RACE_TYPE_CONTROLLER_BASE_URL + "/{id}", method = RequestMethod.DELETE)
  public ResponseEntity<RaceTypeDto> delete(@PathVariable Long id) {
    logStartOfDeleteRequest(EntityTypeEnum.RACE_TYPE, id);
    RaceTypeDto deleted = raceTypeService.deleteById(id);
    HttpStatus status;
    if (deleted == null) {
      status = HttpStatus.NOT_FOUND;
    } else {
      status = HttpStatus.OK;
    }
    logEndOfDeleteRequest(EntityTypeEnum.RACE_TYPE, deleted);
    return new ResponseEntity<>(deleted, status);
  }

  @RequestMapping(value = RestUrlPaths.RACE_TYPE_CONTROLLER_BASE_URL, method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<RaceTypeDto> update(@RequestBody RaceTypeDto toBeSaved) {
    logStartOfUpdateRequest(EntityTypeEnum.RACE_TYPE, toBeSaved);
    RaceTypeDto updated = raceTypeService.update(toBeSaved);
    HttpStatus status;
    if (updated == null) {
      status = HttpStatus.NOT_FOUND;
    } else {
      status = HttpStatus.OK;
    }
    logEndOfUpdateRequest(EntityTypeEnum.RACE_TYPE, updated);
    return new ResponseEntity<>(updated, status);
  }

}
