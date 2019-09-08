package com.apschulewitz.resdb.refdata.controller;

import com.apschulewitz.resdb.common.controller.AbstractController;
import com.apschulewitz.resdb.common.model.entity.VersionStatus;
import com.apschulewitz.resdb.config.RestUrlPaths;
import com.apschulewitz.resdb.refdata.model.dao.RegionDao;
import com.apschulewitz.resdb.refdata.model.entity.ReferenceType;
import com.apschulewitz.resdb.refdata.model.entity.Region;
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
public class RegionController extends AbstractController<Region, Long> {

  private RegionDao regionDao;

  public RegionController(RegionDao regionDao) {
    this.regionDao = regionDao;
  }

  @RequestMapping(value = RestUrlPaths.REGION_CONTROLLER_BASE_URL, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<List<Region>> findAll() {

    List<Region> regions = new ArrayList<>();
    Iterable<Region> iter = regionDao.findByStatusIn(VersionStatus.getLiveStatuses());
    StreamSupport.stream(iter.spliterator(), false)
      .forEach(regions::add);

    return new ResponseEntity<>(regions, HttpStatus.OK);
  }

  @RequestMapping(value = RestUrlPaths.REGION_CONTROLLER_BASE_URL, method = RequestMethod.POST)
  public ResponseEntity<Region> add(HttpServletRequest request, @RequestBody Region toBeSaved) {
    log.info("Save new region: {}", toBeSaved);
    Region saved = regionDao.save(toBeSaved);
    return new ResponseEntity<>(saved, HttpStatus.CREATED);
  }

  @RequestMapping(value = RestUrlPaths.RACE_TYPE_CONTROLLER_BASE_URL + "/{id}", method = RequestMethod.DELETE)
  public ResponseEntity<Region> delete(@PathVariable long id) {
    log.info("Marking region [{}] for deletion", id);
    Optional<Region> existing = regionDao.findById(id);

    if (existing.isEmpty()) {
      log.error("No existing reference type found for id {} - unable to mark for deletion", id);
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    existing.get().setStatus(VersionStatus.Cancel);
    Region saved = regionDao.save(existing.get());
    return new ResponseEntity<>(saved, HttpStatus.OK);
  }

  @RequestMapping(value = RestUrlPaths.REGION_CONTROLLER_BASE_URL, method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Region> update(Region toBeSaved) {
    log.info("Update existing region: {}", toBeSaved);
    Optional<Region> existing = regionDao.findById(toBeSaved.getId());

    if (existing.isEmpty()) {
      log.error("No existing region found for id {} - update aborted for: {}", toBeSaved.getId(), toBeSaved);
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    Region saved = regionDao.save(toBeSaved);
    return new ResponseEntity<>(saved, HttpStatus.OK);
  }

}
