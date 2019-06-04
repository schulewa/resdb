package com.apschulewitz.resdb.refdata.controller;

import com.apschulewitz.resdb.common.controller.AbstractController;
import com.apschulewitz.resdb.config.RestUrlPaths;
import com.apschulewitz.resdb.refdata.model.dao.TechnologyTypeGroupDao;
import com.apschulewitz.resdb.refdata.model.entity.TechnologyTypeGroup;
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
public class TechnologyTypeGroupController extends AbstractController<TechnologyTypeGroup, Long> {

  @Autowired
  private TechnologyTypeGroupDao technologyTypeGroupDao;

  @RequestMapping(value = RestUrlPaths.TECHNOLOGY_TYPE_GROUP_CONTROLLER_BASE_URL, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<List<TechnologyTypeGroup>> findAll() {

    List<TechnologyTypeGroup> technologyTypeGroups = new ArrayList<>();
    Iterable<TechnologyTypeGroup> iter = technologyTypeGroupDao.findAll();
    StreamSupport.stream(iter.spliterator(), false)
      .forEach(at -> technologyTypeGroups.add(at));

    return new ResponseEntity<>(technologyTypeGroups, HttpStatus.OK);
  }

  @RequestMapping(value = RestUrlPaths.TECHNOLOGY_TYPE_GROUP_CONTROLLER_BASE_URL, method = RequestMethod.POST)
  public ResponseEntity<TechnologyTypeGroup> add(HttpServletRequest request, @RequestBody TechnologyTypeGroup toBeSaved) {
    log.info("Save new tale type: {}", toBeSaved);
    TechnologyTypeGroup saved = technologyTypeGroupDao.save(toBeSaved);
    return new ResponseEntity<>(saved, HttpStatus.CREATED);
  }

  @RequestMapping(value = RestUrlPaths.TECHNOLOGY_TYPE_GROUP_CONTROLLER_BASE_URL, method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<TechnologyTypeGroup> update(TechnologyTypeGroup toBeSaved) {
    log.info("Update existing technology type group: {}", toBeSaved);
    Optional<TechnologyTypeGroup> existing = technologyTypeGroupDao.findById(toBeSaved.getId());

    if (existing.isEmpty()) {
      log.error("No existing technology type group found for id {} - update aborted for: {}", toBeSaved.getId(), toBeSaved);
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    TechnologyTypeGroup saved = technologyTypeGroupDao.save(toBeSaved);
    return new ResponseEntity<>(saved, HttpStatus.OK);
  }

}
