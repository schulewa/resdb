package com.apschulewitz.resdb.refdata.controller;

import com.apschulewitz.resdb.common.controller.AbstractController;
import com.apschulewitz.resdb.common.model.entity.VersionStatus;
import com.apschulewitz.resdb.config.RestUrlPaths;
import com.apschulewitz.resdb.refdata.model.dao.CountryDao;
import com.apschulewitz.resdb.refdata.model.entity.Country;
import lombok.extern.slf4j.Slf4j;
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
public class CountryController extends AbstractController<Country, Long> {

  private CountryDao countryDao;

  public CountryController(CountryDao countryDao) {
    this.countryDao = countryDao;
  }

  @RequestMapping(value = RestUrlPaths.COUNTRY_CONTROLLER_BASE_URL, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<List<Country>> findAll() {

    List<Country> countries = new ArrayList<>();
    Iterable<Country> iter = countryDao.findByStatusIn(VersionStatus.getLiveStatuses());
    StreamSupport.stream(iter.spliterator(), false)
      .forEach(countries::add);
    log.info("findAll: {} countries found", countries.size());
    return new ResponseEntity<>(countries, HttpStatus.OK);
  }

  @RequestMapping(value = RestUrlPaths.COUNTRY_CONTROLLER_BASE_URL, method = RequestMethod.POST)
  public ResponseEntity<Country> add(HttpServletRequest request, @RequestBody Country toBeSaved) {
    log.info("Save new country: {}", toBeSaved);
    Country saved;
    try {
        saved = countryDao.save(toBeSaved);
    } catch (Exception e) {
        log.error("Error saving country entity", e);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
    }
    return new ResponseEntity<>(saved, HttpStatus.CREATED);
  }

  @RequestMapping(value = RestUrlPaths.COUNTRY_CONTROLLER_BASE_URL + "/{id}", method = RequestMethod.DELETE)
  public ResponseEntity<Country> delete(@PathVariable long id) {
    log.info("Marking country [{}] for deletion", id);
    Optional<Country> existing = countryDao.findById(id);

    if (existing.isEmpty()) {
      log.error("No existing country found for id {} - unable to mark for deletion", id);
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    existing.get().setStatus(VersionStatus.Cancel);
    Country saved = countryDao.save(existing.get());
    return new ResponseEntity<>(saved, HttpStatus.OK);
  }

  @RequestMapping(value = RestUrlPaths.COUNTRY_CONTROLLER_BASE_URL, method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Country> update(@RequestBody Country toBeSaved) {
    log.info("Update existing country: {}", toBeSaved);
    Optional<Country> existing = countryDao.findById(toBeSaved.getId());

    if (existing.isEmpty()) {
      log.error("No existing country found for id {} - update aborted for: {}", toBeSaved.getId(), toBeSaved);
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    Country saved = countryDao.save(toBeSaved);
    return new ResponseEntity<>(saved, HttpStatus.OK);
  }

}
