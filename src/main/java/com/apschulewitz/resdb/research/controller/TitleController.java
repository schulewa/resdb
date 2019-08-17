package com.apschulewitz.resdb.research.controller;

import com.apschulewitz.resdb.common.controller.AbstractController;
import com.apschulewitz.resdb.common.model.entity.Title;
import com.apschulewitz.resdb.common.model.entity.VersionStatus;
import com.apschulewitz.resdb.config.RestUrlPaths;
import com.apschulewitz.resdb.research.model.dao.TitleDao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * Created by adrianschulewitz on 22/04/2017.
 */
@RestController()
@Slf4j
public class TitleController extends AbstractController<Title, Long> {

  @Autowired
  private TitleDao titleDao;

  @RequestMapping(value = RestUrlPaths.TITLE_CONTROLLER_BASE_URL, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<List<Title>> findAll() {

    Iterable<Title> liveEntries = titleDao.findByStatusIn(VersionStatus.getLiveStatuses());
    List<Title> titles = StreamSupport.stream(liveEntries.spliterator(), false).collect(Collectors.toList());

    return new ResponseEntity<>(titles, HttpStatus.OK);
  }

  @RequestMapping(value = RestUrlPaths.TITLE_CONTROLLER_BASE_URL, method = RequestMethod.POST)
  public ResponseEntity<Title> add(HttpServletRequest request, @RequestBody Title toBeSaved) {
    log.info("Save new title: {}", toBeSaved);
    Title saved = titleDao.save(toBeSaved);
    return new ResponseEntity<>(saved, HttpStatus.CREATED);
  }

  @RequestMapping(value = RestUrlPaths.TITLE_CONTROLLER_BASE_URL + "/{id}", method = RequestMethod.DELETE)
  public ResponseEntity<Title> delete(@PathVariable long id) {
    log.info("Marking title [{}] for deletion", id);
    Optional<Title> existing = titleDao.findById(id);

    if (existing.isEmpty()) {
      log.error("No existing title found for id {} - unable to mark for deletion", id);
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    existing.get().setStatus(VersionStatus.Cancel);
    Title saved = titleDao.save(existing.get());
    return new ResponseEntity<>(saved, HttpStatus.OK);
  }

  @RequestMapping(value = RestUrlPaths.TITLE_CONTROLLER_BASE_URL, method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Title> update(@RequestBody Title toBeSaved) {
    log.info("Update existing title: {}", toBeSaved);
    Optional<Title> existing = titleDao.findById(toBeSaved.getId());

    if (existing.isEmpty()) {
      log.error("No existing title found for id {} - update aborted for: {}", toBeSaved.getId(), toBeSaved);
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    Title saved = titleDao.save(toBeSaved);
    return new ResponseEntity<>(saved, HttpStatus.OK);
  }

}
