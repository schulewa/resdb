package com.apschulewitz.resdb.refdata.controller;

import com.apschulewitz.resdb.common.controller.AbstractController;
import com.apschulewitz.resdb.common.model.EntityTypeEnum;
import com.apschulewitz.resdb.config.RestUrlPaths;
import com.apschulewitz.resdb.refdata.model.dto.LanguageGroupDto;
import com.apschulewitz.resdb.refdata.model.dto.MeasureTypeDto;
import com.apschulewitz.resdb.refdata.service.MeasureTypeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by adrianschulewitz on 22/04/2017.
 */
@RestController()
@Slf4j
@Api(value = "Measure type controller", tags = {"MeasureType"})
public class MeasureTypeController extends AbstractController<MeasureTypeDto, Long> {

  private MeasureTypeService measureTypeService;

  public MeasureTypeController(MeasureTypeService measureTypeService) {
    this.measureTypeService = measureTypeService;
  }

  @ApiOperation(
    value = "Find all measure types",
    httpMethod = "GET",
    notes = "Finds all measure types, active and inactive",
    response = List.class
  )
  @ApiResponses(value = {
    @ApiResponse(code = 200, message = "Request to find all measure types completed successfully.")
  })
  @RequestMapping(value = RestUrlPaths.MEASURE_TYPE_CONTROLLER_BASE_URL, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<List<MeasureTypeDto>> findAll(@ApiParam(name = "onlyActive",
                                                                allowableValues = "true, false",
                                                                required = true,
                                                                type = "Boolean",
                                                                value = "A boolean. True specifies to only include active measure types, false to exclude inactive measure types.")
                                                        @RequestParam Boolean onlyActive) {
    logStartOfFindAllRequest(EntityTypeEnum.MEASURE_TYPE);
    List<MeasureTypeDto> measureTypes = measureTypeService.findAll(onlyActive);
    logEndOfFindAllRequest(EntityTypeEnum.MEASURE_TYPE);
    return new ResponseEntity<>(measureTypes, HttpStatus.OK);
  }

  @ApiOperation(
    value = "Add new measure type",
    notes = "Add new measure type. Returns the saved measure type.",
    httpMethod = "POST",
    response = MeasureTypeDto.class
  )
  @ApiResponses(value = {
    @ApiResponse(code = 200, message = "Request to add the specified measure type completed successfully.")
  })
  @RequestMapping(value = RestUrlPaths.MEASURE_TYPE_CONTROLLER_BASE_URL, method = RequestMethod.POST)
  public ResponseEntity<MeasureTypeDto> add(@ApiParam(name = "toBeSaved",
                                                      required = true,
                                                      type = "MeasureTypeDto",
                                                      value = "A non-null instance of a MeasureTypeDto")
                                              @RequestBody MeasureTypeDto toBeSaved) {
    logStartOfAddRequest(EntityTypeEnum.MEASURE_TYPE, toBeSaved);
    MeasureTypeDto saved = measureTypeService.add(toBeSaved);
    logEndOfAddRequest(EntityTypeEnum.MEASURE_TYPE, saved);
    return new ResponseEntity<>(saved, HttpStatus.CREATED);
  }

  @ApiOperation(
    value = "Delete a measure type",
    notes = "Delete the measure type matching the identifier by marking it as cancelled(inactive).",
    httpMethod = "DELETE",
    response = MeasureTypeDto.class)
  @RequestMapping(value = RestUrlPaths.MEASURE_TYPE_CONTROLLER_BASE_URL + "/{id}", method = RequestMethod.DELETE)
  public ResponseEntity<MeasureTypeDto> delete(@ApiParam(name = "id",
                                                          required = true,
                                                          type = "Long",
                                                          value = "A positive Long number identifying the measure type to be deleted")
                                                 @PathVariable Long id) {
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

  @ApiOperation(
    value = "Update a measure type",
    notes = "Update the measure type using the supplied data. The identifier must identify an active measure type",
    httpMethod = "PUT",
    response = MeasureTypeDto.class)
  @RequestMapping(value = RestUrlPaths.MEASURE_TYPE_CONTROLLER_BASE_URL, method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<MeasureTypeDto> update(@ApiParam(name = "toBeSaved",
                                                          required = true,
                                                          type = "MeasureTypeDto",
                                                          value = "A non-null MeasureTypeDto")
                                                 @RequestBody MeasureTypeDto toBeSaved) {
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
