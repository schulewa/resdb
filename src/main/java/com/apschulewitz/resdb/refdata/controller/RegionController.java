package com.apschulewitz.resdb.refdata.controller;

import com.apschulewitz.resdb.common.controller.AbstractController;
import com.apschulewitz.resdb.common.model.EntityTypeEnum;
import com.apschulewitz.resdb.common.model.entity.VersionStatus;
import com.apschulewitz.resdb.config.RestUrlPaths;
import com.apschulewitz.resdb.refdata.model.dao.RegionDao;
import com.apschulewitz.resdb.refdata.model.dto.ReferenceTypeDto;
import com.apschulewitz.resdb.refdata.model.dto.RegionDto;
import com.apschulewitz.resdb.refdata.model.entity.ReferenceType;
import com.apschulewitz.resdb.refdata.model.entity.Region;
import com.apschulewitz.resdb.refdata.service.RegionService;
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
public class RegionController extends AbstractController<RegionDto, Long> {

  private RegionService regionService;

  public RegionController(RegionService regionService) {
    this.regionService = regionService;
  }

  @RequestMapping(value = RestUrlPaths.REGION_CONTROLLER_BASE_URL, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<List<RegionDto>> findAll(@RequestBody Boolean onlyActive) {
    logStartOfFindAllRequest(EntityTypeEnum.REGION);
    List<RegionDto> regions = new ArrayList<>();
    if (onlyActive)
      regions.addAll(regionService.findAll(true));
    else
      regions.addAll(regionService.findAll(false));

    logEndOfFindAllRequest(EntityTypeEnum.REGION);
    return new ResponseEntity<>(regions, HttpStatus.OK);
  }

//  @Override
//  public ResponseEntity<List<Region>> findAllActive() {
//    logStartOfFindAllActiveRequest(EntityTypeEnum.REGION);
//    List<Region> regions = new ArrayList<>();
//    Iterable<Region> iter = regionService.findByStatusIn(VersionStatus.getLiveStatuses());
//    StreamSupport.stream(iter.spliterator(), false)
//      .forEach(regions::add);
//    logEndOfFindAllActiveRequest(EntityTypeEnum.REGION);
//    return new ResponseEntity<>(regions, HttpStatus.OK);
//  }

  @RequestMapping(value = RestUrlPaths.REGION_CONTROLLER_BASE_URL, method = RequestMethod.POST)
  public ResponseEntity<RegionDto> add(@RequestBody RegionDto toBeSaved) {
    logStartOfAddRequest(EntityTypeEnum.REGION, toBeSaved);
    RegionDto saved = regionService.add(toBeSaved);
    logEndOfAddRequest(EntityTypeEnum.REGION, saved);
    return new ResponseEntity<>(saved, HttpStatus.CREATED);
  }

  @RequestMapping(value = RestUrlPaths.REGION_CONTROLLER_BASE_URL + "/{id}", method = RequestMethod.DELETE)
  public ResponseEntity<RegionDto> delete(@PathVariable Long id) {
    logStartOfDeleteRequest(EntityTypeEnum.REGION, id);
    RegionDto deleted = regionService.deleteById(id);
    logEndOfDeleteRequest(EntityTypeEnum.REGION, deleted);
    return new ResponseEntity<>(deleted, HttpStatus.OK);
  }

  @RequestMapping(value = RestUrlPaths.REGION_CONTROLLER_BASE_URL, method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<RegionDto> update(RegionDto toBeSaved) {
    logStartOfUpdateRequest(EntityTypeEnum.REGION, toBeSaved);
    RegionDto updated = regionService.update(toBeSaved);
    logEndOfUpdateRequest(EntityTypeEnum.REGION, updated);
    return new ResponseEntity<>(updated, HttpStatus.OK);
  }

}
