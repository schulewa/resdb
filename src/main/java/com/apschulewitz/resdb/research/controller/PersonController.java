package com.apschulewitz.resdb.research.controller;

import com.apschulewitz.resdb.common.controller.AbstractController;
import com.apschulewitz.resdb.common.model.EntityTypeEnum;
import com.apschulewitz.resdb.config.RestUrlPaths;
import com.apschulewitz.resdb.refdata.service.PersonService;
import com.apschulewitz.resdb.refdata.model.dto.PersonDto;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by adrianschulewitz on 22/04/2017.
 */
@RestController()
@Slf4j
public class PersonController extends AbstractController<PersonDto, Long> {

  private PersonService personService;

  private final Gson gson = new Gson();

  public PersonController(PersonService personService) {
    this.personService = personService;
  }

  @GetMapping(value = RestUrlPaths.PERSON_CONTROLLER_BASE_URL, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  public ResponseEntity<List<PersonDto>> findAll(Boolean onlyActive) {
    logStartOfFindAllRequest(EntityTypeEnum.PERSON);
    List<PersonDto> persons = personService.findAll(onlyActive);
    logEndOfFindAllRequest(EntityTypeEnum.PERSON);
    return new ResponseEntity<>(persons, HttpStatus.OK);
  }

//  @GetMapping(value = RestUrlPaths.PERSON_CONTROLLER_BASE_URL, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
//  public ResponseEntity<List<PersonDto>> findAllActive() {
//    logStartOfFindAllActiveRequest(EntityTypeEnum.PERSON);
//    List<PersonDto> persons = personService.findByStatusIn(VersionStatus.getLiveStatuses());
//    logEndOfFindAllActiveRequest(EntityTypeEnum.PERSON);
//    return new ResponseEntity<>(persons, HttpStatus.OK);
//  }

  // TODO fix PersonController 'add' method with PersonDto causing unreadable message exception
  @PostMapping(value = RestUrlPaths.PERSON_CONTROLLER_BASE_URL)
  public ResponseEntity<PersonDto> add(@RequestBody PersonDto toBeSaved) {
    logStartOfAddRequest(EntityTypeEnum.PERSON, toBeSaved);
    PersonDto savedDto = personService.add(toBeSaved);
    logEndOfAddRequest(EntityTypeEnum.PERSON, savedDto);
    return new ResponseEntity<>(savedDto, HttpStatus.CREATED);
  }

//  @PostMapping(value = RestUrlPaths.PERSON_CONTROLLER_BASE_URL)
//    public ResponseEntity<PersonDto> addUsingStringData(@RequestBody String json) {
//    logStartOfAddRequest(EntityTypeEnum.PERSON, json);
//    PersonDto toBeSaved;
//    try {
//      toBeSaved = gson.fromJson(json, PersonDto.class);
//    } catch (JsonSyntaxException e) {
//      log.error("Error parsing json version of PerdsonDto", e);
//      return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
//    }
//    return this.add(toBeSaved);
//  }

  @DeleteMapping(value = RestUrlPaths.PERSON_CONTROLLER_BASE_URL + "/{id}")
  public ResponseEntity<PersonDto> delete(@PathVariable Long id) {
    logStartOfDeleteRequest(EntityTypeEnum.PERSON, id);
    PersonDto saved = personService.deleteById(id);
    logEndOfDeleteRequest(EntityTypeEnum.PERSON, saved);
    return new ResponseEntity<>(saved, HttpStatus.OK);
  }

  @PutMapping(value = RestUrlPaths.PERSON_CONTROLLER_BASE_URL, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<PersonDto> update(@RequestBody PersonDto toBeSaved) {
    logStartOfUpdateRequest(EntityTypeEnum.PERSON, toBeSaved);
    PersonDto saved = personService.update(toBeSaved);
    logEndOfUpdateRequest(EntityTypeEnum.PERSON, saved);
    return new ResponseEntity<>(saved, HttpStatus.OK);
  }

}
