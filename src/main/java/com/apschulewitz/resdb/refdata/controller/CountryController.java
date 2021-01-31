package com.apschulewitz.resdb.refdata.controller;

import com.apschulewitz.resdb.common.controller.AbstractController;
import com.apschulewitz.resdb.common.model.EntityTypeEnum;
import com.apschulewitz.resdb.common.utils.LoggingUtils;
import com.apschulewitz.resdb.config.RestUrlPaths;
import com.apschulewitz.resdb.refdata.model.dto.CalendarTypeDto;
import com.apschulewitz.resdb.refdata.model.dto.CountryDto;
import com.apschulewitz.resdb.refdata.service.CountryService;
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
@Api(value = "Country controller", tags = "Country")
public class CountryController extends AbstractController<CountryDto, Long> {

  private CountryService countryService;

  public CountryController(CountryService countryService) {
    this.countryService = countryService;
  }

  @ApiOperation(
    httpMethod = "GET",
    response = List.class,
    tags = "Country",
    value = "Find all countries by active or inactive status")
  @ApiResponses(value = {
    @ApiResponse(code = 200, message = "Successfully executed request to find all countries")
  })
  @RequestMapping(value = RestUrlPaths.COUNTRY_CONTROLLER_BASE_URL, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<List<CountryDto>> findAll(@ApiParam(
                                                      value = "A boolean specifying whether to only include countries with an active status",
                                                      name = "onlyActive",
                                                      allowableValues = "true, false"
                                                  )
                                                  @RequestParam Boolean onlyActive) {
    LoggingUtils.logStartOfFindAllRequest(EntityTypeEnum.COUNTRY);
    List<CountryDto> countries = countryService.findAll(onlyActive);
    LoggingUtils.logEndOfFindAllRequest(EntityTypeEnum.COUNTRY);
    return new ResponseEntity<>(countries, HttpStatus.OK);
  }

  @ApiOperation(
    httpMethod = "POST",
    notes = "Add supplied country. Returns added country",
    response = List.class,
    tags = "Country",
    value = "Add country")
  @ApiResponses(value = {
    @ApiResponse(code = 201, message = "Country successfully added"),
    @ApiResponse(code = 500, message = "Failure adding country")
  })
  @RequestMapping(value = RestUrlPaths.COUNTRY_CONTROLLER_BASE_URL, method = RequestMethod.POST)
  public ResponseEntity<CountryDto> add(@ApiParam(value = "A JSON value representing an country",
                                                  name = "toBeSaved",
                                                  example = "{\"name\":\"Country\"}")
                                          @RequestBody CountryDto toBeSaved) {
    LoggingUtils.logStartOfAddRequest(EntityTypeEnum.COUNTRY, toBeSaved);
    CountryDto saved = countryService.add(toBeSaved);
    HttpStatus status;
    if (saved == null) {
      status = HttpStatus.INTERNAL_SERVER_ERROR;
    } else {
      status = HttpStatus.CREATED;
    }
    return new ResponseEntity<>(saved, status);
  }

  @ApiOperation(
    httpMethod = "DELETE",
    notes = "Marks country as deleted. Returns deleted country",
    response = CountryDto.class,
    tags = "Country",
    value = "Delete country")
  @ApiResponses(value = {
    @ApiResponse(code = 200, message = "Country marked as deleted"),
    @ApiResponse(code = 404, message = "Country not found or does not have active status")
  })
  @RequestMapping(value = RestUrlPaths.COUNTRY_CONTROLLER_BASE_URL + "/{id}", method = RequestMethod.DELETE)
  public ResponseEntity<CountryDto> delete(@ApiParam(value = "Numeric identifier for country to be marked as deleted",
                                                      name = "id",
                                                      example = "{id: 123}")
                                             @PathVariable Long id) {
    LoggingUtils.logStartOfDeleteRequest(EntityTypeEnum.COUNTRY, id);
    CountryDto deleted = countryService.deleteById(id);
    HttpStatus status;
    if (deleted == null) {
      status = HttpStatus.BAD_REQUEST;
    } else {
      status = HttpStatus.OK;
    }
    LoggingUtils.logEndOfDeleteRequest(EntityTypeEnum.COUNTRY, id);
    return new ResponseEntity<>(deleted, status);
  }

  @ApiOperation(
    httpMethod = "PUT",
    notes = "Updates supplied country. Returns updated country",
    response = CountryDto.class,
    tags = "Country",
    value = "Update country")
  @ApiResponses(value = {
    @ApiResponse(code = 200, message = "Country successfully updated"),
    @ApiResponse(code = 404, message = "Country not found or does not have active status")
  })
  @RequestMapping(value = RestUrlPaths.COUNTRY_CONTROLLER_BASE_URL, method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<CountryDto> update(@ApiParam(
                                              value = "A JSON value representing an updated country",
                                              example = "{\"id\":123,\"name\":\"Country\",\"status\":\"Amend\",\"createdBy\":\"user1\",\"updatedBy\":\"user2\",\"lastUpdated\":{\"dateTime\":{\"date\":{\"year\":2021,\"month\":1,\"day\":9},\"time\":{\"hour\":16,\"minute\":51,\"second\":25,\"nano\":796636000}},\"offset\":{\"totalSeconds\":0},\"zone\":{\"totalSeconds\":0}},\"versionNumber\":2}"
                                            )
                                             @RequestBody CountryDto toBeSaved) {
    LoggingUtils.logStartOfUpdateRequest(EntityTypeEnum.COUNTRY, toBeSaved);
    CountryDto updated = countryService.update(toBeSaved);
    return new ResponseEntity<>(updated, HttpStatus.OK);
  }

}
