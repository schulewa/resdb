package com.apschulewitz.resdb.refdata.controller;

import com.apschulewitz.resdb.common.controller.AbstractController;
import com.apschulewitz.resdb.common.model.EntityTypeEnum;
import com.apschulewitz.resdb.common.model.entity.VersionStatus;
import com.apschulewitz.resdb.config.RestUrlPaths;
import com.apschulewitz.resdb.refdata.model.dao.EntityTypeDao;
import com.apschulewitz.resdb.refdata.model.dto.DeityTypeDto;
import com.apschulewitz.resdb.refdata.model.dto.EntityTypeDto;
import com.apschulewitz.resdb.refdata.model.entity.DeityType;
import com.apschulewitz.resdb.refdata.model.entity.EntityType;
import com.apschulewitz.resdb.refdata.service.EntityTypeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
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
@Api(value = "Entity type controller", tags = "EntityType")
public class EntityTypeController extends AbstractController<EntityTypeDto, Long> {

  private EntityTypeService entityTypeService;

  public EntityTypeController(EntityTypeService entityTypeService) {
    this.entityTypeService = entityTypeService;
  }

  @ApiOperation(
    httpMethod = "GET",
    response = List.class,
    tags = "EntityType",
    value = "Find all entity types by active or inactive status")
  @ApiResponses(value = {
    @ApiResponse(code = 200, message = "Successfully executed request to find all entity types")
  })
  @RequestMapping(value = RestUrlPaths.ENTITY_TYPE_CONTROLLER_BASE_URL, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<List<EntityTypeDto>> findAll(@ApiParam(
                                                          value = "A boolean specifying whether to only include entity types with an active status",
                                                          name = "onlyActive",
                                                          allowableValues = "true, false")
                                                       @RequestParam Boolean onlyActive) {
    logStartOfFindAllRequest(EntityTypeEnum.ENTITY_TYPE);
    List<EntityTypeDto> entityTypes = entityTypeService.findAll(onlyActive);
    logEndOfFindAllRequest(EntityTypeEnum.ENTITY_TYPE);
    return new ResponseEntity<>(entityTypes, HttpStatus.OK);
  }

  @ApiOperation(
    httpMethod = "POST",
    notes = "Add supplied entity type. Returns added entity type",
    response = List.class,
    tags = "EntityType",
    value = "Add entity type")
  @ApiResponses(value = {
    @ApiResponse(code = 201, message = "Entity type successfully added"),
    @ApiResponse(code = 500, message = "Failure adding entity type")
  })
  @RequestMapping(value = RestUrlPaths.ENTITY_TYPE_CONTROLLER_BASE_URL, method = RequestMethod.POST)
  public ResponseEntity<EntityTypeDto> add(@ApiParam(value = "A JSON value representing a entity type",
                                                      name = "toBeSaved",
                                                      example = "{\"name\":\"Entity type\"}")
                                             @RequestBody EntityTypeDto toBeSaved) {
    logStartOfAddRequest(EntityTypeEnum.ENTITY_TYPE, toBeSaved);
    EntityTypeDto saved = entityTypeService.add(toBeSaved);
    logEndOfAddRequest(EntityTypeEnum.ENTITY_TYPE, saved);
    return new ResponseEntity<>(saved, HttpStatus.CREATED);
  }

  @ApiOperation(
    httpMethod = "DELETE",
    notes = "Marks entity type as deleted. Returns deleted entity type",
    response = EntityTypeDto.class,
    tags = "EntityType",
    value = "Delete entity type")
  @ApiResponses(value = {
    @ApiResponse(code = 200, message = "Entity type marked as deleted"),
    @ApiResponse(code = 404, message = "Entity type not found or does not have active status")
  })
  @RequestMapping(value = RestUrlPaths.ENTITY_TYPE_CONTROLLER_BASE_URL + "/{id}", method = RequestMethod.DELETE)
  public ResponseEntity<EntityTypeDto> delete(@ApiParam(value = "Numeric identifier for entity type to be marked as deleted",
                                                        name = "id",
                                                        example = "{id: 123}")
                                                @PathVariable Long id) {
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

  @ApiOperation(
    httpMethod = "PUT",
    notes = "Updates supplied entity type. Returns updated entity type",
    response = EntityTypeDto.class,
    tags = "EntityType",
    value = "Update entity type")
  @ApiResponses(value = {
    @ApiResponse(code = 200, message = "Entity type successfully updated"),
    @ApiResponse(code = 404, message = "Entity type not found or does not have active status")
  })
  @RequestMapping(value = RestUrlPaths.ENTITY_TYPE_CONTROLLER_BASE_URL, method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<EntityTypeDto> update(@ApiParam(value = "A JSON value representing an updated entity type",
                                                        example = "{\"id\":123,\"name\":\"Entity type\",\"status\":\"Amend\",\"createdBy\":\"user1\",\"updatedBy\":\"user2\",\"lastUpdated\":{\"dateTime\":{\"date\":{\"year\":2021,\"month\":1,\"day\":9},\"time\":{\"hour\":16,\"minute\":51,\"second\":25,\"nano\":796636000}},\"offset\":{\"totalSeconds\":0},\"zone\":{\"totalSeconds\":0}},\"versionNumber\":2}")
                                                @RequestBody EntityTypeDto toBeSaved) {
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
