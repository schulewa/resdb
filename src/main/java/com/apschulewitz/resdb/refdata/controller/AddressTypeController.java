package com.apschulewitz.resdb.refdata.controller;

import com.apschulewitz.resdb.common.controller.AbstractController;
import com.apschulewitz.resdb.config.RestUrlPaths;
import com.apschulewitz.resdb.refdata.model.dao.AddressTypeDao;
import com.apschulewitz.resdb.refdata.model.entity.AddressType;
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
public class AddressTypeController extends AbstractController<AddressType, Long> {

  @Autowired
  private AddressTypeDao addressTypeDao;

  @RequestMapping(value = RestUrlPaths.ADDRESS_TYPE_CONTROLLER_BASE_URL, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<List<AddressType>> findAll() {

    List<AddressType> addressTypes = new ArrayList<>();
    Iterable<AddressType> iter = addressTypeDao.findAll();
    StreamSupport.stream(iter.spliterator(), false)
      .forEach(at -> addressTypes.add(at));

    return new ResponseEntity<>(addressTypes, HttpStatus.OK);
  }

  @RequestMapping(value = RestUrlPaths.ADDRESS_TYPE_CONTROLLER_BASE_URL, method = RequestMethod.POST)
  public ResponseEntity<AddressType> add(HttpServletRequest request, @RequestBody AddressType toBeSaved) {
    log.info("Save new address type: {}", toBeSaved);
    AddressType saved = addressTypeDao.save(toBeSaved);
    return new ResponseEntity<>(saved, HttpStatus.CREATED);
  }

  @RequestMapping(value = RestUrlPaths.ADDRESS_TYPE_CONTROLLER_BASE_URL, method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<AddressType> update(AddressType toBeSaved) {
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