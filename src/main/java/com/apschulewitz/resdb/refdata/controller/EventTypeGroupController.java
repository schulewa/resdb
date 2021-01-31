package com.apschulewitz.resdb.refdata.controller;

import com.apschulewitz.resdb.common.controller.AbstractController;
import com.apschulewitz.resdb.common.model.EntityTypeEnum;
import com.apschulewitz.resdb.config.RestUrlPaths;
import com.apschulewitz.resdb.refdata.model.dto.EntityTypeDto;
import com.apschulewitz.resdb.refdata.model.dto.EventTypeGroupDto;
import com.apschulewitz.resdb.refdata.service.EventTypeGroupService;
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
@Api(value = "Event type group controller", tags = "EventTypeGroup")
public class EventTypeGroupController extends AbstractController<EventTypeGroupDto, Long> {

  private EventTypeGroupService eventTypeGroupService;

  public EventTypeGroupController(EventTypeGroupService eventTypeGroupService) {
    this.eventTypeGroupService = eventTypeGroupService;
  }

  @ApiOperation(
    httpMethod = "GET",
    response = List.class,
    tags = "EntityType",
    value = "Find all event type groups by active or inactive status")
  @ApiResponses(value = {
    @ApiResponse(code = 200, message = "Successfully executed request to find all event type groups")
  })
  @RequestMapping(value = RestUrlPaths.EVENT_TYPE_GROUP_CONTROLLER_BASE_URL, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<List<EventTypeGroupDto>> findAll(@ApiParam(value = "A boolean specifying whether to only include event type groups with an active status",
                                                                    name = "onlyActive",
                                                                    allowableValues = "true, false")
                                                           @RequestParam Boolean onlyActive) {
    logStartOfFindAllRequest(EntityTypeEnum.EVENT_TYPE_GROUP);
    List<EventTypeGroupDto> eventTypeGroups = eventTypeGroupService.findAll(onlyActive);
    logEndOfFindAllRequest(EntityTypeEnum.EVENT_TYPE_GROUP);
    return new ResponseEntity<>(eventTypeGroups, HttpStatus.OK);
  }

  @ApiOperation(
    httpMethod = "POST",
    notes = "Add supplied event type group. Returns added event type group",
    response = List.class,
    tags = "EventTypeGroup",
    value = "Add event type group")
  @ApiResponses(value = {
    @ApiResponse(code = 201, message = "Event type group successfully added"),
    @ApiResponse(code = 500, message = "Failure adding event type group")
  })
  @RequestMapping(value = RestUrlPaths.EVENT_TYPE_GROUP_CONTROLLER_BASE_URL, method = RequestMethod.POST)
  public ResponseEntity<EventTypeGroupDto> add(@ApiParam(value = "A JSON value representing a event type group",
                                                          name = "toBeSaved",
                                                          example = "{\"name\":\"Event type group\"}")
                                                 @RequestBody EventTypeGroupDto toBeSaved) {
    logStartOfAddRequest(EntityTypeEnum.EVENT_TYPE_GROUP, toBeSaved);
    EventTypeGroupDto saved = eventTypeGroupService.add(toBeSaved);
    logEndOfAddRequest(EntityTypeEnum.EVENT_TYPE_GROUP, saved);
    return new ResponseEntity<>(saved, HttpStatus.CREATED);
  }

  @ApiOperation(
    httpMethod = "DELETE",
    notes = "Marks event type group as deleted. Returns deleted event type group",
    response = EventTypeGroupDto.class,
    tags = "EventTypeGroup",
    value = "Delete event type group")
  @ApiResponses(value = {
    @ApiResponse(code = 200, message = "Event type group marked as deleted"),
    @ApiResponse(code = 404, message = "Event type group not found or does not have active status")
  })
  @RequestMapping(value = RestUrlPaths.EVENT_TYPE_GROUP_CONTROLLER_BASE_URL + "/{id}", method = RequestMethod.DELETE)
  public ResponseEntity<EventTypeGroupDto> delete(@ApiParam(value = "Numeric identifier for event type group to be marked as deleted",
                                                            name = "id",
                                                            example = "{id: 123}")
                                                    @PathVariable Long id) {
    logStartOfDeleteRequest(EntityTypeEnum.EVENT_TYPE_GROUP, id);
    EventTypeGroupDto deleted = eventTypeGroupService.deleteById(id);
    HttpStatus status;
    if (deleted == null) {
      status = HttpStatus.NOT_FOUND;
    } else {
      status = HttpStatus.OK;
    }
    logEndOfDeleteRequest(EntityTypeEnum.EVENT_TYPE_GROUP, deleted);
    return new ResponseEntity<>(deleted, status);
  }

  @ApiOperation(
    httpMethod = "PUT",
    notes = "Updates supplied event type group. Returns updated event type group",
    response = EventTypeGroupDto.class,
    tags = "EventTypeGroup",
    value = "Update event type group")
  @ApiResponses(value = {
    @ApiResponse(code = 200, message = "Event type group successfully updated"),
    @ApiResponse(code = 404, message = "Event type group not found or does not have active status")
  })
  @RequestMapping(value = RestUrlPaths.EVENT_TYPE_GROUP_CONTROLLER_BASE_URL, method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<EventTypeGroupDto> update(@ApiParam(value = "A JSON value representing an updated event type group",
    example = "{\"id\":123,\"name\":\"Event type group\",\"status\":\"Amend\",\"createdBy\":\"user1\",\"updatedBy\":\"user2\",\"lastUpdated\":{\"dateTime\":{\"date\":{\"year\":2021,\"month\":1,\"day\":9},\"time\":{\"hour\":16,\"minute\":51,\"second\":25,\"nano\":796636000}},\"offset\":{\"totalSeconds\":0},\"zone\":{\"totalSeconds\":0}},\"versionNumber\":2}")
                                                    @RequestBody EventTypeGroupDto toBeSaved) {
    logStartOfUpdateRequest(EntityTypeEnum.EVENT_TYPE_GROUP, toBeSaved);
    EventTypeGroupDto updated = eventTypeGroupService.update(toBeSaved);
    HttpStatus status;
    if (updated == null) {
      status = HttpStatus.NOT_FOUND;
    } else {
      status = HttpStatus.OK;
    }
    logEndOfUpdateRequest(EntityTypeEnum.EVENT_TYPE_GROUP, updated);
    return new ResponseEntity<>(updated, status);
  }

}
