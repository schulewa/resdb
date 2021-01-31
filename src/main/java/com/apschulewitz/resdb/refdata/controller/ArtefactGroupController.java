package com.apschulewitz.resdb.refdata.controller;

import com.apschulewitz.resdb.common.controller.AbstractController;
import com.apschulewitz.resdb.common.model.EntityTypeEnum;
import com.apschulewitz.resdb.common.utils.LoggingUtils;
import com.apschulewitz.resdb.config.RestUrlPaths;
import com.apschulewitz.resdb.refdata.model.dto.ArtefactGroupDto;
import com.apschulewitz.resdb.refdata.service.ArtefactGroupService;
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
@Api(value = "Artefact group controller", tags = "ArtefactGroup")
public class ArtefactGroupController extends AbstractController<ArtefactGroupDto, Long> {

  private ArtefactGroupService artefactGroupService;

  public ArtefactGroupController(ArtefactGroupService artefactGroupService) {
    this.artefactGroupService = artefactGroupService;
  }

  @ApiOperation(
    httpMethod = "GET",
    response = List.class,
    tags = "ArtefactGroup",
    value = "Find all artefact groups by active or inactive status")
  @ApiResponses(value = {
    @ApiResponse(code = 200, message = "Successfully executed request to find all artefact groups")
  })
  @RequestMapping(value = RestUrlPaths.ARTEFACT_GROUP_CONTROLLER_BASE_URL, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<List<ArtefactGroupDto>> findAll(@ApiParam(value = "A boolean specifying whether to only include artefact groups with an active status",
                                                                  name = "onlyActive",
                                                                  allowableValues = "true, false")
                                                          @RequestParam(name = "onlyActive") Boolean onlyActive) {
    LoggingUtils.logStartOfFindAllRequest(EntityTypeEnum.ARTEFACT_GROUP);
    List<ArtefactGroupDto> artefactGroups = artefactGroupService.findAll(onlyActive);
    LoggingUtils.logEndOfFindAllRequest(EntityTypeEnum.ARTEFACT_GROUP);
    return new ResponseEntity<>(artefactGroups, HttpStatus.OK);
  }

  @ApiOperation(
    httpMethod = "POST",
    notes = "Add supplied artefact group. Returns added artefact group",
    response = List.class,
    tags = "ArtefactGroup",
    value = "Add artefact group")
  @ApiResponses(value = {
    @ApiResponse(code = 201, message = "Artefact group successfully added")
  })
  @RequestMapping(value = RestUrlPaths.ARTEFACT_GROUP_CONTROLLER_BASE_URL, method = RequestMethod.POST)
  public ResponseEntity<ArtefactGroupDto> add(@ApiParam(value = "A JSON value representing an artefact group",
                                                        name = "toBeSaved",
                                                        example = "{\"name\":\"Artefact group name\"}")
                                                @RequestBody ArtefactGroupDto toBeSaved) {
    LoggingUtils.logStartOfAddRequest(EntityTypeEnum.ARTEFACT_GROUP, toBeSaved);
    ArtefactGroupDto saved = artefactGroupService.add(toBeSaved);
    LoggingUtils.logEndOfAddRequest(EntityTypeEnum.ARTEFACT_GROUP, saved);
    return new ResponseEntity<>(saved, HttpStatus.CREATED);
  }

  @ApiOperation(
    httpMethod = "DELETE",
    notes = "Marks artefact group as deleted. Returns deleted artefact group",
    response = ArtefactGroupDto.class,
    tags = "ArtefactGroup",
    value = "Delete artefact group")
  @ApiResponses(value = {
    @ApiResponse(code = 200, message = "Artefact group marked as deleted"),
    @ApiResponse(code = 404, message = "Artefact group not found or does not have active status")
  })
  @RequestMapping(value = RestUrlPaths.ARTEFACT_GROUP_CONTROLLER_BASE_URL + "/{id}", method = RequestMethod.DELETE)
  public ResponseEntity<ArtefactGroupDto> delete(@ApiParam(value = "Numeric identifier for artefact group to be marked as deleted",
                                                            name = "id",
                                                            example = "{id: 123}")
                                                   @PathVariable Long id) {
    LoggingUtils.logStartOfDeleteRequest(EntityTypeEnum.ARTEFACT_GROUP, id);
    ArtefactGroupDto deleted = artefactGroupService.deleteById(id);
    HttpStatus status;
    if (deleted == null) {
      status = HttpStatus.NOT_FOUND;
    } else {
      status = HttpStatus.OK;
    }
    LoggingUtils.logEndOfDeleteRequest(EntityTypeEnum.ARTEFACT_GROUP, deleted);
    return new ResponseEntity<>(deleted, status);
  }

  @ApiOperation(
    httpMethod = "PUT",
    notes = "Updates supplied artefact group. Returns updated artefact group",
    response = ArtefactGroupDto.class,
    tags = "ArtefactGroup",
    value = "Update artefact group")
  @ApiResponses(value = {
    @ApiResponse(code = 200, message = "Artefact group successfully updated"),
    @ApiResponse(code = 404, message = "Artefact group not found or does not have active status")
  })
  @RequestMapping(value = RestUrlPaths.ARTEFACT_GROUP_CONTROLLER_BASE_URL, method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<ArtefactGroupDto> update(@ApiParam(value = "A JSON value representing an updated artefact group",
    example = "{\"id\":123,\"name\":\"Home address\",\"status\":\"Amend\",\"createdBy\":\"user1\",\"updatedBy\":\"user2\",\"lastUpdated\":{\"dateTime\":{\"date\":{\"year\":2021,\"month\":1,\"day\":9},\"time\":{\"hour\":16,\"minute\":51,\"second\":25,\"nano\":796636000}},\"offset\":{\"totalSeconds\":0},\"zone\":{\"totalSeconds\":0}},\"versionNumber\":2}")
                                                   @RequestBody ArtefactGroupDto toBeSaved) {
    LoggingUtils.logStartOfUpdateRequest(EntityTypeEnum.ARTEFACT_GROUP, toBeSaved);
    ArtefactGroupDto saved = artefactGroupService.update(toBeSaved);
    LoggingUtils.logEndOfUpdateRequest(EntityTypeEnum.ARTEFACT_GROUP, saved);
    return new ResponseEntity<>(saved, HttpStatus.OK);
  }

}
