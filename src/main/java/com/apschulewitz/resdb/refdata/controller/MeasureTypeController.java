package com.apschulewitz.resdb.refdata.controller;

import com.apschulewitz.resdb.common.controller.AbstractController;
import com.apschulewitz.resdb.common.model.EntityTypeEnum;
import com.apschulewitz.resdb.config.RestUrlPaths;
import com.apschulewitz.resdb.refdata.model.dto.MeasureTypeDto;
import com.apschulewitz.resdb.refdata.service.MeasureTypeService;
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
public class MeasureTypeController extends AbstractController<MeasureTypeDto, Long> {

  private MeasureTypeService measureTypeService;

  public MeasureTypeController(MeasureTypeService measureTypeService) {
    this.measureTypeService = measureTypeService;
  }

  @RequestMapping(value = RestUrlPaths.MEASURE_TYPE_CONTROLLER_BASE_URL, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<List<MeasureTypeDto>> findAll(@RequestBody Boolean onlyActive) {
    logStartOfFindAllRequest(EntityTypeEnum.MEASURE_TYPE);
    List<MeasureTypeDto> measureTypes = measureTypeService.findAll(onlyActive);
    logEndOfFindAllRequest(EntityTypeEnum.MEASURE_TYPE);
    return new ResponseEntity<>(measureTypes, HttpStatus.OK);
  }

//  @Override
//  public ResponseEntity<List<MeasureType>> findAllActive() {
//    logStartOfFindAllActiveRequest(EntityTypeEnum.MEASURE_TYPE);
//    List<MeasureType> measureTypes = new ArrayList<>();
//    Iterable<MeasureType> iter = measureTypeDao.findByStatusIn(VersionStatus.getLiveStatuses());
//    StreamSupport.stream(iter.spliterator(), false)
//      .forEach(measureTypes::add);
//    logEndOfFindAllActiveRequest(EntityTypeEnum.MEASURE_TYPE);
//    return new ResponseEntity<>(measureTypes, HttpStatus.OK);
//  }

  @RequestMapping(value = RestUrlPaths.MEASURE_TYPE_CONTROLLER_BASE_URL, method = RequestMethod.POST)
  public ResponseEntity<MeasureTypeDto> add(@RequestBody MeasureTypeDto toBeSaved) {
    logStartOfAddRequest(EntityTypeEnum.MEASURE_TYPE, toBeSaved);
    MeasureTypeDto saved = measureTypeService.add(toBeSaved);
    logEndOfAddRequest(EntityTypeEnum.MEASURE_TYPE, saved);
    return new ResponseEntity<>(saved, HttpStatus.CREATED);
  }

  @RequestMapping(value = RestUrlPaths.MEASURE_TYPE_CONTROLLER_BASE_URL + "/{id}", method = RequestMethod.DELETE)
  public ResponseEntity<MeasureTypeDto> delete(@PathVariable Long id) {
    logStartOfDeleteRequest(EntityTypeEnum.MEASURE_TYPE, toString());
    MeasureTypeDto deleted = measureTypeService.deleteById(id);
    HttpStatus status;
    if (deleted == null) {
      status = HttpStatus.NOT_FOUND;
    } else {
      status = HttpStatus.OK;
    }
    logEndOfDeleteRequest(EntityTypeEnum.MEASURE_TYPE, deleted);
    return new ResponseEntity<>(deleted, status);
  }

  @RequestMapping(value = RestUrlPaths.MEASURE_TYPE_CONTROLLER_BASE_URL, method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<MeasureTypeDto> update(@RequestBody MeasureTypeDto toBeSaved) {
    logStartOfUpdateRequest(EntityTypeEnum.MEASURE_TYPE, toBeSaved);
    MeasureTypeDto updated = measureTypeService.update(toBeSaved);
    HttpStatus status;
    if (updated == null) {
      status = HttpStatus.NOT_FOUND;
    } else {
      status = HttpStatus.OK;
    }
    logEndOfUpdateRequest(EntityTypeEnum.MEASURE_TYPE, updated);
    return new ResponseEntity<>(updated, status);
  }

}
