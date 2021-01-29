package com.apschulewitz.resdb.refdata.controller;

import com.apschulewitz.resdb.common.controller.AbstractController;
import com.apschulewitz.resdb.common.model.EntityTypeEnum;
import com.apschulewitz.resdb.config.RestUrlPaths;
import com.apschulewitz.resdb.refdata.model.dto.RaceTypeDto;
import com.apschulewitz.resdb.refdata.service.RaceTypeService;
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
@Api(value = "Race type controller", tags = {"RaceType"})
public class RaceTypeController extends AbstractController<RaceTypeDto, Long> {

  private RaceTypeService raceTypeService;

  public RaceTypeController(RaceTypeService raceTypeService) {
    this.raceTypeService = raceTypeService;
  }

  @ApiOperation(
    value = "Find all race types",
    httpMethod = "GET",
    notes = "Finds all race types, active and inactive",
    response = List.class
  )
  @ApiResponses(value = {
    @ApiResponse(code = 200, message = "Request to find all race types completed successfully.")
  })
  @RequestMapping(value = RestUrlPaths.RACE_TYPE_CONTROLLER_BASE_URL, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<List<RaceTypeDto>> findAll(@ApiParam(name = "onlyActive",
                                                              allowableValues = "true, false",
                                                              required = true,
                                                              type = "Boolean",
                                                              value = "A boolean. True specifies to only include active race types, false to exclude inactive race types.")
                                                     @RequestParam Boolean onlyActive) {
    logStartOfFindAllRequest(EntityTypeEnum.RACE_TYPE);
    List<RaceTypeDto> raceTypes = raceTypeService.findAll(onlyActive);
    logEndOfFindAllRequest(EntityTypeEnum.RACE_TYPE);
    return new ResponseEntity<>(raceTypes, HttpStatus.OK);
  }

  @ApiOperation(
    value = "Add new race type",
    notes = "Add new race type. Returns the saved race type.",
    httpMethod = "POST",
    response = RaceTypeDto.class
  )
  @ApiResponses(value = {
    @ApiResponse(code = 200, message = "Request to add the specified race type completed successfully.")
  })
  @RequestMapping(value = RestUrlPaths.RACE_TYPE_CONTROLLER_BASE_URL, method = RequestMethod.POST)
  public ResponseEntity<RaceTypeDto> add(@ApiParam(name = "toBeSaved",
                                                    required = true,
                                                    type = "RaceTypeDto",
                                                    value = "A non-null instance of a RaceTypeDto")
                                           @RequestBody RaceTypeDto toBeSaved) {
    logStartOfAddRequest(EntityTypeEnum.RACE_TYPE, toBeSaved);
    RaceTypeDto saved = raceTypeService.add(toBeSaved);
    logEndOfAddRequest(EntityTypeEnum.RACE_TYPE, saved);
    return new ResponseEntity<>(saved, HttpStatus.CREATED);
  }

  @ApiOperation(
    value = "Delete a race type",
    notes = "Delete the race type matching the identifier by marking it as cancelled(inactive).",
    httpMethod = "DELETE",
    response = RaceTypeDto.class)
  @RequestMapping(value = RestUrlPaths.RACE_TYPE_CONTROLLER_BASE_URL + "/{id}", method = RequestMethod.DELETE)
  public ResponseEntity<RaceTypeDto> delete(@ApiParam(name = "id",
                                                      required = true,
                                                      type = "Long",
                                                      value = "A positive Long number identifying the race type to be deleted")
                                              @PathVariable Long id) {
    logStartOfDeleteRequest(EntityTypeEnum.RACE_TYPE, id);
    RaceTypeDto deleted = raceTypeService.deleteById(id);
    HttpStatus status;
    if (deleted == null) {
      status = HttpStatus.NOT_FOUND;
    } else {
      status = HttpStatus.OK;
    }
    logEndOfDeleteRequest(EntityTypeEnum.RACE_TYPE, deleted);
    return new ResponseEntity<>(deleted, status);
  }

  @ApiOperation(
    value = "Update a race type",
    notes = "Update the race type using the supplied data. The identifier must identify an active race type",
    httpMethod = "PUT",
    response = RaceTypeDto.class)
  @RequestMapping(value = RestUrlPaths.RACE_TYPE_CONTROLLER_BASE_URL, method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<RaceTypeDto> update(@ApiParam(name = "toBeSaved",
                                                      required = true,
                                                      type = "RaceTypeDto",
                                                      value = "A non-null RaceTypeDto")
                                              @RequestBody RaceTypeDto toBeSaved) {
    logStartOfUpdateRequest(EntityTypeEnum.RACE_TYPE, toBeSaved);
    RaceTypeDto updated = raceTypeService.update(toBeSaved);
    HttpStatus status;
    if (updated == null) {
      status = HttpStatus.NOT_FOUND;
    } else {
      status = HttpStatus.OK;
    }
    logEndOfUpdateRequest(EntityTypeEnum.RACE_TYPE, updated);
    return new ResponseEntity<>(updated, status);
  }

}
