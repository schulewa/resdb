package com.apschulewitz.resdb.refdata.controller;

import com.apschulewitz.resdb.common.controller.AbstractController;
import com.apschulewitz.resdb.config.RestUrlPaths;
import com.apschulewitz.resdb.refdata.model.dao.RaceTypeDao;
import com.apschulewitz.resdb.refdata.model.entity.RaceType;
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
public class RaceTypeController extends AbstractController<RaceType, Long> {

  @Autowired
  private RaceTypeDao raceTypeDao;

  @RequestMapping(value = RestUrlPaths.RACE_TYPE_CONTROLLER_BASE_URL, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<List<RaceType>> findAll() {

    List<RaceType> raceTypes = new ArrayList<>();
    Iterable<RaceType> iter = raceTypeDao.findAll();
    StreamSupport.stream(iter.spliterator(), false)
      .forEach(at -> raceTypes.add(at));

    return new ResponseEntity<>(raceTypes, HttpStatus.OK);
  }

  @RequestMapping(value = RestUrlPaths.RACE_TYPE_CONTROLLER_BASE_URL, method = RequestMethod.POST)
  public ResponseEntity<RaceType> add(HttpServletRequest request, @RequestBody RaceType toBeSaved) {
    log.info("Save new race type: {}", toBeSaved);
    RaceType saved = raceTypeDao.save(toBeSaved);
    return new ResponseEntity<>(saved, HttpStatus.CREATED);
  }

  @RequestMapping(value = RestUrlPaths.RACE_TYPE_CONTROLLER_BASE_URL, method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<RaceType> update(RaceType toBeSaved) {
    log.info("Update existing race type: {}", toBeSaved);
    Optional<RaceType> existing = raceTypeDao.findById(toBeSaved.getId());

    if (existing.isEmpty()) {
      log.error("No existing race type found for id {} - update aborted for: {}", toBeSaved.getId(), toBeSaved);
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    RaceType saved = raceTypeDao.save(toBeSaved);
    return new ResponseEntity<>(saved, HttpStatus.OK);
  }

}
