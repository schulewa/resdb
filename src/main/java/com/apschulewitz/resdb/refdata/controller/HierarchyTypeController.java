package com.apschulewitz.resdb.refdata.controller;

import com.apschulewitz.resdb.common.controller.AbstractController;
import com.apschulewitz.resdb.config.RestUrlPaths;
import com.apschulewitz.resdb.refdata.model.dao.EventTypeGroupDao;
import com.apschulewitz.resdb.refdata.model.dao.HierarchyTypeDao;
import com.apschulewitz.resdb.refdata.model.entity.HierarchyType;
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
public class HierarchyTypeController extends AbstractController<HierarchyType, Long> {

  @Autowired
  private HierarchyTypeDao hierarchyTypeDao;

  @RequestMapping(value = RestUrlPaths.HIERARCHY_TYPE_CONTROLLER_BASE_URL, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<List<HierarchyType>> findAll() {

    List<HierarchyType> hierarchyTypes = new ArrayList<>();
    Iterable<HierarchyType> iter = hierarchyTypeDao.findAll();
    StreamSupport.stream(iter.spliterator(), false)
      .forEach(at -> hierarchyTypes.add(at));
    log.info("findAll: {} hierarchy types found", hierarchyTypes.size());
    return new ResponseEntity<>(hierarchyTypes, HttpStatus.OK);
  }

  @RequestMapping(value = RestUrlPaths.HIERARCHY_TYPE_CONTROLLER_BASE_URL, method = RequestMethod.POST)
  public ResponseEntity<HierarchyType> add(HttpServletRequest request, @RequestBody HierarchyType toBeSaved) {
    log.info("Save new hierarchy type: {}", toBeSaved);
    HierarchyType saved = hierarchyTypeDao.save(toBeSaved);
    return new ResponseEntity<>(saved, HttpStatus.CREATED);
  }

  @RequestMapping(value = RestUrlPaths.HIERARCHY_TYPE_CONTROLLER_BASE_URL, method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<HierarchyType> update(HierarchyType toBeSaved) {
    log.info("Update existing hierarchy type: {}", toBeSaved);
    Optional<HierarchyType> existing = hierarchyTypeDao.findById(toBeSaved.getId());

    if (existing.isEmpty()) {
      log.error("No existing hierarchy type found for id {} - update aborted for: {}", toBeSaved.getId(), toBeSaved);
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    HierarchyType saved = hierarchyTypeDao.save(toBeSaved);
    return new ResponseEntity<>(saved, HttpStatus.OK);
  }

}
