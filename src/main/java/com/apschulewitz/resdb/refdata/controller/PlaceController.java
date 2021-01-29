package com.apschulewitz.resdb.refdata.controller;

import com.apschulewitz.resdb.common.controller.AbstractController;
import com.apschulewitz.resdb.common.model.EntityTypeEnum;
import com.apschulewitz.resdb.common.model.entity.VersionStatus;
import com.apschulewitz.resdb.config.RestUrlPaths;
import com.apschulewitz.resdb.refdata.model.dao.PlaceDao;
import com.apschulewitz.resdb.refdata.model.dto.PlaceDto;
import com.apschulewitz.resdb.refdata.model.entity.Place;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

/**
 * Created by adrianschulewitz on 22/04/2017.
 */
@RestController()
@Slf4j
@Api(value = "Place controller", tags = {"Place"})
public class PlaceController extends AbstractController<Place, Long> {

  private PlaceDao placeDao;

  public PlaceController(PlaceDao placeDao) {
    this.placeDao = placeDao;
  }

  @ApiOperation(
    value = "Find all places",
    httpMethod = "GET",
    notes = "Finds all places, active and inactive",
    response = List.class
  )
  @ApiResponses(value = {
    @ApiResponse(code = 200, message = "Request to find all places completed successfully.")
  })
  @RequestMapping(value = RestUrlPaths.PLACE_CONTROLLER_BASE_URL, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<List<Place>> findAll(@ApiParam(name = "onlyActive",
                                                        allowableValues = "true, false",
                                                        required = true,
                                                        type = "Boolean",
                                                        value = "A boolean. True specifies to only include active places, false to exclude inactive places.")
                                               @RequestParam Boolean onlyActive) {
    logStartOfFindAllRequest(EntityTypeEnum.PLACE);
    List<Place> places = new ArrayList<>();
    Iterable<Place> iter;
    if (onlyActive)
      iter = placeDao.findByStatusIn(VersionStatus.getLiveStatuses());
    else
      iter = placeDao.findAll();

    StreamSupport.stream(iter.spliterator(), false)
      .forEach(places::add);
    logEndOfFindAllRequest(EntityTypeEnum.PLACE);
    return new ResponseEntity<>(places, HttpStatus.OK);
  }

  @ApiOperation(
    value = "Add new place",
    notes = "Add new place. Returns the saved place.",
    httpMethod = "POST",
    response = PlaceDto.class
  )
  @ApiResponses(value = {
    @ApiResponse(code = 200, message = "Request to add the specified place completed successfully.")
  })
  @RequestMapping(value = RestUrlPaths.PLACE_CONTROLLER_BASE_URL, method = RequestMethod.POST)
  public ResponseEntity<Place> add(@ApiParam(name = "toBeSaved",
                                              required = true,
                                              type = "PlaceDto",
                                              value = "A non-null instance of a PlaceDto")
                                     @RequestBody Place toBeSaved) {
    logStartOfAddRequest(EntityTypeEnum.PLACE, toBeSaved);
    Place saved;
    try {
        saved = placeDao.save(toBeSaved);
    } catch (Exception e) {
        log.error("Error saving place entity", e);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
    }
    logEndOfAddRequest(EntityTypeEnum.PLACE, saved);
    return new ResponseEntity<>(saved, HttpStatus.CREATED);
  }

  @ApiOperation(
    value = "Delete a place",
    notes = "Delete the place matching the identifier by marking it as cancelled(inactive).",
    httpMethod = "DELETE",
    response = PlaceDto.class)
  @RequestMapping(value = RestUrlPaths.PLACE_CONTROLLER_BASE_URL + "/{id}", method = RequestMethod.DELETE)
  public ResponseEntity<Place> delete(@ApiParam(name = "id",
                                                required = true,
                                                type = "Long",
                                                value = "A positive Long number identifying the place to be deleted")
                                        @PathVariable Long id) {
    logStartOfDeleteRequest(EntityTypeEnum.PLACE, id);
    Optional<Place> existing = placeDao.findById(id);

    if (existing.isEmpty()) {
      log.error("No existing place found for id {} - unable to mark for deletion", id);
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    existing.get().setStatus(VersionStatus.Cancel);
    Place saved = placeDao.save(existing.get());
    logEndOfDeleteRequest(EntityTypeEnum.PLACE, saved);
    return new ResponseEntity<>(saved, HttpStatus.OK);
  }

  @ApiOperation(
    value = "Update a place",
    notes = "Update the place using the supplied data. The identifier must identify an active place",
    httpMethod = "PUT",
    response = PlaceDto.class)
  @RequestMapping(value = RestUrlPaths.PLACE_CONTROLLER_BASE_URL, method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Place> update(@ApiParam(name = "toBeSaved",
                                                required = true,
                                                type = "PlaceDto",
                                                value = "A non-null PlaceDto")
                                        @RequestBody Place toBeSaved) {
    logStartOfUpdateRequest(EntityTypeEnum.PLACE, toBeSaved);
    Optional<Place> existing = placeDao.findById(toBeSaved.getId());

    if (existing.isEmpty()) {
      log.error("No existing place found for id {} - update aborted for: {}", toBeSaved.getId(), toBeSaved);
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    Place saved = placeDao.save(toBeSaved);
    logEndOfUpdateRequest(EntityTypeEnum.PLACE, saved);
    return new ResponseEntity<>(saved, HttpStatus.OK);
  }

}
