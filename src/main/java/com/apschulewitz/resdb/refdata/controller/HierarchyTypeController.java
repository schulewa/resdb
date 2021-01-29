package com.apschulewitz.resdb.refdata.controller;

import com.apschulewitz.resdb.common.controller.AbstractController;
import com.apschulewitz.resdb.common.model.EntityTypeEnum;
import com.apschulewitz.resdb.config.RestUrlPaths;
import com.apschulewitz.resdb.refdata.model.dto.EventTypeGroupDto;
import com.apschulewitz.resdb.refdata.model.dto.HierarchyTypeDto;
import com.apschulewitz.resdb.refdata.service.HierarchyTypeService;
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
@Api(value = "Hierarchy type controller", tags = "HierarchyType")
public class HierarchyTypeController extends AbstractController<HierarchyTypeDto, Long> {

  private HierarchyTypeService hierarchyTypeService;

  public HierarchyTypeController(HierarchyTypeService hierarchyTypeService) {
    this.hierarchyTypeService = hierarchyTypeService;
  }

  @ApiOperation(
    httpMethod = "GET",
    response = List.class,
    tags = "HierarchyType",
    value = "Find all hierarchy types by active or inactive status")
  @ApiResponses(value = {
    @ApiResponse(code = 200, message = "Successfully executed request to find all hierarchy types")
  })
  @RequestMapping(value = RestUrlPaths.HIERARCHY_TYPE_CONTROLLER_BASE_URL, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<List<HierarchyTypeDto>> findAll(@ApiParam(value = "A boolean specifying whether to only include hierarchy types with an active status",
                                                                  name = "onlyActive",
                                                                  allowableValues = "true, false")
                                                          @RequestParam Boolean onlyActive) {
    logStartOfFindAllRequest(EntityTypeEnum.HIERARCHY_TYPE);
    List<HierarchyTypeDto> hierarchyTypes = hierarchyTypeService.findAll(onlyActive);
    logEndOfFindAllRequest(EntityTypeEnum.HIERARCHY_TYPE);
    return new ResponseEntity<>(hierarchyTypes, HttpStatus.OK);
  }

  @ApiOperation(
    httpMethod = "POST",
    notes = "Add supplied hierarchy type. Returns added hierarchy type",
    response = List.class,
    tags = "HierarchyType",
    value = "Add hierarchy type")
  @ApiResponses(value = {
    @ApiResponse(code = 201, message = "Hierarchy type successfully added"),
    @ApiResponse(code = 500, message = "Failure adding hierarchy type")
  })
  @RequestMapping(value = RestUrlPaths.HIERARCHY_TYPE_CONTROLLER_BASE_URL, method = RequestMethod.POST)
  public ResponseEntity<HierarchyTypeDto> add(@ApiParam(value = "A JSON value representing a hierarchy type",
                                                        name = "toBeSaved",
                                                        example = "{\"name\":\"Hierarchy type \"}")
                                                @RequestBody HierarchyTypeDto toBeSaved) {
    logStartOfAddRequest(EntityTypeEnum.HIERARCHY_TYPE, toBeSaved);
    HierarchyTypeDto saved = hierarchyTypeService.add(toBeSaved);
    logEndOfAddRequest(EntityTypeEnum.HIERARCHY_TYPE, saved);
    return new ResponseEntity<>(saved, HttpStatus.CREATED);
  }

  @ApiOperation(
    httpMethod = "DELETE",
    notes = "Marks hierarchy type as deleted. Returns deleted hierarchy type",
    response = HierarchyTypeDto.class,
    tags = "HierarchyType",
    value = "Delete hierarchy type")
  @ApiResponses(value = {
    @ApiResponse(code = 200, message = "Hierarchy type marked as deleted"),
    @ApiResponse(code = 404, message = "Hierarchy type not found or does not have active status")
  })
  @RequestMapping(value = RestUrlPaths.HIERARCHY_TYPE_CONTROLLER_BASE_URL + "/{id}", method = RequestMethod.DELETE)
  public ResponseEntity<HierarchyTypeDto> delete(@ApiParam(value = "Numeric identifier for hierarchy type to be marked as deleted",
                                                            name = "id",
                                                            example = "{id: 123}")
                                                   @PathVariable Long id) {
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

  @ApiOperation(
    httpMethod = "PUT",
    notes = "Updates supplied hierarchy type. Returns updated hierarchy type",
    response = HierarchyTypeDto.class,
    tags = "HierarchyType",
    value = "Update hierarchy type")
  @ApiResponses(value = {
    @ApiResponse(code = 200, message = "Hierarchy type successfully updated"),
    @ApiResponse(code = 404, message = "Hierarchy type not found or does not have active status")
  })
  @RequestMapping(value = RestUrlPaths.HIERARCHY_TYPE_CONTROLLER_BASE_URL, method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<HierarchyTypeDto> update(@ApiParam(value = "A JSON value representing an updated hierarchy type",
                                                            example = "{\"id\":123,\"name\":\"Hierarchy type\",\"status\":\"Amend\",\"createdBy\":\"user1\",\"updatedBy\":\"user2\",\"lastUpdated\":{\"dateTime\":{\"date\":{\"year\":2021,\"month\":1,\"day\":9},\"time\":{\"hour\":16,\"minute\":51,\"second\":25,\"nano\":796636000}},\"offset\":{\"totalSeconds\":0},\"zone\":{\"totalSeconds\":0}},\"versionNumber\":2}")
                                                   @RequestBody HierarchyTypeDto toBeSaved) {
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
