package com.apschulewitz.resdb.refdata.controller;

import com.apschulewitz.resdb.common.controller.AbstractController;
import com.apschulewitz.resdb.common.model.entity.VersionStatus;
import com.apschulewitz.resdb.config.RestUrlPaths;
import com.apschulewitz.resdb.refdata.model.dao.PublicationTypeDao;
import com.apschulewitz.resdb.refdata.model.entity.PersonType;
import com.apschulewitz.resdb.refdata.model.entity.PublicationType;
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
public class PublicationTypeController extends AbstractController<PublicationType, Long> {

  private PublicationTypeDao publicationTypeDao;

  public PublicationTypeController(PublicationTypeDao publicationTypeDao) {
    this.publicationTypeDao = publicationTypeDao;
  }

  @RequestMapping(value = RestUrlPaths.PUBLICATION_TYPE_CONTROLLER_BASE_URL, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<List<PublicationType>> findAll() {

    List<PublicationType> publicationTypes = new ArrayList<>();
    Iterable<PublicationType> iter = publicationTypeDao.findByStatusIn(VersionStatus.getLiveStatuses());
    StreamSupport.stream(iter.spliterator(), false)
      .forEach(publicationTypes::add);

    return new ResponseEntity<>(publicationTypes, HttpStatus.OK);
  }

  @RequestMapping(value = RestUrlPaths.PUBLICATION_TYPE_CONTROLLER_BASE_URL, method = RequestMethod.POST)
  public ResponseEntity<PublicationType> add(HttpServletRequest request, @RequestBody PublicationType toBeSaved) {
    log.info("Save new publication type: {}", toBeSaved);
    PublicationType saved = publicationTypeDao.save(toBeSaved);
    return new ResponseEntity<>(saved, HttpStatus.CREATED);
  }

  @RequestMapping(value = RestUrlPaths.PUBLICATION_TYPE_CONTROLLER_BASE_URL + "/{id}", method = RequestMethod.DELETE)
  public ResponseEntity<PublicationType> delete(@PathVariable long id) {
    log.info("Marking publication type [{}] for deletion", id);
    Optional<PublicationType> existing = publicationTypeDao.findById(id);

    if (existing.isEmpty()) {
      log.error("No existing publication type found for id {} - unable to mark for deletion", id);
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    existing.get().setStatus(VersionStatus.Cancel);
    PublicationType saved = publicationTypeDao.save(existing.get());
    return new ResponseEntity<>(saved, HttpStatus.OK);
  }

  @RequestMapping(value = RestUrlPaths.PUBLICATION_TYPE_CONTROLLER_BASE_URL, method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<PublicationType> update(@RequestBody PublicationType toBeSaved) {
    log.info("Update existing publication type: {}", toBeSaved);
    Optional<PublicationType> existing = publicationTypeDao.findById(toBeSaved.getId());

    if (existing.isEmpty()) {
      log.error("No existing publication type found for id {} - update aborted for: {}", toBeSaved.getId(), toBeSaved);
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    PublicationType saved = publicationTypeDao.save(toBeSaved);
    return new ResponseEntity<>(saved, HttpStatus.OK);
  }

}
