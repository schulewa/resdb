package com.apschulewitz.resdb.refdata.controller;

import com.apschulewitz.resdb.common.controller.AbstractController;
import com.apschulewitz.resdb.common.model.EntityTypeEnum;
import com.apschulewitz.resdb.config.RestUrlPaths;
import com.apschulewitz.resdb.refdata.model.dto.ImageTypeDto;
import com.apschulewitz.resdb.refdata.service.ImageTypeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by adrianschulewitz on 22/04/2017.
 */
@RestController()
@Slf4j
public class ImageTypeController extends AbstractController<ImageTypeDto, Long> {

  private ImageTypeService imageTypeService;

  public ImageTypeController(ImageTypeService imageTypeService) {
    this.imageTypeService = imageTypeService;
  }

  @RequestMapping(value = RestUrlPaths.IMAGE_TYPE_CONTROLLER_BASE_URL, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<List<ImageTypeDto>> findAll(@RequestBody Boolean onlyActive) {
    logStartOfFindAllRequest(EntityTypeEnum.IMAGE_TYPE);
    List<ImageTypeDto> imageTypes = imageTypeService.findAll(onlyActive);
    logEndOfFindAllRequest(EntityTypeEnum.IMAGE_TYPE);
    return new ResponseEntity<>(imageTypes, HttpStatus.OK);
  }

//  @Override
//  public ResponseEntity<List<ImageType>> findAllActive() {
//    logStartOfFindAllActiveRequest(EntityTypeEnum.IMAGE_TYPE);
//    List<ImageType> imageTypes = new ArrayList<>();
//    Iterable<ImageType> iter = imageTypeDao.findByStatusIn(VersionStatus.getLiveStatuses());
//    StreamSupport.stream(iter.spliterator(), false)
//      .forEach(imageTypes::add);
//    logEndOfFindAllActiveRequest(EntityTypeEnum.IMAGE_TYPE);
//    return new ResponseEntity<>(imageTypes, HttpStatus.OK);
//  }

  @RequestMapping(value = RestUrlPaths.IMAGE_TYPE_CONTROLLER_BASE_URL, method = RequestMethod.POST)
  public ResponseEntity<ImageTypeDto> add(@RequestBody ImageTypeDto toBeSaved) {
    logStartOfAddRequest(EntityTypeEnum.IMAGE_TYPE, toBeSaved);
    ImageTypeDto saved = imageTypeService.add(toBeSaved);
    logEndOfAddRequest(EntityTypeEnum.IMAGE_TYPE, saved);
    return new ResponseEntity<>(saved, HttpStatus.CREATED);
  }

  @RequestMapping(value = RestUrlPaths.IMAGE_TYPE_CONTROLLER_BASE_URL + "/{id}", method = RequestMethod.DELETE)
  public ResponseEntity<ImageTypeDto> delete(@PathVariable Long id) {
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

  @RequestMapping(value = RestUrlPaths.IMAGE_TYPE_CONTROLLER_BASE_URL, method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<ImageTypeDto> update(@RequestBody ImageTypeDto toBeSaved) {
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
