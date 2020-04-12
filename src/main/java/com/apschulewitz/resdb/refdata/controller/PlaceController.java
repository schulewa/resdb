package com.apschulewitz.resdb.refdata.controller;

import com.apschulewitz.resdb.common.controller.AbstractController;
import com.apschulewitz.resdb.common.model.entity.VersionStatus;
import com.apschulewitz.resdb.config.RestUrlPaths;
import com.apschulewitz.resdb.refdata.model.dao.PlaceDao;
import com.apschulewitz.resdb.refdata.model.entity.Place;
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
public class PlaceController extends AbstractController<Place, Long> {

  private PlaceDao placeDao;

  public PlaceController(PlaceDao placeDao) {
    this.placeDao = placeDao;
  }

  @RequestMapping(value = RestUrlPaths.PLACE_CONTROLLER_BASE_URL, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<List<Place>> findAll() {

    List<Place> places = new ArrayList<>();
    Iterable<Place> iter = placeDao.findByStatusIn(VersionStatus.getLiveStatuses());
    StreamSupport.stream(iter.spliterator(), false)
      .forEach(places::add);
    log.info("findAll: {} places found", places.size());
    return new ResponseEntity<>(places, HttpStatus.OK);
  }

  @RequestMapping(value = RestUrlPaths.PLACE_CONTROLLER_BASE_URL, method = RequestMethod.POST)
  public ResponseEntity<Place> add(HttpServletRequest request, @RequestBody Place toBeSaved) {
    log.info("Save new place: {}", toBeSaved);
    Place saved;
    try {
        saved = placeDao.save(toBeSaved);
    } catch (Exception e) {
        log.error("Error saving place entity", e);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
    }
    return new ResponseEntity<>(saved, HttpStatus.CREATED);
  }

  @RequestMapping(value = RestUrlPaths.PLACE_CONTROLLER_BASE_URL + "/{id}", method = RequestMethod.DELETE)
  public ResponseEntity<Place> delete(@PathVariable long id) {
    log.info("Marking place [{}] for deletion", id);
    Optional<Place> existing = placeDao.findById(id);

    if (existing.isEmpty()) {
      log.error("No existing place found for id {} - unable to mark for deletion", id);
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    existing.get().setStatus(VersionStatus.Cancel);
    Place saved = placeDao.save(existing.get());
    return new ResponseEntity<>(saved, HttpStatus.OK);
  }

  @RequestMapping(value = RestUrlPaths.PLACE_CONTROLLER_BASE_URL, method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Place> update(@RequestBody Place toBeSaved) {
    log.info("Update existing place: {}", toBeSaved);
    Optional<Place> existing = placeDao.findById(toBeSaved.getId());

    if (existing.isEmpty()) {
      log.error("No existing place found for id {} - update aborted for: {}", toBeSaved.getId(), toBeSaved);
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    Place saved = placeDao.save(toBeSaved);
    return new ResponseEntity<>(saved, HttpStatus.OK);
  }

}
