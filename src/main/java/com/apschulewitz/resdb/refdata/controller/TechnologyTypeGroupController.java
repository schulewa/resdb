package com.apschulewitz.resdb.refdata.controller;

import com.apschulewitz.resdb.common.controller.AbstractController;
import com.apschulewitz.resdb.common.model.EntityTypeEnum;
import com.apschulewitz.resdb.config.RestUrlPaths;
import com.apschulewitz.resdb.refdata.service.TechnologyTypeGroupService;
import com.apschulewitz.resdb.refdata.model.dto.TechnologyTypeGroupDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by adrianschulewitz on 22/04/2017.
 */
@RestController()
@Slf4j
public class TechnologyTypeGroupController extends AbstractController<TechnologyTypeGroupDto, Long> {

  private TechnologyTypeGroupService technologyTypeGroupService;

  public TechnologyTypeGroupController(TechnologyTypeGroupService technologyTypeGroupService) {
    this.technologyTypeGroupService = technologyTypeGroupService;
  }

  @RequestMapping(value = RestUrlPaths.TECHNOLOGY_TYPE_GROUP_CONTROLLER_BASE_URL, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<List<TechnologyTypeGroupDto>> findAll(@RequestBody Boolean onlyActive) {
    logStartOfFindAllRequest(EntityTypeEnum.TECHNOLOGY_TYPE_GROUP);
    List<TechnologyTypeGroupDto> technologyTypeGroups = technologyTypeGroupService.findAll(onlyActive);
    logEndOfFindAllRequest(EntityTypeEnum.TECHNOLOGY_TYPE_GROUP);
    return new ResponseEntity<>(technologyTypeGroups, HttpStatus.OK);
  }

  @RequestMapping(value = RestUrlPaths.TECHNOLOGY_TYPE_GROUP_CONTROLLER_BASE_URL, method = RequestMethod.POST)
  public ResponseEntity<TechnologyTypeGroupDto> add(@RequestBody TechnologyTypeGroupDto toBeSaved) {
    logStartOfAddRequest(EntityTypeEnum.TECHNOLOGY_TYPE_GROUP, toBeSaved);
    TechnologyTypeGroupDto saved = technologyTypeGroupService.add(toBeSaved);
    logEndOfAddRequest(EntityTypeEnum.TECHNOLOGY_TYPE_GROUP, saved);
    return new ResponseEntity<>(saved, HttpStatus.CREATED);
  }

  @RequestMapping(value = RestUrlPaths.TECHNOLOGY_TYPE_GROUP_CONTROLLER_BASE_URL + "/{id}", method = RequestMethod.DELETE)
  public ResponseEntity<TechnologyTypeGroupDto> delete(@PathVariable Long id) {
    logStartOfDeleteRequest(EntityTypeEnum.TECHNOLOGY_TYPE_GROUP, id);
    TechnologyTypeGroupDto deleted = technologyTypeGroupService.deleteById(id);
    HttpStatus result;
    if (deleted == null)
      result = HttpStatus.NOT_FOUND;
    else
      result = HttpStatus.OK;
    logEndOfDeleteRequest(EntityTypeEnum.TECHNOLOGY_TYPE_GROUP, deleted);
    return new ResponseEntity<>(deleted, result);
  }

  @RequestMapping(value = RestUrlPaths.TECHNOLOGY_TYPE_GROUP_CONTROLLER_BASE_URL, method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<TechnologyTypeGroupDto> update(@RequestBody TechnologyTypeGroupDto toBeSaved) {
    logStartOfUpdateRequest(EntityTypeEnum.TECHNOLOGY_TYPE_GROUP, toBeSaved);
    TechnologyTypeGroupDto saved = technologyTypeGroupService.update(toBeSaved);
    logEndOfUpdateRequest(EntityTypeEnum.TECHNOLOGY_TYPE_GROUP, saved);
    return new ResponseEntity<>(saved, HttpStatus.OK);
  }

}
