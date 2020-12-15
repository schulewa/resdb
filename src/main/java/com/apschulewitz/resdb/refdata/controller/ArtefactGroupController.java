package com.apschulewitz.resdb.refdata.controller;

import com.apschulewitz.resdb.common.controller.AbstractController;
import com.apschulewitz.resdb.common.model.EntityTypeEnum;
import com.apschulewitz.resdb.common.model.entity.VersionStatus;
import com.apschulewitz.resdb.common.utils.LoggingUtils;
import com.apschulewitz.resdb.config.RestUrlPaths;
import com.apschulewitz.resdb.refdata.model.dao.ArtefactGroupDao;
import com.apschulewitz.resdb.refdata.model.dto.ArtefactGroupDto;
import com.apschulewitz.resdb.refdata.model.entity.ArtefactGroup;
import com.apschulewitz.resdb.refdata.service.ArtefactGroupService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * Created by adrianschulewitz on 22/04/2017.
 */
@RestController()
@Slf4j
public class ArtefactGroupController extends AbstractController<ArtefactGroupDto, Long> {

  private ArtefactGroupService artefactGroupService;

  public ArtefactGroupController(ArtefactGroupService artefactGroupService) {
    this.artefactGroupService = artefactGroupService;
  }

  @RequestMapping(value = RestUrlPaths.ARTEFACT_GROUP_CONTROLLER_BASE_URL, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<List<ArtefactGroupDto>> findAll(@RequestBody Boolean onlyActive) {
    LoggingUtils.logStartOfFindAllRequest(EntityTypeEnum.ARTEFACT_GROUP);
    List<ArtefactGroupDto> artefactGroups = artefactGroupService.findAll(onlyActive);
    LoggingUtils.logEndOfFindAllRequest(EntityTypeEnum.ARTEFACT_GROUP);
    return new ResponseEntity<>(artefactGroups, HttpStatus.OK);
  }

//  @Override
//  public ResponseEntity<List<ArtefactGroup>> findAllActive() {
//    logStartOfFindAllActiveRequest(EntityTypeEnum.ARTEFACT_GROUP);
//    Iterable<ArtefactGroup> liveEntries = artefactGroupDao.findByStatusIn(VersionStatus.getLiveStatuses());
//    List<ArtefactGroup> artefactGroups = StreamSupport.stream(liveEntries.spliterator(), false).collect(Collectors.toList());
//    logEndOfFindAllActiveRequest(EntityTypeEnum.ARTEFACT_GROUP);
//    return new ResponseEntity<>(artefactGroups, HttpStatus.OK);
//  }

  @RequestMapping(value = RestUrlPaths.ARTEFACT_GROUP_CONTROLLER_BASE_URL, method = RequestMethod.POST)
  public ResponseEntity<ArtefactGroupDto> add(@RequestBody ArtefactGroupDto toBeSaved) {
    LoggingUtils.logStartOfAddRequest(EntityTypeEnum.ARTEFACT_GROUP, toBeSaved);
    ArtefactGroupDto saved = artefactGroupService.add(toBeSaved);
    LoggingUtils.logEndOfAddRequest(EntityTypeEnum.ARTEFACT_GROUP, saved);
    return new ResponseEntity<>(saved, HttpStatus.CREATED);
  }

  @RequestMapping(value = RestUrlPaths.ARTEFACT_GROUP_CONTROLLER_BASE_URL + "/{id}", method = RequestMethod.DELETE)
  public ResponseEntity<ArtefactGroupDto> delete(@PathVariable Long id) {
    LoggingUtils.logStartOfDeleteRequest(EntityTypeEnum.ARTEFACT_GROUP, id);
    ArtefactGroupDto deleted = artefactGroupService.deleteById(id);
    HttpStatus status;
    if (deleted == null) {
      status = HttpStatus.NOT_FOUND;
    } else {
      status = HttpStatus.OK;
    }
    LoggingUtils.logEndOfDeleteRequest(EntityTypeEnum.ARTEFACT_GROUP, deleted);
    return new ResponseEntity<>(deleted, status);
  }

  @RequestMapping(value = RestUrlPaths.ARTEFACT_GROUP_CONTROLLER_BASE_URL, method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<ArtefactGroupDto> update(@RequestBody ArtefactGroupDto toBeSaved) {
    LoggingUtils.logStartOfUpdateRequest(EntityTypeEnum.ARTEFACT_GROUP, toBeSaved);
    ArtefactGroupDto saved = artefactGroupService.update(toBeSaved);
    LoggingUtils.logEndOfUpdateRequest(EntityTypeEnum.ARTEFACT_GROUP, saved);
    return new ResponseEntity<>(saved, HttpStatus.OK);
  }

}
