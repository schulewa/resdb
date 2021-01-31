package com.apschulewitz.resdb.refdata.controller;

import com.apschulewitz.resdb.common.controller.AbstractController;
import com.apschulewitz.resdb.common.model.EntityTypeEnum;
import com.apschulewitz.resdb.common.utils.LoggingUtils;
import com.apschulewitz.resdb.config.RestUrlPaths;
import com.apschulewitz.resdb.refdata.model.dto.AddressTypeDto;
import com.apschulewitz.resdb.refdata.service.AddressTypeService;
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
@Api(value = "Address type controller", tags = "AddressType")
public class AddressTypeController extends AbstractController<AddressTypeDto, Long> {

  private AddressTypeService addressTypeService;

  public AddressTypeController(AddressTypeService addressTypeService) {
    this.addressTypeService = addressTypeService;
  }

  @ApiOperation(
    httpMethod = "GET",
    response = List.class,
    tags = "AddressType",
  value = "Find all address types by active or inactive status")
  @ApiResponses(value = {
    @ApiResponse(code = 200, message = "Successfully executed request to find all address types")
  })
  @RequestMapping(value = RestUrlPaths.ADDRESS_TYPE_CONTROLLER_BASE_URL, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<List<AddressTypeDto>> findAll(@ApiParam(value = "A boolean specifying whether to only include address types with an active status",
                                                                name = "onlyActive",
                                                                allowableValues = "true, false")
                                                        @RequestParam(name = "onlyActive") Boolean onlyActive) {
    LoggingUtils.logStartOfFindAllRequest(EntityTypeEnum.ADDRESS_TYPE);
    List<AddressTypeDto> addressTypes = addressTypeService.findAll(onlyActive);
    LoggingUtils.logEndOfFindAllRequest(EntityTypeEnum.ADDRESS_TYPE);
    return new ResponseEntity<>(addressTypes, HttpStatus.OK);
  }

  @ApiOperation(
    httpMethod = "POST",
    notes = "Add the supplied address type. Returns the added address type",
    response = List.class,
    tags = "AddressType",
    value = "Add address type")
  @ApiResponses(value = {
    @ApiResponse(code = 201, message = "Address type successfully added")
  })
  @RequestMapping(value = RestUrlPaths.ADDRESS_TYPE_CONTROLLER_BASE_URL, method = RequestMethod.POST)
  public ResponseEntity<AddressTypeDto> add(@ApiParam(value = "A JSON value representing an address type",
                                                      name = "toBeSaved",
                                                      example = "{\"name\":\"Home address\"}")
                                            @RequestBody AddressTypeDto toBeSaved) {
    LoggingUtils.logStartOfAddRequest(EntityTypeEnum.ADDRESS_TYPE, toBeSaved);
    AddressTypeDto saved = addressTypeService.add(toBeSaved);
    LoggingUtils.logEndOfAddRequest(EntityTypeEnum.ADDRESS_TYPE, saved);
    return new ResponseEntity<>(saved, HttpStatus.CREATED);
  }

  @ApiOperation(
    httpMethod = "DELETE",
    notes = "Marks the supplied address type as deleted. Returns the deleted address type",
    response = AddressTypeDto.class,
    tags = "AddressType",
    value = "Delete address type")
  @ApiResponses(value = {
    @ApiResponse(code = 200, message = "Address type marked as deleted"),
    @ApiResponse(code = 404, message = "Address type not found or does not have active status")
  })
  @RequestMapping(value = RestUrlPaths.ADDRESS_TYPE_CONTROLLER_BASE_URL + "/{id}", method = RequestMethod.DELETE)
  public ResponseEntity<AddressTypeDto> delete(@ApiParam(value = "Numeric identifier for address type to be marked as deleted",
                                                          name = "id",
                                                          example = "{id: 123}")
                                                 @PathVariable Long id) {
    LoggingUtils.logStartOfDeleteRequest(EntityTypeEnum.ADDRESS_TYPE, id);
    AddressTypeDto deleted = addressTypeService.deleteById(id);

    HttpStatus result;
    if (deleted == null)
      result = HttpStatus.NOT_FOUND;
    else
      result = HttpStatus.OK;

    LoggingUtils.logEndOfDeleteRequest(EntityTypeEnum.ADDRESS_TYPE, deleted);
    return new ResponseEntity<>(deleted, result);
  }

  @ApiOperation(
    httpMethod = "PUT",
    notes = "Updates the supplied address type. Returns the updated address type",
    response = AddressTypeDto.class,
    tags = "AddressType",
    value = "Update address type")
  @ApiResponses(value = {
    @ApiResponse(code = 200, message = "Address type successfully updated"),
    @ApiResponse(code = 404, message = "Address type not found or does not have active status")
  })
  @RequestMapping(value = RestUrlPaths.ADDRESS_TYPE_CONTROLLER_BASE_URL, method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<AddressTypeDto> update(@ApiParam(value = "A JSON value representing an updated address type",
                                                        example = "{\"id\":123,\"name\":\"Home address\",\"status\":\"Amend\",\"createdBy\":\"user1\",\"updatedBy\":\"user2\",\"lastUpdated\":{\"dateTime\":{\"date\":{\"year\":2021,\"month\":1,\"day\":9},\"time\":{\"hour\":16,\"minute\":51,\"second\":25,\"nano\":796636000}},\"offset\":{\"totalSeconds\":0},\"zone\":{\"totalSeconds\":0}},\"versionNumber\":2}")
                                                 @RequestBody AddressTypeDto toBeSaved) {
    LoggingUtils.logStartOfUpdateRequest(EntityTypeEnum.ADDRESS_TYPE, toBeSaved);
    HttpStatus result;
    AddressTypeDto updated = addressTypeService.update(toBeSaved);
    if (updated == null) {
      result = HttpStatus.NOT_FOUND;
    } else {
      result= HttpStatus.OK;
    }
    LoggingUtils.logEndOfUpdateRequest(EntityTypeEnum.ADDRESS_TYPE, updated);
    return new ResponseEntity<>(updated, result);
  }

}
