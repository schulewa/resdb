package com.apschulewitz.resdb.refdata.controller;

import com.apschulewitz.resdb.common.controller.AbstractController;
import com.apschulewitz.resdb.common.model.EntityTypeEnum;
import com.apschulewitz.resdb.common.utils.LoggingUtils;
import com.apschulewitz.resdb.config.RestUrlPaths;
import com.apschulewitz.resdb.refdata.model.dto.ArtefactGroupDto;
import com.apschulewitz.resdb.refdata.model.dto.ArtefactTypeDto;
import com.apschulewitz.resdb.refdata.service.ArtefactTypeService;
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
@Api(value = "Artefact type controller", tags = "ArtefactType")
public class ArtefactTypeController extends AbstractController<ArtefactTypeDto, Long> {

  private ArtefactTypeService artefactTypeService;

  public ArtefactTypeController(ArtefactTypeService artefactTypeService) {
    this.artefactTypeService = artefactTypeService;
  }

  @ApiOperation(
    httpMethod = "GET",
    response = List.class,
    tags = "ArtefactType",
    value = "Find all artefact types by active or inactive status")
  @ApiResponses(value = {
    @ApiResponse(code = 200, message = "Successfully executed request to find all artefact types")
  })
  @RequestMapping(value = RestUrlPaths.ARTEFACT_TYPE_CONTROLLER_BASE_URL, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<List<ArtefactTypeDto>> findAll(@ApiParam(value = "A boolean specifying whether to only include artefact types with an active status",
                                                      name = "onlyActive",
                                                      allowableValues = "true, false")
                                                      @RequestParam(name = "onlyActive") Boolean onlyActive) {
    LoggingUtils.logStartOfFindAllRequest(EntityTypeEnum.ARTEFACT_TYPE);
    List<ArtefactTypeDto> artefactTypes = artefactTypeService.findAll(onlyActive);
    LoggingUtils.logEndOfFindAllRequest(EntityTypeEnum.ARTEFACT_TYPE);
    return new ResponseEntity<>(artefactTypes, HttpStatus.OK);
  }

  @ApiOperation(
    httpMethod = "POST",
    notes = "Add supplied artefact type. Returns added artefact type",
    response = List.class,
    tags = "ArtefactType",
    value = "Add artefact type")
  @ApiResponses(value = {
    @ApiResponse(code = 201, message = "Artefact type successfully added")
  })
  @RequestMapping(value = RestUrlPaths.ARTEFACT_TYPE_CONTROLLER_BASE_URL, method = RequestMethod.POST)
  public ResponseEntity<ArtefactTypeDto> add(@ApiParam(value = "A JSON value representing an artefact group",
                                            name = "toBeSaved",
                                            example = "{\"name\":\"Artefact type name\"}")
                                               @RequestParam ArtefactTypeDto toBeSaved) {
    LoggingUtils.logStartOfAddRequest(EntityTypeEnum.ARTEFACT_TYPE, toBeSaved);
    ArtefactTypeDto saved = artefactTypeService.add(toBeSaved);
    LoggingUtils.logEndOfAddRequest(EntityTypeEnum.ARTEFACT_TYPE, saved);
    return new ResponseEntity<>(saved, HttpStatus.CREATED);
  }

  @ApiOperation(
    httpMethod = "DELETE",
    notes = "Marks artefact type as deleted. Returns deleted artefact type",
    response = ArtefactTypeDto.class,
    tags = "ArtefactType",
    value = "Delete artefact type")
  @ApiResponses(value = {
    @ApiResponse(code = 200, message = "Artefact type marked as deleted"),
    @ApiResponse(code = 404, message = "Artefact type not found or does not have active status")
  })
  @RequestMapping(value = RestUrlPaths.ARTEFACT_TYPE_CONTROLLER_BASE_URL + "/{id}", method = RequestMethod.DELETE)
  public ResponseEntity<ArtefactTypeDto> delete(@ApiParam(value = "Numeric identifier for artefact type to be marked as deleted",
                                                  name = "id",
                                                  example = "{id: 123}")
                                                  @PathVariable Long id) {
    LoggingUtils.logStartOfDeleteRequest(EntityTypeEnum.ARTEFACT_TYPE, id);
    ArtefactTypeDto deleted = artefactTypeService.deleteById(id);
    HttpStatus status;
    if (deleted == null) {
      status = HttpStatus.NOT_FOUND;
    } else {
      status = HttpStatus.OK;
    }
    LoggingUtils.logEndOfDeleteRequest(EntityTypeEnum.ARTEFACT_TYPE, deleted);
    return new ResponseEntity<>(deleted, status);
  }

  @ApiOperation(
    httpMethod = "PUT",
    notes = "Updates supplied artefact type. Returns updated artefact type",
    response = ArtefactTypeDto.class,
    tags = "ArtefactType",
    value = "Update artefact type")
  @ApiResponses(value = {
    @ApiResponse(code = 200, message = "Artefact type successfully updated"),
    @ApiResponse(code = 404, message = "Artefact type not found or does not have active status")
  })
  @RequestMapping(value = RestUrlPaths.ARTEFACT_TYPE_CONTROLLER_BASE_URL, method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<ArtefactTypeDto> update(
              @ApiParam(value = "A JSON value representing an updated artefact type",
                example = "{\"id\":123,\"name\":\"Artefact type name\",\"status\":\"Amend\",\"createdBy\":\"user1\",\"updatedBy\":\"user2\",\"lastUpdated\":{\"dateTime\":{\"date\":{\"year\":2021,\"month\":1,\"day\":9},\"time\":{\"hour\":16,\"minute\":51,\"second\":25,\"nano\":796636000}},\"offset\":{\"totalSeconds\":0},\"zone\":{\"totalSeconds\":0}},\"versionNumber\":2}")
              @RequestBody ArtefactTypeDto toBeSaved) {
    LoggingUtils.logStartOfUpdateRequest(EntityTypeEnum.ARTEFACT_TYPE, toBeSaved);
    ArtefactTypeDto saved = artefactTypeService.update(toBeSaved);
    LoggingUtils.logEndOfUpdateRequest(EntityTypeEnum.ARTEFACT_TYPE, saved);
    return new ResponseEntity<>(saved, HttpStatus.OK);
  }

}
