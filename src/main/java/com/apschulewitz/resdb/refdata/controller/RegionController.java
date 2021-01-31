package com.apschulewitz.resdb.refdata.controller;

import com.apschulewitz.resdb.common.controller.AbstractController;
import com.apschulewitz.resdb.common.model.EntityTypeEnum;
import com.apschulewitz.resdb.config.RestUrlPaths;
import com.apschulewitz.resdb.refdata.model.dto.RegionDto;
import com.apschulewitz.resdb.refdata.service.RegionService;
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

import java.util.ArrayList;
import java.util.List;

/**
 * Created by adrianschulewitz on 22/04/2017.
 */
@RestController()
@Slf4j
@Api(value = "Region controller", tags = {"Region"})
public class RegionController extends AbstractController<RegionDto, Long> {

  private RegionService regionService;

  public RegionController(RegionService regionService) {
    this.regionService = regionService;
  }

  @ApiOperation(
    value = "Find all regions",
    httpMethod = "GET",
    notes = "Finds all regions, active and inactive",
    response = List.class
  )
  @ApiResponses(value = {
    @ApiResponse(code = 200, message = "Request to find all regions completed successfully.")
  })
  @RequestMapping(value = RestUrlPaths.REGION_CONTROLLER_BASE_URL, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<List<RegionDto>> findAll(@ApiParam(name = "onlyActive",
                                                            allowableValues = "true, false",
                                                            required = true,
                                                            type = "Boolean",
                                                            value = "A boolean. True specifies to only include active regions, false to exclude inactive regions.")
                                                   @RequestParam Boolean onlyActive) {
    logStartOfFindAllRequest(EntityTypeEnum.REGION);
    List<RegionDto> regions = new ArrayList<>();
    if (onlyActive)
      regions.addAll(regionService.findAll(true));
    else
      regions.addAll(regionService.findAll(false));

    logEndOfFindAllRequest(EntityTypeEnum.REGION);
    return new ResponseEntity<>(regions, HttpStatus.OK);
  }

  @ApiOperation(
    value = "Add new region",
    notes = "Add new region. Returns the saved region.",
    httpMethod = "POST",
    response = RegionDto.class
  )
  @ApiResponses(value = {
    @ApiResponse(code = 200, message = "Request to add the specified region completed successfully.")
  })
  @RequestMapping(value = RestUrlPaths.REGION_CONTROLLER_BASE_URL, method = RequestMethod.POST)
  public ResponseEntity<RegionDto> add(@ApiParam(name = "toBeSaved",
                                                  required = true,
                                                  type = "RegionDto",
                                                  value = "A non-null instance of a RegionDto")
                                         @RequestBody RegionDto toBeSaved) {
    logStartOfAddRequest(EntityTypeEnum.REGION, toBeSaved);
    RegionDto saved = regionService.add(toBeSaved);
    logEndOfAddRequest(EntityTypeEnum.REGION, saved);
    return new ResponseEntity<>(saved, HttpStatus.CREATED);
  }

  @ApiOperation(
    value = "Delete a region",
    notes = "Delete the region matching the identifier by marking it as cancelled(inactive).",
    httpMethod = "DELETE",
    response = RegionDto.class)
  @RequestMapping(value = RestUrlPaths.REGION_CONTROLLER_BASE_URL + "/{id}", method = RequestMethod.DELETE)
  public ResponseEntity<RegionDto> delete(@ApiParam(name = "id",
                                                    required = true,
                                                    type = "Long",
                                                    value = "A positive Long number identifying the region to be deleted")
                                            @PathVariable Long id) {
    logStartOfDeleteRequest(EntityTypeEnum.REGION, id);
    RegionDto deleted = regionService.deleteById(id);
    logEndOfDeleteRequest(EntityTypeEnum.REGION, deleted);
    return new ResponseEntity<>(deleted, HttpStatus.OK);
  }

  @ApiOperation(
    value = "Update a region",
    notes = "Update the region using the supplied data. The identifier must identify an active region",
    httpMethod = "PUT",
    response = RegionDto.class)
  @RequestMapping(value = RestUrlPaths.REGION_CONTROLLER_BASE_URL, method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<RegionDto> update(@ApiParam(name = "toBeSaved",
                                                    required = true,
                                                    type = "RegionDto",
                                                    value = "A non-null RegionDto")
                                            @RequestBody RegionDto toBeSaved) {
    logStartOfUpdateRequest(EntityTypeEnum.REGION, toBeSaved);
    RegionDto updated = regionService.update(toBeSaved);
    logEndOfUpdateRequest(EntityTypeEnum.REGION, updated);
    return new ResponseEntity<>(updated, HttpStatus.OK);
  }

}
