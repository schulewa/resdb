package com.apschulewitz.resdb.refdata.controller;

import com.apschulewitz.resdb.common.controller.AbstractController;
import com.apschulewitz.resdb.common.model.entity.VersionStatus;
import com.apschulewitz.resdb.config.RestUrlPaths;
import com.apschulewitz.resdb.refdata.model.dao.MeasureTypeDao;
import com.apschulewitz.resdb.refdata.model.entity.LanguageGroup;
import com.apschulewitz.resdb.refdata.model.entity.MeasureType;
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
public class MeasureTypeController extends AbstractController<MeasureType, Long> {

  private MeasureTypeDao measureTypeDao;

  public MeasureTypeController(MeasureTypeDao measureTypeDao) {
    this.measureTypeDao = measureTypeDao;
  }

  @RequestMapping(value = RestUrlPaths.MEASURE_TYPE_CONTROLLER_BASE_URL, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<List<MeasureType>> findAll() {

    List<MeasureType> measureTypes = new ArrayList<>();
    Iterable<MeasureType> iter = measureTypeDao.findByStatusIn(VersionStatus.getLiveStatuses());
    StreamSupport.stream(iter.spliterator(), false)
      .forEach(at -> measureTypes.add(at));

    return new ResponseEntity<>(measureTypes, HttpStatus.OK);
  }

  @RequestMapping(value = RestUrlPaths.MEASURE_TYPE_CONTROLLER_BASE_URL, method = RequestMethod.POST)
  public ResponseEntity<MeasureType> add(HttpServletRequest request, @RequestBody MeasureType toBeSaved) {
    log.info("Save new measure type: {}", toBeSaved);
    MeasureType saved = measureTypeDao.save(toBeSaved);
    return new ResponseEntity<>(saved, HttpStatus.CREATED);
  }

  @RequestMapping(value = RestUrlPaths.MEASURE_TYPE_CONTROLLER_BASE_URL + "/{id}", method = RequestMethod.DELETE)
  public ResponseEntity<MeasureType> delete(@PathVariable long id) {
    log.info("Marking measure type [{}] for deletion", id);
    Optional<MeasureType> existing = measureTypeDao.findById(id);

    if (existing.isEmpty()) {
      log.error("No existing measure type found for id {} - unable to mark for deletion", id);
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    existing.get().setStatus(VersionStatus.Cancel);
    MeasureType saved = measureTypeDao.save(existing.get());
    return new ResponseEntity<>(saved, HttpStatus.OK);
  }

  @RequestMapping(value = RestUrlPaths.MEASURE_TYPE_CONTROLLER_BASE_URL, method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<MeasureType> update(@RequestBody MeasureType toBeSaved) {
    log.info("Update existing measure type: {}", toBeSaved);
    Optional<MeasureType> existing = measureTypeDao.findById(toBeSaved.getId());

    if (existing.isEmpty()) {
      log.error("No existing measure type found for id {} - update aborted for: {}", toBeSaved.getId(), toBeSaved);
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    MeasureType saved = measureTypeDao.save(toBeSaved);
    return new ResponseEntity<>(saved, HttpStatus.OK);
  }

}
