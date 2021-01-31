package com.apschulewitz.resdb.research.controller;

import com.apschulewitz.resdb.common.controller.AbstractController;
import com.apschulewitz.resdb.common.model.EntityTypeEnum;
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
  public ResponseEntity<List<Title>> findAll(@RequestBody Boolean onlyActive) {
    logStartOfFindAllRequest(EntityTypeEnum.TITLE);
    Iterable<Title> iter;
    if (onlyActive)
      iter = titleDao.findByStatusIn(VersionStatus.getLiveStatuses());
    else
      iter = titleDao.findAll();

    List<Title> titles = StreamSupport.stream(iter.spliterator(), false).collect(Collectors.toList());
    logEndOfFindAllRequest(EntityTypeEnum.TITLE);
    return new ResponseEntity<>(titles, HttpStatus.OK);
  }

//  @Override
//  public ResponseEntity<List<Title>> findAllActive() {
//    logStartOfFindAllActiveRequest(EntityTypeEnum.TITLE);
//    Iterable<Title> liveEntries = titleDao.findByStatusIn(VersionStatus.getLiveStatuses());
//    List<Title> titles = StreamSupport.stream(liveEntries.spliterator(), false).collect(Collectors.toList());
//    logEndOfFindAllActiveRequest(EntityTypeEnum.TITLE);
//    return new ResponseEntity<>(titles, HttpStatus.OK);
//  }

  @RequestMapping(value = RestUrlPaths.TITLE_CONTROLLER_BASE_URL, method = RequestMethod.POST)
  public ResponseEntity<Title> add(@RequestBody Title toBeSaved) {
    logStartOfAddRequest(EntityTypeEnum.TITLE, toBeSaved);
    Title saved = titleDao.save(toBeSaved);
    logEndOfAddRequest(EntityTypeEnum.TITLE, saved);
    return new ResponseEntity<>(saved, HttpStatus.CREATED);
  }

  @RequestMapping(value = RestUrlPaths.TITLE_CONTROLLER_BASE_URL + "/{id}", method = RequestMethod.DELETE)
  public ResponseEntity<Title> delete(@PathVariable Long id) {
    logStartOfDeleteRequest(EntityTypeEnum.TITLE, id);
    Optional<Title> existing = titleDao.findById(id);

    if (existing.isEmpty()) {
      log.error("No existing title found for id {} - unable to mark for deletion", id);
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    existing.get().setStatus(VersionStatus.Cancel);
    Title saved = titleDao.save(existing.get());
    logEndOfDeleteRequest(EntityTypeEnum.TITLE, saved);
    return new ResponseEntity<>(saved, HttpStatus.OK);
  }

  @RequestMapping(value = RestUrlPaths.TITLE_CONTROLLER_BASE_URL, method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Title> update(@RequestBody Title toBeSaved) {
    logStartOfUpdateRequest(EntityTypeEnum.TITLE, toBeSaved);
    Optional<Title> existing = titleDao.findById(toBeSaved.getId());

    if (existing.isEmpty()) {
      log.error("No existing title found for id {} - update aborted for: {}", toBeSaved.getId(), toBeSaved);
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    Title saved = titleDao.save(toBeSaved);
    logEndOfUpdateRequest(EntityTypeEnum.TITLE, saved);
    return new ResponseEntity<>(saved, HttpStatus.OK);
  }

}
