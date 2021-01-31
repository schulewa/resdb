package com.apschulewitz.resdb.refdata.controller;

import com.apschulewitz.resdb.common.controller.AbstractController;
import com.apschulewitz.resdb.common.model.EntityTypeEnum;
import com.apschulewitz.resdb.config.RestUrlPaths;
import com.apschulewitz.resdb.refdata.model.dto.MeasureTypeDto;
import com.apschulewitz.resdb.refdata.model.dto.PersonTypeDto;
import com.apschulewitz.resdb.refdata.service.PersonTypeService;
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
@Api(value = "Person type controller", tags = {"PersonType"})
public class PersonTypeController extends AbstractController<PersonTypeDto, Long> {

  private PersonTypeService personTypeService;

  public PersonTypeController(PersonTypeService personTypeService) {
    this.personTypeService = personTypeService;
  }

  @ApiOperation(
    value = "Find all Person types",
    httpMethod = "GET",
    notes = "Finds all person types, active and inactive",
    response = List.class
  )
  @ApiResponses(value = {
    @ApiResponse(code = 200, message = "Request to find all person types completed successfully.")
  })
  @RequestMapping(value = RestUrlPaths.PERSON_TYPE_CONTROLLER_BASE_URL, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<List<PersonTypeDto>> findAll(@ApiParam(name = "onlyActive",
                                                                allowableValues = "true, false",
                                                                required = true,
                                                                type = "Boolean",
                                                                value = "A boolean. True specifies to only include active person types, false to exclude inactive person types.")
                                                       @RequestParam Boolean onlyActive) {
    logStartOfFindAllRequest(EntityTypeEnum.PERSON_TYPE);
    List<PersonTypeDto> personTypes = personTypeService.findAll(onlyActive);
    logEndOfFindAllRequest(EntityTypeEnum.PERSON_TYPE);
    return new ResponseEntity<>(personTypes, HttpStatus.OK);
  }

  @ApiOperation(
    value = "Add new person type",
    notes = "Add new person type. Returns the saved person type.",
    httpMethod = "POST",
    response = MeasureTypeDto.class
  )
  @ApiResponses(value = {
    @ApiResponse(code = 200, message = "Request to add the specified person type completed successfully.")
  })
  @RequestMapping(value = RestUrlPaths.PERSON_TYPE_CONTROLLER_BASE_URL, method = RequestMethod.POST)
  public ResponseEntity<PersonTypeDto> add(@ApiParam(name = "toBeSaved",
                                                      required = true,
                                                      type = "PersonTypeDto",
                                                      value = "A non-null instance of a PersonTypeDto")
                                             @RequestBody PersonTypeDto toBeSaved) {
    logStartOfAddRequest(EntityTypeEnum.PERSON_TYPE, toBeSaved);
    PersonTypeDto saved = personTypeService.add(toBeSaved);
    logEndOfAddRequest(EntityTypeEnum.PERSON_TYPE, saved);
    return new ResponseEntity<>(saved, HttpStatus.CREATED);
  }

  @ApiOperation(
    value = "Delete a person type",
    notes = "Delete the person type matching the identifier by marking it as cancelled(inactive).",
    httpMethod = "DELETE",
    response = PersonTypeDto.class)
  @RequestMapping(value = RestUrlPaths.PERSON_TYPE_CONTROLLER_BASE_URL + "/{id}", method = RequestMethod.DELETE)
  public ResponseEntity<PersonTypeDto> delete(@ApiParam(name = "id",
                                                        required = true,
                                                        type = "Long",
                                                        value = "A positive Long number identifying the person type to be deleted")
                                                @PathVariable Long id) {
    logStartOfDeleteRequest(EntityTypeEnum.PERSON_TYPE, id);
    PersonTypeDto deleted = personTypeService.deleteById(id);
    HttpStatus status;
    if (deleted == null) {
      status = HttpStatus.NOT_FOUND;
    } else {
      status = HttpStatus.OK;
    }
    logEndOfDeleteRequest(EntityTypeEnum.PERSON_TYPE, deleted);
    return new ResponseEntity<>(deleted, status);
  }

  @ApiOperation(
    value = "Update a person type",
    notes = "Update the person type using the supplied data. The identifier must identify an active person type",
    httpMethod = "PUT",
    response = PersonTypeDto.class)
  @RequestMapping(value = RestUrlPaths.PERSON_TYPE_CONTROLLER_BASE_URL, method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<PersonTypeDto> update(@ApiParam(name = "toBeSaved",
                                                        required = true,
                                                        type = "PersonTypeDto",
                                                        value = "A non-null PersonTypeDto")
                                                @RequestBody PersonTypeDto toBeSaved) {
    logStartOfUpdateRequest(EntityTypeEnum.PERSON_TYPE, toBeSaved);
    PersonTypeDto updated = personTypeService.update(toBeSaved);
    HttpStatus status;
    if (updated == null) {
      status = HttpStatus.NOT_FOUND;
    } else {
      status = HttpStatus.OK;
    }
    logEndOfUpdateRequest(EntityTypeEnum.PERSON_TYPE, updated);
    return new ResponseEntity<>(updated, status);
  }

}
