package com.apschulewitz.resdb.refdata.controller;

import com.apschulewitz.resdb.common.controller.AbstractController;
import com.apschulewitz.resdb.config.RestUrlPaths;
import com.apschulewitz.resdb.refdata.model.dao.LanguageGroupDao;
import com.apschulewitz.resdb.refdata.model.entity.LanguageGroup;
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
public class LanguageGroupController extends AbstractController<LanguageGroup, Long> {

  @Autowired
  private LanguageGroupDao languageGroupDao;

  @RequestMapping(value = RestUrlPaths.LANGUAGE_GROUP_CONTROLLER_BASE_URL, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<List<LanguageGroup>> findAll() {

    List<LanguageGroup> languageGroups = new ArrayList<>();
    Iterable<LanguageGroup> iter = languageGroupDao.findAll();
    StreamSupport.stream(iter.spliterator(), false)
      .forEach(at -> languageGroups.add(at));
    log.info("findAll: {} language groups found", languageGroups.size());
    return new ResponseEntity<>(languageGroups, HttpStatus.OK);
  }

  @RequestMapping(value = RestUrlPaths.LANGUAGE_GROUP_CONTROLLER_BASE_URL, method = RequestMethod.POST)
  public ResponseEntity<LanguageGroup> add(HttpServletRequest request, @RequestBody LanguageGroup toBeSaved) {
    log.info("Save new language group: {}", toBeSaved);
    LanguageGroup saved = languageGroupDao.save(toBeSaved);
    return new ResponseEntity<>(saved, HttpStatus.CREATED);
  }

  @RequestMapping(value = RestUrlPaths.LANGUAGE_GROUP_CONTROLLER_BASE_URL, method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<LanguageGroup> update(LanguageGroup toBeSaved) {
    log.info("Update existing language group: {}", toBeSaved);
    Optional<LanguageGroup> existing = languageGroupDao.findById(toBeSaved.getId());

    if (existing.isEmpty()) {
      log.error("No existing language group found for id {} - update aborted for: {}", toBeSaved.getId(), toBeSaved);
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    LanguageGroup saved = languageGroupDao.save(toBeSaved);
    return new ResponseEntity<>(saved, HttpStatus.OK);
  }

}
