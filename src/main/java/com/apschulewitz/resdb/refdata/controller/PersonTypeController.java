package com.apschulewitz.resdb.refdata.controller;

import com.apschulewitz.resdb.common.controller.AbstractController;
import com.apschulewitz.resdb.common.model.EntityTypeEnum;
import com.apschulewitz.resdb.config.RestUrlPaths;
import com.apschulewitz.resdb.refdata.model.dto.PersonTypeDto;
import com.apschulewitz.resdb.refdata.service.PersonTypeService;
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
public class PersonTypeController extends AbstractController<PersonTypeDto, Long> {

  private PersonTypeService personTypeService;

  public PersonTypeController(PersonTypeService personTypeService) {
    this.personTypeService = personTypeService;
  }

  @RequestMapping(value = RestUrlPaths.PERSON_TYPE_CONTROLLER_BASE_URL, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<List<PersonTypeDto>> findAll(@RequestBody Boolean onlyActive) {
    logStartOfFindAllRequest(EntityTypeEnum.PERSON_TYPE);
    List<PersonTypeDto> personTypes = personTypeService.findAll(onlyActive);
    logEndOfFindAllRequest(EntityTypeEnum.PERSON_TYPE);
    return new ResponseEntity<>(personTypes, HttpStatus.OK);
  }

//  @Override
//  public ResponseEntity<List<PersonType>> findAllActive() {
//    logStartOfFindAllActiveRequest(EntityTypeEnum.PERSON_TYPE);
//    List<PersonType> personTypes = new ArrayList<>();
//    Iterable<PersonType> iter = personTypeDao.findByStatusIn(VersionStatus.getLiveStatuses());
//    StreamSupport.stream(iter.spliterator(), false)
//      .forEach(personTypes::add);
//    logEndOfFindAllActiveRequest(EntityTypeEnum.PERSON_TYPE);
//    return new ResponseEntity<>(personTypes, HttpStatus.OK);
//  }

  @RequestMapping(value = RestUrlPaths.PERSON_TYPE_CONTROLLER_BASE_URL, method = RequestMethod.POST)
  public ResponseEntity<PersonTypeDto> add(@RequestBody PersonTypeDto toBeSaved) {
    logStartOfAddRequest(EntityTypeEnum.PERSON_TYPE, toBeSaved);
    PersonTypeDto saved = personTypeService.add(toBeSaved);
    logEndOfAddRequest(EntityTypeEnum.PERSON_TYPE, saved);
    return new ResponseEntity<>(saved, HttpStatus.CREATED);
  }

  @RequestMapping(value = RestUrlPaths.PERSON_TYPE_CONTROLLER_BASE_URL + "/{id}", method = RequestMethod.DELETE)
  public ResponseEntity<PersonTypeDto> delete(@PathVariable Long id) {
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

  @RequestMapping(value = RestUrlPaths.PERSON_TYPE_CONTROLLER_BASE_URL, method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<PersonTypeDto> update(@RequestBody PersonTypeDto toBeSaved) {
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
