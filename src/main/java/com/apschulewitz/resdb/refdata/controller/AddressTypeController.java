package com.apschulewitz.resdb.refdata.controller;

import com.apschulewitz.resdb.common.controller.AbstractController;
import com.apschulewitz.resdb.common.model.EntityTypeEnum;
import com.apschulewitz.resdb.common.utils.LoggingUtils;
import com.apschulewitz.resdb.config.RestUrlPaths;
import com.apschulewitz.resdb.refdata.model.dto.AddressTypeDto;
import com.apschulewitz.resdb.refdata.service.AddressTypeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by adrianschulewitz on 22/04/2017.
 */
@RestController()
@Slf4j
public class AddressTypeController extends AbstractController<AddressTypeDto, Long> {

  private AddressTypeService addressTypeService;

  public AddressTypeController(AddressTypeService addressTypeService) {
    this.addressTypeService = addressTypeService;
  }

  @RequestMapping(value = RestUrlPaths.ADDRESS_TYPE_CONTROLLER_BASE_URL, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<List<AddressTypeDto>> findAll(@RequestBody Boolean onlyActive) {
    LoggingUtils.logStartOfFindAllRequest(EntityTypeEnum.ADDRESS_TYPE);
    List<AddressTypeDto> addressTypes = addressTypeService.findAll(true);
    LoggingUtils.logEndOfFindAllRequest(EntityTypeEnum.ADDRESS_TYPE);
    return new ResponseEntity<>(addressTypes, HttpStatus.OK);
  }

  @RequestMapping(value = RestUrlPaths.ADDRESS_TYPE_CONTROLLER_BASE_URL, method = RequestMethod.POST)
  public ResponseEntity<AddressTypeDto> add(@RequestBody AddressTypeDto toBeSaved) {
    LoggingUtils.logStartOfAddRequest(EntityTypeEnum.ADDRESS_TYPE, toBeSaved);
    AddressTypeDto saved = addressTypeService.add(toBeSaved);
    LoggingUtils.logEndOfAddRequest(EntityTypeEnum.ADDRESS_TYPE, saved);
    return new ResponseEntity<>(saved, HttpStatus.CREATED);
  }

  @RequestMapping(value = RestUrlPaths.ADDRESS_TYPE_CONTROLLER_BASE_URL + "/{id}", method = RequestMethod.DELETE)
  public ResponseEntity<AddressTypeDto> delete(@PathVariable Long id) {
    LoggingUtils.logStartOfDeleteRequest(EntityTypeEnum.ADDRESS_TYPE, id);
    AddressTypeDto deleted = addressTypeService.deleteById(id);

    HttpStatus result;
    if (deleted == null)
      result = HttpStatus.NOT_FOUND;
    else
      result = HttpStatus.OK;

    LoggingUtils.logEndOfDeleteRequest(EntityTypeEnum.ADDRESS_TYPE, deleted);
    return new ResponseEntity<>(deleted, result);
  }

  @RequestMapping(value = RestUrlPaths.ADDRESS_TYPE_CONTROLLER_BASE_URL, method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<AddressTypeDto> update(@RequestBody AddressTypeDto toBeSaved) {
    LoggingUtils.logStartOfUpdateRequest(EntityTypeEnum.ADDRESS_TYPE, toBeSaved);
    HttpStatus result;
    AddressTypeDto updated = addressTypeService.update(toBeSaved);
    if (updated == null) {
      result = HttpStatus.NOT_FOUND;
    } else {
      result= HttpStatus.OK;
    }
    LoggingUtils.logEndOfUpdateRequest(EntityTypeEnum.ADDRESS_TYPE, updated);
    return new ResponseEntity<>(updated, result);
  }

}
