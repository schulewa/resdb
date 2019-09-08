package com.apschulewitz.resdb.refdata.controller;

import com.apschulewitz.resdb.common.controller.AbstractController;
import com.apschulewitz.resdb.common.model.entity.VersionStatus;
import com.apschulewitz.resdb.config.RestUrlPaths;
import com.apschulewitz.resdb.refdata.model.dao.PersonTypeDao;
import com.apschulewitz.resdb.refdata.model.entity.MeasureType;
import com.apschulewitz.resdb.refdata.model.entity.PersonType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

  private PersonTypeDao personTypeDao;

  public PersonTypeController(PersonTypeDao personTypeDao) {
    this.personTypeDao = personTypeDao;
  }

  @RequestMapping(value = RestUrlPaths.PERSON_TYPE_CONTROLLER_BASE_URL, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<List<PersonType>> findAll() {

    List<PersonType> personTypes = new ArrayList<>();
    Iterable<PersonType> iter = personTypeDao.findByStatusIn(VersionStatus.getLiveStatuses());
    StreamSupport.stream(iter.spliterator(), false)
      .forEach(personTypes::add);

    return new ResponseEntity<>(personTypes, HttpStatus.OK);
  }

  @RequestMapping(value = RestUrlPaths.PERSON_TYPE_CONTROLLER_BASE_URL, method = RequestMethod.POST)
  public ResponseEntity<PersonType> add(HttpServletRequest request, @RequestBody PersonType toBeSaved) {
    log.info("Save new person type: {}", toBeSaved);
    PersonType saved = personTypeDao.save(toBeSaved);
    return new ResponseEntity<>(saved, HttpStatus.CREATED);
  }

  @RequestMapping(value = RestUrlPaths.PERSON_TYPE_CONTROLLER_BASE_URL + "/{id}", method = RequestMethod.DELETE)
  public ResponseEntity<PersonType> delete(@PathVariable long id) {
    log.info("Marking person type [{}] for deletion", id);
    Optional<PersonType> existing = personTypeDao.findById(id);

    if (existing.isEmpty()) {
      log.error("No existing person type found for id {} - unable to mark for deletion", id);
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    existing.get().setStatus(VersionStatus.Cancel);
    PersonType saved = personTypeDao.save(existing.get());
    return new ResponseEntity<>(saved, HttpStatus.OK);
  }

  @RequestMapping(value = RestUrlPaths.PERSON_TYPE_CONTROLLER_BASE_URL, method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<PersonType> update(@RequestBody PersonType toBeSaved) {
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
