package com.apschulewitz.resdb.refdata.controller;

import com.apschulewitz.resdb.common.controller.AbstractController;
import com.apschulewitz.resdb.common.model.entity.VersionStatus;
import com.apschulewitz.resdb.config.RestUrlPaths;
import com.apschulewitz.resdb.refdata.model.dao.AddressTypeDao;
import com.apschulewitz.resdb.refdata.model.entity.AddressType;
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
public class AddressTypeController extends AbstractController<AddressType, Long> {

  @Autowired
  private AddressTypeDao addressTypeDao;

  @RequestMapping(value = RestUrlPaths.ADDRESS_TYPE_CONTROLLER_BASE_URL, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<List<AddressType>> findAll() {

    Iterable<AddressType> liveEntries = addressTypeDao.findByStatusIn(VersionStatus.getLiveStatuses());
    List<AddressType> addressTypes = StreamSupport.stream(liveEntries.spliterator(), false).collect(Collectors.toList());

    return new ResponseEntity<>(addressTypes, HttpStatus.OK);
  }

  @RequestMapping(value = RestUrlPaths.ADDRESS_TYPE_CONTROLLER_BASE_URL, method = RequestMethod.POST)
  public ResponseEntity<AddressType> add(HttpServletRequest request, @RequestBody AddressType toBeSaved) {
    log.info("Save new address type: {}", toBeSaved);
    AddressType saved = addressTypeDao.save(toBeSaved);
    return new ResponseEntity<>(saved, HttpStatus.CREATED);
  }

  @RequestMapping(value = RestUrlPaths.ADDRESS_TYPE_CONTROLLER_BASE_URL + "/{id}", method = RequestMethod.DELETE)
  public ResponseEntity<AddressType> delete(@PathVariable long id) {
    log.info("Marking address type [{}] for deletion", id);
    Optional<AddressType> existing = addressTypeDao.findById(id);

    if (existing.isEmpty()) {
      log.error("No existing address type found for id {} - unable to mark for deletion", id);
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    existing.get().setStatus(VersionStatus.Cancel);
    AddressType saved = addressTypeDao.save(existing.get());
    return new ResponseEntity<>(saved, HttpStatus.OK);
  }

  @RequestMapping(value = RestUrlPaths.ADDRESS_TYPE_CONTROLLER_BASE_URL, method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<AddressType> update(@RequestBody AddressType toBeSaved) {
    log.info("Update existing address type: {}", toBeSaved);
    Optional<AddressType> existing = addressTypeDao.findById(toBeSaved.getId());

    if (existing.isEmpty()) {
      log.error("No existing address type found for id {} - update aborted for: {}", toBeSaved.getId(), toBeSaved);
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    AddressType saved = addressTypeDao.save(toBeSaved);
    return new ResponseEntity<>(saved, HttpStatus.OK);
  }

}
