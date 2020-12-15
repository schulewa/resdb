package com.apschulewitz.resdb.refdata.controller;

import com.apschulewitz.resdb.common.controller.AbstractController;
import com.apschulewitz.resdb.common.model.EntityTypeEnum;
import com.apschulewitz.resdb.common.utils.LoggingUtils;
import com.apschulewitz.resdb.config.RestUrlPaths;
import com.apschulewitz.resdb.refdata.model.dto.CountryDto;
import com.apschulewitz.resdb.refdata.service.CountryService;
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
public class CountryController extends AbstractController<CountryDto, Long> {

  private CountryService countryService;

  public CountryController(CountryService countryService) {
    this.countryService = countryService;
  }

  @RequestMapping(value = RestUrlPaths.COUNTRY_CONTROLLER_BASE_URL, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<List<CountryDto>> findAll(@RequestBody Boolean onlyActive) {
    LoggingUtils.logStartOfFindAllRequest(EntityTypeEnum.COUNTRY);
    List<CountryDto> countries = countryService.findAll(onlyActive);
    LoggingUtils.logEndOfFindAllRequest(EntityTypeEnum.COUNTRY);
    return new ResponseEntity<>(countries, HttpStatus.OK);
  }

//  @Override
//  public ResponseEntity<List<Country>> findAllActive() {
//    log.info("Processing request to find all active countries");
//    List<Country> countries = new ArrayList<>();
//    Iterable<Country> iter = countryDao.findByStatusIn(VersionStatus.getLiveStatuses());
//    StreamSupport.stream(iter.spliterator(), false)
//      .forEach(countries::add);
//    return new ResponseEntity<>(countries, HttpStatus.OK);
//  }

  @RequestMapping(value = RestUrlPaths.COUNTRY_CONTROLLER_BASE_URL, method = RequestMethod.POST)
  public ResponseEntity<CountryDto> add(@RequestBody CountryDto toBeSaved) {
    LoggingUtils.logStartOfAddRequest(EntityTypeEnum.COUNTRY, toBeSaved);
    CountryDto saved = countryService.add(toBeSaved);
    HttpStatus status;
    if (saved == null) {
      status = HttpStatus.INTERNAL_SERVER_ERROR;
    } else {
      status = HttpStatus.CREATED;
    }
    return new ResponseEntity<>(saved, status);
  }

  @RequestMapping(value = RestUrlPaths.COUNTRY_CONTROLLER_BASE_URL + "/{id}", method = RequestMethod.DELETE)
  public ResponseEntity<CountryDto> delete(@PathVariable Long id) {
    LoggingUtils.logStartOfDeleteRequest(EntityTypeEnum.COUNTRY, id);
    CountryDto deleted = countryService.deleteById(id);
    HttpStatus status;
    if (deleted == null) {
      status = HttpStatus.BAD_REQUEST;
    } else {
      status = HttpStatus.OK;
    }
    LoggingUtils.logEndOfDeleteRequest(EntityTypeEnum.COUNTRY, id);
    return new ResponseEntity<>(deleted, status);
  }

  @RequestMapping(value = RestUrlPaths.COUNTRY_CONTROLLER_BASE_URL, method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<CountryDto> update(@RequestBody CountryDto toBeSaved) {
    LoggingUtils.logStartOfUpdateRequest(EntityTypeEnum.COUNTRY, toBeSaved);
    CountryDto updated = countryService.update(toBeSaved);
    return new ResponseEntity<>(updated, HttpStatus.OK);
  }

}
