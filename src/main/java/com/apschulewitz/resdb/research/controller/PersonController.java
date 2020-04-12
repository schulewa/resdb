package com.apschulewitz.resdb.research.controller;

import com.apschulewitz.resdb.common.controller.AbstractController;
import com.apschulewitz.resdb.common.model.entity.VersionStatus;
import com.apschulewitz.resdb.config.RestUrlPaths;
import com.apschulewitz.resdb.refdata.model.dao.PersonDao;
import com.apschulewitz.resdb.refdata.model.entity.AddressType;
import com.apschulewitz.resdb.refdata.model.entity.Person;
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
public class PersonController extends AbstractController<Person, Long> {

  @Autowired
  private PersonDao personDao;

  @RequestMapping(value = RestUrlPaths.PERSON_CONTROLLER_BASE_URL, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<List<Person>> findAll() {

    List<Person> persons = new ArrayList<>();
    Iterable<Person> iter = personDao.findByStatusIn(VersionStatus.getLiveStatuses());
    StreamSupport.stream(iter.spliterator(), false)
      .forEach(at -> persons.add(at));

    return new ResponseEntity<>(persons, HttpStatus.OK);
  }

  @RequestMapping(value = RestUrlPaths.PERSON_CONTROLLER_BASE_URL, method = RequestMethod.POST)
  public ResponseEntity<Person> add(HttpServletRequest request, @RequestBody Person toBeSaved) {
    log.info("Save new person: {}", toBeSaved);
    Person saved = personDao.save(toBeSaved);
    return new ResponseEntity<>(saved, HttpStatus.CREATED);
  }

  @RequestMapping(value = RestUrlPaths.PERSON_CONTROLLER_BASE_URL + "/{id}", method = RequestMethod.DELETE)
  public ResponseEntity<Person> delete(@PathVariable long id) {
    log.info("Marking person [{}] for deletion", id);
    Optional<Person> existing = personDao.findById(id);

    if (existing.isEmpty()) {
      log.error("No existing person found for id {} - unable to mark for deletion", id);
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    existing.get().setStatus(VersionStatus.Cancel);
    Person saved = personDao.save(existing.get());
    return new ResponseEntity<>(saved, HttpStatus.OK);
  }

  @RequestMapping(value = RestUrlPaths.PERSON_CONTROLLER_BASE_URL, method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Person> update(@RequestBody Person toBeSaved) {
    log.info("Update existing person: {}", toBeSaved);
    Optional<Person> existing = personDao.findById(toBeSaved.getId());

    if (existing.isEmpty()) {
      log.error("No existing person found for id {} - update aborted for: {}", toBeSaved.getId(), toBeSaved);
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    Person saved = personDao.save(toBeSaved);
    return new ResponseEntity<>(saved, HttpStatus.OK);
  }

}
