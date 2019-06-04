package com.apschulewitz.resdb.refdata.controller;

import com.apschulewitz.resdb.common.controller.AbstractController;
import com.apschulewitz.resdb.config.RestUrlPaths;
import com.apschulewitz.resdb.refdata.model.dao.TaleTypeDao;
import com.apschulewitz.resdb.refdata.model.entity.TaleType;
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
public class TaleTypeController extends AbstractController<TaleType, Long> {

  @Autowired
  private TaleTypeDao taleTypeDao;

  @RequestMapping(value = RestUrlPaths.TALE_TYPE_CONTROLLER_BASE_URL, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<List<TaleType>> findAll() {

    List<TaleType> taleTypes = new ArrayList<>();
    Iterable<TaleType> iter = taleTypeDao.findAll();
    StreamSupport.stream(iter.spliterator(), false)
      .forEach(at -> taleTypes.add(at));

    return new ResponseEntity<>(taleTypes, HttpStatus.OK);
  }

  @RequestMapping(value = RestUrlPaths.TALE_TYPE_CONTROLLER_BASE_URL, method = RequestMethod.POST)
  public ResponseEntity<TaleType> add(HttpServletRequest request, @RequestBody TaleType toBeSaved) {
    log.info("Save new tale type: {}", toBeSaved);
    TaleType saved = taleTypeDao.save(toBeSaved);
    return new ResponseEntity<>(saved, HttpStatus.CREATED);
  }

  @RequestMapping(value = RestUrlPaths.TALE_TYPE_CONTROLLER_BASE_URL, method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<TaleType> update(TaleType toBeSaved) {
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
