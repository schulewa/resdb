package com.apschulewitz.resdb.refdata.controller;

import com.apschulewitz.resdb.common.controller.AbstractController;
import com.apschulewitz.resdb.common.model.EntityTypeEnum;
import com.apschulewitz.resdb.config.RestUrlPaths;
import com.apschulewitz.resdb.refdata.model.dto.LanguageGroupDto;
import com.apschulewitz.resdb.refdata.service.LanguageGroupService;
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
@Api(value = "Language group controller", tags = {"LanguageGroup"})
public class LanguageGroupController extends AbstractController<LanguageGroupDto, Long> {

  private LanguageGroupService languageGroupService;

  public LanguageGroupController(LanguageGroupService languageGroupService) {
    this.languageGroupService = languageGroupService;
  }

  @ApiOperation(
    value = "Find all language groups",
    httpMethod = "GET",
    notes = "Finds all language groups, active and inactive",
    response = List.class
  )
  @ApiResponses(value = {
    @ApiResponse(code = 200, message = "Request to find all language groups completed successfully.")
  })
  @RequestMapping(value = RestUrlPaths.LANGUAGE_GROUP_CONTROLLER_BASE_URL, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<List<LanguageGroupDto>> findAll(@ApiParam(name = "onlyActive",
                                                                  allowableValues = "true, false",
                                                                  required = true,
                                                                  type = "Boolean",
                                                                  value = "A boolean. True specifies to only include active language groups, false to exclude inactive language groups.")
                                                          @RequestParam Boolean onlyActive) {
    logStartOfFindAllRequest(EntityTypeEnum.LANGUAGE_GROUP);
    List<LanguageGroupDto> languageGroups = languageGroupService.findAll(onlyActive);
    logEndOfFindAllRequest(EntityTypeEnum.LANGUAGE_GROUP);
    return new ResponseEntity<>(languageGroups, HttpStatus.OK);
  }

  @ApiOperation(
    value = "Add new language group",
    notes = "Add new language group. Returns the saved language group.",
    httpMethod = "POST",
    response = LanguageGroupDto.class
  )
  @ApiResponses(value = {
    @ApiResponse(code = 200, message = "Request to add the specified language group completed successfully.")
  })
  @RequestMapping(value = RestUrlPaths.LANGUAGE_GROUP_CONTROLLER_BASE_URL, method = RequestMethod.POST)
  public ResponseEntity<LanguageGroupDto> add(@ApiParam(name = "toBeSaved",
                                                        required = true,
                                                        type = "LanguageGroupDto",
                                                        value = "A non-null instance of a LanguageGroupDto")
                                                @RequestBody LanguageGroupDto toBeSaved) {
    logStartOfAddRequest(EntityTypeEnum.LANGUAGE_GROUP, toBeSaved);
    LanguageGroupDto saved = languageGroupService.add(toBeSaved);
    logEndOfAddRequest(EntityTypeEnum.LANGUAGE_GROUP, saved);
    return new ResponseEntity<>(saved, HttpStatus.CREATED);
  }

  @ApiOperation(
    value = "Delete a language group",
    notes = "Delete the language group matching the identifier by marking it as cancelled(inactive).",
    httpMethod = "DELETE",
    response = LanguageGroupDto.class)
  @RequestMapping(value = RestUrlPaths.LANGUAGE_GROUP_CONTROLLER_BASE_URL + "/{id}", method = RequestMethod.DELETE)
  public ResponseEntity<LanguageGroupDto> delete(@ApiParam(name = "id",
                                                          required = true,
                                                          type = "Long",
                                                          value = "A positive Long number identifying the language group to be deleted")
                                                   @PathVariable Long id) {
    logStartOfDeleteRequest(EntityTypeEnum.LANGUAGE_GROUP, id);
    LanguageGroupDto deleted = languageGroupService.deleteById(id);
    HttpStatus status;
    if (deleted == null) {
      status = HttpStatus.NOT_FOUND;
    } else {
      status = HttpStatus.OK;
    }
    logEndOfDeleteRequest(EntityTypeEnum.LANGUAGE_GROUP, deleted);
    return new ResponseEntity<>(deleted, status);
  }

  @ApiOperation(
    value = "Update a language group",
    notes = "Update the language group using the supplied data. The identifier must identify an active language group",
    httpMethod = "PUT",
    response = LanguageGroupDto.class)
  @RequestMapping(value = RestUrlPaths.LANGUAGE_GROUP_CONTROLLER_BASE_URL, method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<LanguageGroupDto> update(@ApiParam(name = "toBeSaved",
                                                          required = true,
                                                          type = "LanguageGroupDto",
                                                          value = "A non-null LanguageGroupDto")
                                                   @RequestBody LanguageGroupDto toBeSaved) {
    logStartOfUpdateRequest(EntityTypeEnum.LANGUAGE_GROUP, toBeSaved);
    LanguageGroupDto updated = languageGroupService.update(toBeSaved);
    HttpStatus status;
    if (updated == null) {
      status = HttpStatus.NOT_FOUND;
    } else {
      status = HttpStatus.OK;
    }
    logEndOfUpdateRequest(EntityTypeEnum.LANGUAGE_GROUP, updated);
    return new ResponseEntity<>(updated, status);
  }

}
