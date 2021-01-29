package com.apschulewitz.resdb.refdata.controller;

import com.apschulewitz.resdb.common.controller.AbstractController;
import com.apschulewitz.resdb.common.model.EntityTypeEnum;
import com.apschulewitz.resdb.config.RestUrlPaths;
import com.apschulewitz.resdb.refdata.model.dto.PlaceDto;
import com.apschulewitz.resdb.refdata.model.dto.PublicationTypeDto;
import com.apschulewitz.resdb.refdata.service.PublicationTypeService;
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
@Api(value = "Publication type controller", tags = {"PublicationType"})
public class PublicationTypeController extends AbstractController<PublicationTypeDto, Long> {

  private PublicationTypeService publicationTypeService;

  public PublicationTypeController(PublicationTypeService publicationTypeService) {
    this.publicationTypeService = publicationTypeService;
  }

  @ApiOperation(
    value = "Find all publication types",
    httpMethod = "GET",
    notes = "Finds all publication types, active and inactive",
    response = List.class
  )
  @ApiResponses(value = {
    @ApiResponse(code = 200, message = "Request to find all publication types completed successfully.")
  })
  @RequestMapping(value = RestUrlPaths.PUBLICATION_TYPE_CONTROLLER_BASE_URL, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<List<PublicationTypeDto>> findAll(@ApiParam(name = "onlyActive",
                                                                    allowableValues = "true, false",
                                                                    required = true,
                                                                    type = "Boolean",
                                                                    value = "A boolean. True specifies to only include active publication types, false to exclude inactive publication types.")
                                                            @RequestParam Boolean onlyActive) {
    logStartOfFindAllRequest(EntityTypeEnum.PUBLICATION_TYPE);
    List<PublicationTypeDto> publicationTypes = publicationTypeService.findAll(onlyActive);
    logEndOfFindAllRequest(EntityTypeEnum.PUBLICATION_TYPE);
    return new ResponseEntity<>(publicationTypes, HttpStatus.OK);
  }

  @ApiOperation(
    value = "Add new publication type",
    notes = "Add new publication type. Returns the saved publication type.",
    httpMethod = "POST",
    response = PlaceDto.class
  )
  @ApiResponses(value = {
    @ApiResponse(code = 200, message = "Request to add the specified publication type completed successfully.")
  })
  @RequestMapping(value = RestUrlPaths.PUBLICATION_TYPE_CONTROLLER_BASE_URL, method = RequestMethod.POST)
  public ResponseEntity<PublicationTypeDto> add(@ApiParam(name = "toBeSaved",
                                                          required = true,
                                                          type = "PlaceDto",
                                                          value = "A non-null instance of a PublicationTypeDto")
                                                  @RequestBody PublicationTypeDto toBeSaved) {
    logStartOfAddRequest(EntityTypeEnum.PUBLICATION_TYPE, toBeSaved);
    PublicationTypeDto saved = publicationTypeService.add(toBeSaved);
    logEndOfAddRequest(EntityTypeEnum.PUBLICATION_TYPE, saved);
    return new ResponseEntity<>(saved, HttpStatus.CREATED);
  }

  @ApiOperation(
    value = "Delete a publication type",
    notes = "Delete the publication type matching the identifier by marking it as cancelled(inactive).",
    httpMethod = "DELETE",
    response = PlaceDto.class)
  @RequestMapping(value = RestUrlPaths.PUBLICATION_TYPE_CONTROLLER_BASE_URL + "/{id}", method = RequestMethod.DELETE)
  public ResponseEntity<PublicationTypeDto> delete(@ApiParam(name = "id",
                                                              required = true,
                                                              type = "Long",
                                                              value = "A positive Long number identifying the publication type to be deleted")
                                                     @PathVariable Long id) {
    logStartOfDeleteRequest(EntityTypeEnum.PUBLICATION_TYPE, id);
    PublicationTypeDto deleted = publicationTypeService.deleteById(id);
    HttpStatus status;
    if (deleted == null) {
      status = HttpStatus.NOT_FOUND;
    } else {
      status = HttpStatus.OK;
    }
    logEndOfDeleteRequest(EntityTypeEnum.PUBLICATION_TYPE, deleted);
    return new ResponseEntity<>(deleted, status);
  }

  @ApiOperation(
    value = "Update a publication type",
    notes = "Update the publication type using the supplied data. The identifier must identify an active publication type",
    httpMethod = "PUT",
    response = PublicationTypeDto.class)
  @RequestMapping(value = RestUrlPaths.PUBLICATION_TYPE_CONTROLLER_BASE_URL, method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<PublicationTypeDto> update(@RequestBody PublicationTypeDto toBeSaved) {
    logStartOfUpdateRequest(EntityTypeEnum.PUBLICATION_TYPE, toBeSaved);
    PublicationTypeDto updated = publicationTypeService.update(toBeSaved);
    HttpStatus status;
    if (updated == null) {
      status = HttpStatus.NOT_FOUND;
    } else {
      status = HttpStatus.OK;
    }
    logEndOfUpdateRequest(EntityTypeEnum.PUBLICATION_TYPE, updated);
    return new ResponseEntity<>(updated, status);
  }

}
