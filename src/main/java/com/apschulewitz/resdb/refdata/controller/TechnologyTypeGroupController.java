package com.apschulewitz.resdb.refdata.controller;

import com.apschulewitz.resdb.common.controller.AbstractController;
import com.apschulewitz.resdb.common.model.EntityTypeEnum;
import com.apschulewitz.resdb.config.RestUrlPaths;
import com.apschulewitz.resdb.refdata.model.dto.TechnologyTypeGroupDto;
import com.apschulewitz.resdb.refdata.service.TechnologyTypeGroupService;
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
@Api(value = "Technology type group controller", tags = {"TechnologyTypeGroup"})
public class TechnologyTypeGroupController extends AbstractController<TechnologyTypeGroupDto, Long> {

  private TechnologyTypeGroupService technologyTypeGroupService;

  public TechnologyTypeGroupController(TechnologyTypeGroupService technologyTypeGroupService) {
    this.technologyTypeGroupService = technologyTypeGroupService;
  }

  @ApiOperation(
    value = "Find all technology type groups",
    httpMethod = "GET",
    notes = "Finds all technology type groups, active and inactive",
    response = List.class
  )
  @ApiResponses(value = {
    @ApiResponse(code = 200, message = "Request to find all technology type groups completed successfully.")
  })
  @RequestMapping(value = RestUrlPaths.TECHNOLOGY_TYPE_GROUP_CONTROLLER_BASE_URL, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<List<TechnologyTypeGroupDto>> findAll(@ApiParam(name = "onlyActive",
                                                                        allowableValues = "true, false",
                                                                        required = true,
                                                                        type = "Boolean",
                                                                        value = "A boolean. True specifies to only include active technology type groups, false to exclude inactive technology type groups.")
                                                                @RequestParam Boolean onlyActive) {
    logStartOfFindAllRequest(EntityTypeEnum.TECHNOLOGY_TYPE_GROUP);
    List<TechnologyTypeGroupDto> technologyTypeGroups = technologyTypeGroupService.findAll(onlyActive);
    logEndOfFindAllRequest(EntityTypeEnum.TECHNOLOGY_TYPE_GROUP);
    return new ResponseEntity<>(technologyTypeGroups, HttpStatus.OK);
  }

  @ApiOperation(
    value = "Add new technology type group",
    notes = "Add new technology type group. Returns the saved technology type group.",
    httpMethod = "POST",
    response = TechnologyTypeGroupDto.class
  )
  @ApiResponses(value = {
    @ApiResponse(code = 200, message = "Request to add the specified technology type group completed successfully.")
  })
  @RequestMapping(value = RestUrlPaths.TECHNOLOGY_TYPE_GROUP_CONTROLLER_BASE_URL, method = RequestMethod.POST)
  public ResponseEntity<TechnologyTypeGroupDto> add(@ApiParam(name = "toBeSaved",
                                                              required = true,
                                                              type = "TechnologyTypeGroupDto",
                                                              value = "A non-null instance of a TechnologyTypeGroupDto")
                                                      @RequestBody TechnologyTypeGroupDto toBeSaved) {
    logStartOfAddRequest(EntityTypeEnum.TECHNOLOGY_TYPE_GROUP, toBeSaved);
    TechnologyTypeGroupDto saved = technologyTypeGroupService.add(toBeSaved);
    logEndOfAddRequest(EntityTypeEnum.TECHNOLOGY_TYPE_GROUP, saved);
    return new ResponseEntity<>(saved, HttpStatus.CREATED);
  }

  @ApiOperation(
    value = "Delete a technology type group",
    notes = "Delete the technology type group matching the identifier by marking it as cancelled(inactive).",
    httpMethod = "DELETE",
    response = TechnologyTypeGroupDto.class)
  @RequestMapping(value = RestUrlPaths.TECHNOLOGY_TYPE_GROUP_CONTROLLER_BASE_URL + "/{id}", method = RequestMethod.DELETE)
  public ResponseEntity<TechnologyTypeGroupDto> delete(@ApiParam(name = "id",
                                                                required = true,
                                                                type = "Long",
                                                                value = "A positive Long number identifying the technology type group to be deleted")
                                                         @PathVariable Long id) {
    logStartOfDeleteRequest(EntityTypeEnum.TECHNOLOGY_TYPE_GROUP, id);
    TechnologyTypeGroupDto deleted = technologyTypeGroupService.deleteById(id);
    HttpStatus result;
    if (deleted == null)
      result = HttpStatus.NOT_FOUND;
    else
      result = HttpStatus.OK;
    logEndOfDeleteRequest(EntityTypeEnum.TECHNOLOGY_TYPE_GROUP, deleted);
    return new ResponseEntity<>(deleted, result);
  }

  @ApiOperation(
    value = "Update a technology type group",
    notes = "Update the technology type group using the supplied data. The identifier must identify an active technology type group",
    httpMethod = "PUT",
    response = TechnologyTypeGroupDto.class)
  @RequestMapping(value = RestUrlPaths.TECHNOLOGY_TYPE_GROUP_CONTROLLER_BASE_URL, method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<TechnologyTypeGroupDto> update(@ApiParam(name = "toBeSaved",
                                                                  required = true,
                                                                  type = "TechnologyTypeGroupDto",
                                                                  value = "A non-null TechnologyTypeGroupDto")
                                                         @RequestBody TechnologyTypeGroupDto toBeSaved) {
    logStartOfUpdateRequest(EntityTypeEnum.TECHNOLOGY_TYPE_GROUP, toBeSaved);
    TechnologyTypeGroupDto saved = technologyTypeGroupService.update(toBeSaved);
    logEndOfUpdateRequest(EntityTypeEnum.TECHNOLOGY_TYPE_GROUP, saved);
    return new ResponseEntity<>(saved, HttpStatus.OK);
  }

}
