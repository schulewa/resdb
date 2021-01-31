package com.apschulewitz.resdb.refdata.controller;

import com.apschulewitz.resdb.common.controller.AbstractController;
import com.apschulewitz.resdb.common.model.EntityTypeEnum;
import com.apschulewitz.resdb.config.RestUrlPaths;
import com.apschulewitz.resdb.refdata.model.dto.TaleTypeDto;
import com.apschulewitz.resdb.refdata.service.TaleTypeService;
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
@Api(value = "Tale type controller", tags = {"TaleType"})
public class TaleTypeController extends AbstractController<TaleTypeDto, Long> {

  private TaleTypeService taleTypeService;

  public TaleTypeController(TaleTypeService taleTypeService) {
    this.taleTypeService = taleTypeService;
  }

  @ApiOperation(
    value = "Find all tale types",
    httpMethod = "GET",
    notes = "Finds all tale types, active and inactive",
    response = List.class
  )
  @ApiResponses(value = {
    @ApiResponse(code = 200, message = "Request to find all tale types completed successfully.")
  })
  @RequestMapping(value = RestUrlPaths.TALE_TYPE_CONTROLLER_BASE_URL, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<List<TaleTypeDto>> findAll(@ApiParam(name = "onlyActive",
                                                              allowableValues = "true, false",
                                                              required = true,
                                                              type = "Boolean",
                                                              value = "A boolean. True specifies to only include active tale types, false to exclude inactive tale types.")
                                                     @RequestParam Boolean onlyActive) {
    logStartOfFindAllRequest(EntityTypeEnum.TALE_TYPE);
    List<TaleTypeDto> taleTypes = taleTypeService.findAll(onlyActive);
    logEndOfFindAllRequest(EntityTypeEnum.TALE_TYPE);
    return new ResponseEntity<>(taleTypes, HttpStatus.OK);
  }

  @ApiOperation(
    value = "Add new tale type",
    notes = "Add new tale type. Returns the saved tale type.",
    httpMethod = "POST",
    response = TaleTypeDto.class
  )
  @ApiResponses(value = {
    @ApiResponse(code = 200, message = "Request to add the specified tale type completed successfully.")
  })
  @RequestMapping(value = RestUrlPaths.TALE_TYPE_CONTROLLER_BASE_URL, method = RequestMethod.POST)
  public ResponseEntity<TaleTypeDto> add(@ApiParam(name = "toBeSaved",
                                                    required = true,
                                                    type = "TaleTypeDto",
                                                    value = "A non-null instance of a TaleTypeDto")
                                           @RequestBody TaleTypeDto toBeSaved) {
    logStartOfAddRequest(EntityTypeEnum.TALE_TYPE, toBeSaved);
    TaleTypeDto saved = taleTypeService.add(toBeSaved);
    logEndOfAddRequest(EntityTypeEnum.TALE_TYPE, saved);
    return new ResponseEntity<>(saved, HttpStatus.CREATED);
  }

  @ApiOperation(
    value = "Delete a tale type",
    notes = "Delete the tale type matching the identifier by marking it as cancelled(inactive).",
    httpMethod = "DELETE",
    response = TaleTypeDto.class)
  @RequestMapping(value = RestUrlPaths.TALE_TYPE_CONTROLLER_BASE_URL + "/{id}", method = RequestMethod.DELETE)
  public ResponseEntity<TaleTypeDto> delete(@ApiParam(name = "id",
                                                      required = true,
                                                      type = "Long",
                                                      value = "A positive Long number identifying the tale type to be deleted")
                                              @PathVariable Long id) {
    logStartOfDeleteRequest(EntityTypeEnum.TALE_TYPE, id);
    TaleTypeDto deleted = taleTypeService.deleteById(id);
    HttpStatus status;
    if (deleted == null) {
      status = HttpStatus.NOT_FOUND;
    } else {
      status = HttpStatus.OK;
    }
    logEndOfDeleteRequest(EntityTypeEnum.TALE_TYPE, deleted);
    return new ResponseEntity<>(deleted, status);
  }

  @ApiOperation(
    value = "Update a tale type",
    notes = "Update the tale type using the supplied data. The identifier must identify an active tale type",
    httpMethod = "PUT",
    response = TaleTypeDto.class)
  @RequestMapping(value = RestUrlPaths.TALE_TYPE_CONTROLLER_BASE_URL, method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<TaleTypeDto> update(@ApiParam(name = "toBeSaved",
                                                      required = true,
                                                      type = "TaleTypeDto",
                                                      value = "A non-null TaleTypeDto")
                                              @RequestBody TaleTypeDto toBeSaved) {
    logStartOfUpdateRequest(EntityTypeEnum.TALE_TYPE, toBeSaved);
    TaleTypeDto updated = taleTypeService.update(toBeSaved);
    HttpStatus status;
    if (updated == null) {
      status = HttpStatus.NOT_FOUND;
    } else {
      status = HttpStatus.OK;
    }
    logEndOfUpdateRequest(EntityTypeEnum.TALE_TYPE, updated);
    return new ResponseEntity<>(updated, status);
  }

}
