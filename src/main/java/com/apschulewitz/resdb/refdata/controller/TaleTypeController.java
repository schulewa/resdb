package com.apschulewitz.resdb.refdata.controller;

import com.apschulewitz.resdb.common.controller.AbstractController;
import com.apschulewitz.resdb.common.model.entity.VersionStatus;
import com.apschulewitz.resdb.config.RestUrlPaths;
import com.apschulewitz.resdb.refdata.model.dao.TaleTypeDao;
import com.apschulewitz.resdb.refdata.model.entity.TaleType;
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
public class TaleTypeController extends AbstractController<TaleType, Long> {

  private TaleTypeDao taleTypeDao;

  public TaleTypeController(TaleTypeDao taleTypeDao) {
    this.taleTypeDao = taleTypeDao;
  }

  @RequestMapping(value = RestUrlPaths.TALE_TYPE_CONTROLLER_BASE_URL, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<List<TaleType>> findAll() {

    List<TaleType> taleTypes = new ArrayList<>();
    Iterable<TaleType> iter = taleTypeDao.findByStatusIn(VersionStatus.getLiveStatuses());
    StreamSupport.stream(iter.spliterator(), false)
      .forEach(taleTypes::add);

    return new ResponseEntity<>(taleTypes, HttpStatus.OK);
  }

  @RequestMapping(value = RestUrlPaths.TALE_TYPE_CONTROLLER_BASE_URL, method = RequestMethod.POST)
  public ResponseEntity<TaleType> add(HttpServletRequest request, @RequestBody TaleType toBeSaved) {
    log.info("Save new tale type: {}", toBeSaved);
    TaleType saved = taleTypeDao.save(toBeSaved);
    return new ResponseEntity<>(saved, HttpStatus.CREATED);
  }

  @RequestMapping(value = RestUrlPaths.TALE_TYPE_CONTROLLER_BASE_URL + "/{id}", method = RequestMethod.DELETE)
  public ResponseEntity<TaleType> delete(@PathVariable long id) {
    log.info("Marking tale type [{}] for deletion", id);
    Optional<TaleType> existing = taleTypeDao.findById(id);

    if (existing.isEmpty()) {
      log.error("No existing tale type found for id {} - unable to mark for deletion", id);
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    existing.get().setStatus(VersionStatus.Cancel);
    TaleType saved = taleTypeDao.save(existing.get());
    return new ResponseEntity<>(saved, HttpStatus.OK);
  }


  @RequestMapping(value = RestUrlPaths.TALE_TYPE_CONTROLLER_BASE_URL, method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<TaleType> update(@RequestBody TaleType toBeSaved) {
    log.info("Update existing tale type: {}", toBeSaved);
    Optional<TaleType> existing = taleTypeDao.findById(toBeSaved.getId());

    if (existing.isEmpty()) {
      log.error("No existing tale type found for id {} - update aborted for: {}", toBeSaved.getId(), toBeSaved);
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    TaleType saved = taleTypeDao.save(toBeSaved);
    return new ResponseEntity<>(saved, HttpStatus.OK);
  }

}
