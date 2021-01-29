package com.apschulewitz.resdb.refdata.controller;

import com.apschulewitz.resdb.common.controller.AbstractController;
import com.apschulewitz.resdb.common.model.EntityTypeEnum;
import com.apschulewitz.resdb.common.utils.LoggingUtils;
import com.apschulewitz.resdb.config.RestUrlPaths;
import com.apschulewitz.resdb.refdata.model.dto.ArtefactTypeDto;
import com.apschulewitz.resdb.refdata.model.dto.CalendarTypeDto;
import com.apschulewitz.resdb.refdata.service.CalendarTypeService;
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
@Api(value = "Calendar type controller", tags = "CalendarType")
public class CalendarTypeController extends AbstractController<CalendarTypeDto, Long> {

  private CalendarTypeService calendarTypeService;

  public CalendarTypeController(CalendarTypeService calendarTypeService) {
    this.calendarTypeService = calendarTypeService;
  }

  @ApiOperation(
    httpMethod = "GET",
    response = List.class,
    tags = "CalendarType",
    value = "Find all calendar types by active or inactive status")
  @ApiResponses(value = {
    @ApiResponse(code = 200, message = "Successfully executed request to find all calendar types")
  })
  @RequestMapping(value = RestUrlPaths.CALENDAR_TYPE_CONTROLLER_BASE_URL, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<List<CalendarTypeDto>> findAll(@ApiParam(value = "A boolean specifying whether to only include calendar types with an active status",
                                                                  name = "onlyActive",
                                                                  allowableValues = "true, false")
                                                         @RequestParam Boolean onlyActive) {
    LoggingUtils.logStartOfFindAllRequest(EntityTypeEnum.CALENDAR_TYPE);
    List<CalendarTypeDto> calendarTypes = calendarTypeService.findAll(onlyActive);
    LoggingUtils.logEndOfFindAllRequest(EntityTypeEnum.CALENDAR_TYPE);
    return new ResponseEntity<>(calendarTypes, HttpStatus.OK);
  }

  @ApiOperation(
    httpMethod = "POST",
    notes = "Add supplied calendar type. Returns added calendar type",
    response = List.class,
    tags = "CalendarType",
    value = "Add calendar type")
  @ApiResponses(value = {
    @ApiResponse(code = 201, message = "Calendar type successfully added")
  })
  @RequestMapping(value = RestUrlPaths.CALENDAR_TYPE_CONTROLLER_BASE_URL, method = RequestMethod.POST)
  public ResponseEntity<CalendarTypeDto> add(@ApiParam(value = "A JSON value representing an calendar type",
                                                        name = "toBeSaved",
                                                        example = "{\"name\":\"Calendar type name\"}")
                                               @RequestBody CalendarTypeDto toBeSaved) {
    LoggingUtils.logStartOfAddRequest(EntityTypeEnum.CALENDAR_TYPE, toBeSaved);
    CalendarTypeDto saved = calendarTypeService.add(toBeSaved);
    LoggingUtils.logEndOfAddRequest(EntityTypeEnum.CALENDAR_TYPE, saved);
    return new ResponseEntity<>(saved, HttpStatus.CREATED);
  }

  @ApiOperation(
    httpMethod = "DELETE",
    notes = "Marks calendar type as deleted. Returns deleted calendar type",
    response = CalendarTypeDto.class,
    tags = "CalendarType",
    value = "Delete calendar type")
  @ApiResponses(value = {
    @ApiResponse(code = 200, message = "Calendar type marked as deleted"),
    @ApiResponse(code = 404, message = "Calendar type not found or does not have active status")
  })
  @RequestMapping(value = RestUrlPaths.CALENDAR_TYPE_CONTROLLER_BASE_URL + "/{id}", method = RequestMethod.DELETE)
  public ResponseEntity<CalendarTypeDto> delete(@ApiParam(value = "Numeric identifier for calendar type to be marked as deleted",
                                                          name = "id",
                                                          example = "{id: 123}")
                                                  @PathVariable Long id) {
    LoggingUtils.logStartOfDeleteRequest(EntityTypeEnum.CALENDAR_TYPE, id);
    CalendarTypeDto deleted = calendarTypeService.deleteById(id);
    HttpStatus status;
    if (deleted == null) {
      status = HttpStatus.NOT_FOUND;
    } else {
      status = HttpStatus.OK;
    }
    LoggingUtils.logEndOfDeleteRequest(EntityTypeEnum.CALENDAR_TYPE, deleted);
    return new ResponseEntity<>(deleted, status);
  }

  @ApiOperation(
    httpMethod = "PUT",
    notes = "Updates supplied calendar type. Returns updated calendar type",
    response = CalendarTypeDto.class,
    tags = "CalendarType",
    value = "Update calendar type")
  @ApiResponses(value = {
    @ApiResponse(code = 200, message = "Calendar type successfully updated"),
    @ApiResponse(code = 404, message = "Calendar type not found or does not have active status")
  })
  @RequestMapping(value = RestUrlPaths.CALENDAR_TYPE_CONTROLLER_BASE_URL, method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<CalendarTypeDto> update(@ApiParam(
                                                  value = "A JSON value representing an updated calendar type",
                                                  example = "{\"id\":123,\"name\":\"Calendar type name\",\"status\":\"Amend\",\"createdBy\":\"user1\",\"updatedBy\":\"user2\",\"lastUpdated\":{\"dateTime\":{\"date\":{\"year\":2021,\"month\":1,\"day\":9},\"time\":{\"hour\":16,\"minute\":51,\"second\":25,\"nano\":796636000}},\"offset\":{\"totalSeconds\":0},\"zone\":{\"totalSeconds\":0}},\"versionNumber\":2}"
                                              )
                                              @RequestBody CalendarTypeDto toBeSaved) {
    LoggingUtils.logStartOfUpdateRequest(EntityTypeEnum.CALENDAR_TYPE, toBeSaved);
    CalendarTypeDto saved = calendarTypeService.update(toBeSaved);
    LoggingUtils.logEndOfUpdateRequest(EntityTypeEnum.CALENDAR_TYPE, saved);
    return new ResponseEntity<>(saved, HttpStatus.OK);
  }

}
