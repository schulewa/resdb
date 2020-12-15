package com.apschulewitz.resdb.refdata.controller;

import com.apschulewitz.resdb.common.controller.AbstractController;
import com.apschulewitz.resdb.common.model.EntityTypeEnum;
import com.apschulewitz.resdb.config.RestUrlPaths;
import com.apschulewitz.resdb.refdata.model.dto.PublicationTypeDto;
import com.apschulewitz.resdb.refdata.service.PublicationTypeService;
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
public class PublicationTypeController extends AbstractController<PublicationTypeDto, Long> {

  private PublicationTypeService publicationTypeService;

  public PublicationTypeController(PublicationTypeService publicationTypeService) {
    this.publicationTypeService = publicationTypeService;
  }

  @RequestMapping(value = RestUrlPaths.PUBLICATION_TYPE_CONTROLLER_BASE_URL, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<List<PublicationTypeDto>> findAll(@RequestBody Boolean onlyActive) {
    logStartOfFindAllRequest(EntityTypeEnum.PUBLICATION_TYPE);
    List<PublicationTypeDto> publicationTypes = publicationTypeService.findAll(onlyActive);
    logEndOfFindAllRequest(EntityTypeEnum.PUBLICATION_TYPE);
    return new ResponseEntity<>(publicationTypes, HttpStatus.OK);
  }

//  @Override
//  public ResponseEntity<List<PublicationType>> findAllActive() {
//    logStartOfFindAllActiveRequest(EntityTypeEnum.PUBLICATION_TYPE);
//    List<PublicationType> publicationTypes = new ArrayList<>();
//    Iterable<PublicationType> iter = publicationTypeDao.findByStatusIn(VersionStatus.getLiveStatuses());
//    StreamSupport.stream(iter.spliterator(), false)
//      .forEach(publicationTypes::add);
//    logEndOfFindAllActiveRequest(EntityTypeEnum.PUBLICATION_TYPE);
//    return new ResponseEntity<>(publicationTypes, HttpStatus.OK);
//  }

  @RequestMapping(value = RestUrlPaths.PUBLICATION_TYPE_CONTROLLER_BASE_URL, method = RequestMethod.POST)
  public ResponseEntity<PublicationTypeDto> add(@RequestBody PublicationTypeDto toBeSaved) {
    logStartOfAddRequest(EntityTypeEnum.PUBLICATION_TYPE, toBeSaved);
    PublicationTypeDto saved = publicationTypeService.add(toBeSaved);
    logEndOfAddRequest(EntityTypeEnum.PUBLICATION_TYPE, saved);
    return new ResponseEntity<>(saved, HttpStatus.CREATED);
  }

  @RequestMapping(value = RestUrlPaths.PUBLICATION_TYPE_CONTROLLER_BASE_URL + "/{id}", method = RequestMethod.DELETE)
  public ResponseEntity<PublicationTypeDto> delete(@PathVariable Long id) {
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
