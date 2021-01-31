package com.apschulewitz.resdb.refdata.controller;

import com.apschulewitz.resdb.common.controller.AbstractController;
import com.apschulewitz.resdb.common.model.EntityTypeEnum;
import com.apschulewitz.resdb.config.RestUrlPaths;
import com.apschulewitz.resdb.research.model.dto.ReferenceTypeDto;
import com.apschulewitz.resdb.refdata.service.ReferenceTypeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by adrianschulewitz on 22/04/2017.
 */
@RestController()
@Slf4j
@Api(value = "Reference type controller", tags = {"ReferenceType"})
public class ReferenceTypeController extends AbstractController<ReferenceTypeDto, Long> {

  private ReferenceTypeService referenceTypeService;

  public ReferenceTypeController(ReferenceTypeService referenceTypeService) {
    this.referenceTypeService = referenceTypeService;
  }

  @ApiOperation(
    value = "Find all reference types",
    httpMethod = "GET",
    notes = "Finds all reference types, active and inactive",
    response = List.class
  )
  @ApiResponses(value = {
    @ApiResponse(code = 200, message = "Request to find all reference types completed successfully.")
  })
  @RequestMapping(value = RestUrlPaths.REFERENCE_TYPE_CONTROLLER_BASE_URL, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<List<ReferenceTypeDto>> findAll(@ApiParam(name = "onlyActive",
                                                                  allowableValues = "true, false",
                                                                  required = true,
                                                                  type = "Boolean",
                                                                  value = "A boolean. True specifies to only include active reference types, false to exclude inactive reference types.")
                                                          @RequestParam Boolean onlyActive) {
    logStartOfFindAllRequest(EntityTypeEnum.REFERENCE_TYPE);
    List<ReferenceTypeDto> referenceTypes = referenceTypeService.findAll(onlyActive);
    logEndOfFindAllRequest(EntityTypeEnum.REFERENCE_TYPE);
    return new ResponseEntity<>(referenceTypes, HttpStatus.OK);
  }

  @ApiOperation(
    value = "Add new reference type",
    notes = "Add new reference type. Returns the saved reference type.",
    httpMethod = "POST",
    response = ReferenceTypeDto.class
  )
  @ApiResponses(value = {
    @ApiResponse(code = 200, message = "Request to add the specified reference type completed successfully.")
  })
  @RequestMapping(value = RestUrlPaths.REFERENCE_TYPE_CONTROLLER_BASE_URL, method = RequestMethod.POST)
  public ResponseEntity<ReferenceTypeDto> add(@ApiParam(name = "toBeSaved",
                                                        required = true,
                                                        type = "ReferenceTypeDto",
                                                        value = "A non-null instance of a ReferenceTypeDto")
                                                @RequestBody ReferenceTypeDto toBeSaved) {
    logStartOfAddRequest(EntityTypeEnum.REFERENCE_TYPE, toBeSaved);
    ReferenceTypeDto saved = referenceTypeService.add(toBeSaved);
    logEndOfAddRequest(EntityTypeEnum.REFERENCE_TYPE, saved);
    return new ResponseEntity<>(saved, HttpStatus.CREATED);
  }

  @ApiOperation(
    value = "Delete a reference type",
    notes = "Delete the reference type matching the identifier by marking it as cancelled(inactive).",
    httpMethod = "DELETE",
    response = ReferenceTypeDto.class)
  @RequestMapping(value = RestUrlPaths.REFERENCE_TYPE_CONTROLLER_BASE_URL + "/{id}", method = RequestMethod.DELETE)
  public ResponseEntity<ReferenceTypeDto> delete(@ApiParam(name = "id",
                                                            required = true,
                                                            type = "Long",
                                                            value = "A positive Long number identifying the reference type to be deleted")
                                                   @PathVariable Long id) {
    logStartOfDeleteRequest(EntityTypeEnum.REFERENCE_TYPE, id);
    ReferenceTypeDto deleted = referenceTypeService.deleteById(id);
    HttpStatus result;
    if (deleted == null)
      result = HttpStatus.NOT_FOUND;
    else
      result = HttpStatus.OK;

    logEndOfDeleteRequest(EntityTypeEnum.REFERENCE_TYPE, deleted);
    return new ResponseEntity<>(deleted, result);
  }

  @ApiOperation(
    value = "Update a reference type",
    notes = "Update the reference type using the supplied data. The identifier must identify an active reference type",
    httpMethod = "PUT",
    response = ReferenceTypeDto.class)
  @RequestMapping(value = RestUrlPaths.REFERENCE_TYPE_CONTROLLER_BASE_URL, method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<ReferenceTypeDto> update(@ApiParam(name = "toBeSaved",
                                                          required = true,
                                                          type = "ReferenceTypeDto",
                                                          value = "A non-null ReferenceTypeDto")
                                                   @RequestBody ReferenceTypeDto toBeSaved) {
    logStartOfUpdateRequest(EntityTypeEnum.REFERENCE_TYPE, toBeSaved);
    ReferenceTypeDto saved = referenceTypeService.update(toBeSaved);
    logEndOfUpdateRequest(EntityTypeEnum.REFERENCE_TYPE, saved);
    return new ResponseEntity<>(saved, HttpStatus.OK);
  }

}
