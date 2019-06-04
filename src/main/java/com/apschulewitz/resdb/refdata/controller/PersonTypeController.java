package com.apschulewitz.resdb.refdata.controller;

import com.apschulewitz.resdb.common.controller.AbstractController;
import com.apschulewitz.resdb.config.RestUrlPaths;
import com.apschulewitz.resdb.refdata.model.dao.PersonTypeDao;
import com.apschulewitz.resdb.refdata.model.entity.PersonType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

/**
 * Created by adrianschulewitz on 22/04/2017.
 */
@RestController()
@Slf4j
public class PersonTypeController extends AbstractController<PersonType, Long> {

  @Autowired
  private PersonTypeDao personTypeDao;

  @RequestMapping(value = RestUrlPaths.PERSON_TYPE_CONTROLLER_BASE_URL, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<List<PersonType>> findAll() {

    List<PersonType> personTypes = new ArrayList<>();
    Iterable<PersonType> iter = personTypeDao.findAll();
    StreamSupport.stream(iter.spliterator(), false)
      .forEach(at -> personTypes.add(at));

    return new ResponseEntity<>(personTypes, HttpStatus.OK);
  }

  @RequestMapping(value = RestUrlPaths.PERSON_TYPE_CONTROLLER_BASE_URL, method = RequestMethod.POST)
  public ResponseEntity<PersonType> add(HttpServletRequest request, @RequestBody PersonType toBeSaved) {
    log.info("Save new person type: {}", toBeSaved);
    PersonType saved = personTypeDao.save(toBeSaved);
    return new ResponseEntity<>(saved, HttpStatus.CREATED);
  }

  @RequestMapping(value = RestUrlPaths.PERSON_TYPE_CONTROLLER_BASE_URL, method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<PersonType> update(PersonType toBeSaved) {
    log.info("Update existing person type: {}", toBeSaved);
    Optional<PersonType> existing = personTypeDao.findById(toBeSaved.getId());

    if (existing.isEmpty()) {
      log.error("No existing person type found for id {} - update aborted for: {}", toBeSaved.getId(), toBeSaved);
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    PersonType saved = personTypeDao.save(toBeSaved);
    return new ResponseEntity<>(saved, HttpStatus.OK);
  }

}
