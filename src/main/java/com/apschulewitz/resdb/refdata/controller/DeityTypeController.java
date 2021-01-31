package com.apschulewitz.resdb.refdata.controller;

import com.apschulewitz.resdb.common.controller.AbstractController;
import com.apschulewitz.resdb.common.model.EntityTypeEnum;
import com.apschulewitz.resdb.common.utils.LoggingUtils;
import com.apschulewitz.resdb.config.RestUrlPaths;
import com.apschulewitz.resdb.refdata.model.dto.CountryDto;
import com.apschulewitz.resdb.refdata.model.dto.DeityTypeDto;
import com.apschulewitz.resdb.refdata.service.DeityTypeService;
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
@Api(value = "Deity type controller", tags = "DeityType")
public class DeityTypeController extends AbstractController<DeityTypeDto, Long> {

  private DeityTypeService deityTypeService;

  public DeityTypeController(DeityTypeService deityTypeService) {
    this.deityTypeService = deityTypeService;
  }

  @ApiOperation(
    httpMethod = "GET",
    response = List.class,
    tags = "DeityType",
    value = "Find all deity types by active or inactive status")
  @ApiResponses(value = {
    @ApiResponse(code = 200, message = "Successfully executed request to find all deity types")
  })
  @RequestMapping(value = RestUrlPaths.DEITY_TYPE_CONTROLLER_BASE_URL, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<List<DeityTypeDto>> findAll(@ApiParam(value = "A boolean specifying whether to only include deity types with an active status",
                                                              name = "onlyActive",
                                                              allowableValues = "true, false")
                                                      @RequestParam Boolean onlyActive) {
    LoggingUtils.logStartOfFindAllRequest(EntityTypeEnum.DEITY_TYPE);
    List<DeityTypeDto> deityTypes = deityTypeService.findAll(onlyActive);
    LoggingUtils.logEndOfFindAllRequest(EntityTypeEnum.DEITY_TYPE);
    return new ResponseEntity<>(deityTypes, HttpStatus.OK);
  }

  @ApiOperation(
    httpMethod = "POST",
    notes = "Add supplied deity type. Returns added deity type",
    response = List.class,
    tags = "DeityType",
    value = "Add deity type")
  @ApiResponses(value = {
    @ApiResponse(code = 201, message = "Deity type successfully added"),
    @ApiResponse(code = 500, message = "Failure adding deity type")
  })
  @RequestMapping(value = RestUrlPaths.DEITY_TYPE_CONTROLLER_BASE_URL, method = RequestMethod.POST)
  public ResponseEntity<DeityTypeDto> add(@ApiParam(value = "A JSON value representing a deity type",
                                                    name = "toBeSaved",
                                                    example = "{\"name\":\"Deity type\"}")
                                            @RequestBody DeityTypeDto toBeSaved) {
    LoggingUtils.logStartOfAddRequest(EntityTypeEnum.DEITY_TYPE, toBeSaved);
    DeityTypeDto saved = deityTypeService.add(toBeSaved);
    LoggingUtils.logEndOfAddRequest(EntityTypeEnum.DEITY_TYPE, saved);
    return new ResponseEntity<>(saved, HttpStatus.CREATED);
  }

  @ApiOperation(
    httpMethod = "DELETE",
    notes = "Marks deity type as deleted. Returns deleted deity type",
    response = DeityTypeDto.class,
    tags = "DeityType",
    value = "Delete deity type")
  @ApiResponses(value = {
    @ApiResponse(code = 200, message = "Deity type marked as deleted"),
    @ApiResponse(code = 404, message = "Deity type not found or does not have active status")
  })
  @RequestMapping(value = RestUrlPaths.DEITY_TYPE_CONTROLLER_BASE_URL + "/{id}", method = RequestMethod.DELETE)
  public ResponseEntity<DeityTypeDto> delete(@ApiParam(value = "Numeric identifier for deity type to be marked as deleted",
                                                        name = "id",
                                                        example = "{id: 123}")
                                               @PathVariable Long id) {
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

  @ApiOperation(
    httpMethod = "PUT",
    notes = "Updates supplied deity type. Returns updated deity type",
    response = DeityTypeDto.class,
    tags = "DeityType",
    value = "Update deity type")
  @ApiResponses(value = {
    @ApiResponse(code = 200, message = "Deity type successfully updated"),
    @ApiResponse(code = 404, message = "Deity type not found or does not have active status")
  })
  @RequestMapping(value = RestUrlPaths.DEITY_TYPE_CONTROLLER_BASE_URL, method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<DeityTypeDto> update(@ApiParam(
                                                value = "A JSON value representing an updated deity type",
                                                example = "{\"id\":123,\"name\":\"Deity type\",\"status\":\"Amend\",\"createdBy\":\"user1\",\"updatedBy\":\"user2\",\"lastUpdated\":{\"dateTime\":{\"date\":{\"year\":2021,\"month\":1,\"day\":9},\"time\":{\"hour\":16,\"minute\":51,\"second\":25,\"nano\":796636000}},\"offset\":{\"totalSeconds\":0},\"zone\":{\"totalSeconds\":0}},\"versionNumber\":2}")
                                               @RequestBody DeityTypeDto toBeSaved) {
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
