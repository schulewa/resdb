package com.apschulewitz.resdb.refdata.controller;

import com.apschulewitz.resdb.common.controller.AbstractController;
import com.apschulewitz.resdb.common.model.EntityTypeEnum;
import com.apschulewitz.resdb.config.RestUrlPaths;
import com.apschulewitz.resdb.refdata.model.dto.HierarchyTypeDto;
import com.apschulewitz.resdb.refdata.model.dto.ImageTypeDto;
import com.apschulewitz.resdb.refdata.service.ImageTypeService;
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
@Api(value = "Image type controller", tags = "ImageType")
public class ImageTypeController extends AbstractController<ImageTypeDto, Long> {

  private ImageTypeService imageTypeService;

  public ImageTypeController(ImageTypeService imageTypeService) {
    this.imageTypeService = imageTypeService;
  }

  @ApiOperation(
    httpMethod = "GET",
    response = List.class,
    tags = "ImageType",
    value = "Find all image types by active or inactive status")
  @ApiResponses(value = {
    @ApiResponse(code = 200, message = "Successfully executed request to find all image types")
  })
  @RequestMapping(value = RestUrlPaths.IMAGE_TYPE_CONTROLLER_BASE_URL, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<List<ImageTypeDto>> findAll(@ApiParam(value = "A boolean specifying whether to only image hierarchy types with an active status",
                                                              name = "onlyActive",
                                                              allowableValues = "true, false")
                                                      @RequestParam Boolean onlyActive) {
    logStartOfFindAllRequest(EntityTypeEnum.IMAGE_TYPE);
    List<ImageTypeDto> imageTypes = imageTypeService.findAll(onlyActive);
    logEndOfFindAllRequest(EntityTypeEnum.IMAGE_TYPE);
    return new ResponseEntity<>(imageTypes, HttpStatus.OK);
  }

  @ApiOperation(
    httpMethod = "POST",
    notes = "Add supplied image type. Returns added image type",
    response = List.class,
    tags = "ImageType",
    value = "Add image type")
  @ApiResponses(value = {
    @ApiResponse(code = 201, message = "Image type successfully added"),
    @ApiResponse(code = 500, message = "Failure adding image type")
  })
  @RequestMapping(value = RestUrlPaths.IMAGE_TYPE_CONTROLLER_BASE_URL, method = RequestMethod.POST)
  public ResponseEntity<ImageTypeDto> add(@ApiParam(value = "A JSON value representing a image type",
                                                    name = "toBeSaved",
                                                    example = "{\"name\":\"Image type \"}")
                                            @RequestBody ImageTypeDto toBeSaved) {
    logStartOfAddRequest(EntityTypeEnum.IMAGE_TYPE, toBeSaved);
    ImageTypeDto saved = imageTypeService.add(toBeSaved);
    logEndOfAddRequest(EntityTypeEnum.IMAGE_TYPE, saved);
    return new ResponseEntity<>(saved, HttpStatus.CREATED);
  }

  @ApiOperation(
    httpMethod = "DELETE",
    notes = "Marks image type as deleted. Returns deleted image type",
    response = ImageTypeDto.class,
    tags = "ImageType",
    value = "Delete image type")
  @ApiResponses(value = {
    @ApiResponse(code = 200, message = "Image type marked as deleted"),
    @ApiResponse(code = 404, message = "Image type not found or does not have active status")
  })
  @RequestMapping(value = RestUrlPaths.IMAGE_TYPE_CONTROLLER_BASE_URL + "/{id}", method = RequestMethod.DELETE)
  public ResponseEntity<ImageTypeDto> delete(@ApiParam(value = "Numeric identifier for image type to be marked as deleted",
                                                        name = "id",
                                                        example = "{id: 123}")
                                               @PathVariable Long id) {
    logStartOfDeleteRequest(EntityTypeEnum.IMAGE_TYPE, id);
    ImageTypeDto deleted = imageTypeService.deleteById(id);
    HttpStatus status;
    if (deleted == null) {
      status = HttpStatus.NOT_FOUND;
    } else {
      status = HttpStatus.OK;
    }
    logEndOfDeleteRequest(EntityTypeEnum.IMAGE_TYPE, deleted);
    return new ResponseEntity<>(deleted, status);
  }

  @ApiOperation(
    httpMethod = "PUT",
    notes = "Updates supplied image type. Returns updated image type",
    response = ImageTypeDto.class,
    tags = "ImageType",
    value = "Update image type")
  @ApiResponses(value = {
    @ApiResponse(code = 200, message = "Image type successfully updated"),
    @ApiResponse(code = 404, message = "Image type not found or does not have active status")
  })
  @RequestMapping(value = RestUrlPaths.IMAGE_TYPE_CONTROLLER_BASE_URL, method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<ImageTypeDto> update(@ApiParam(value = "A JSON value representing an updated image type",
                                                        example = "{\"id\":123,\"name\":\"updated Image type\",\"numberOfDimenions\":2,\"defaultWidth\":15,\"defaultHeight\":90,\"status\":\"Amend\",\"createdBy\":\"user1\",\"updatedBy\":\"user2\",\"lastUpdated\":{\"dateTime\":{\"date\":{\"year\":2021,\"month\":1,\"day\":10},\"time\":{\"hour\":13,\"minute\":13,\"second\":23,\"nano\":715117000}},\"offset\":{\"totalSeconds\":0},\"zone\":{\"totalSeconds\":0}},\"versionNumber\":2}")
                                               @RequestBody ImageTypeDto toBeSaved) {
    logStartOfUpdateRequest(EntityTypeEnum.IMAGE_TYPE, toBeSaved);
    ImageTypeDto updated = imageTypeService.update(toBeSaved);
    HttpStatus status;
    if (updated == null) {
      status = HttpStatus.NOT_FOUND;
    } else {
      status = HttpStatus.OK;
    }
    logEndOfUpdateRequest(EntityTypeEnum.IMAGE_TYPE, updated);
    return new ResponseEntity<>(updated, status);
  }

}
