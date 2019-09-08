package com.apschulewitz.resdb.refdata.controller;

import com.apschulewitz.resdb.common.controller.AbstractController;
import com.apschulewitz.resdb.common.model.entity.VersionStatus;
import com.apschulewitz.resdb.config.RestUrlPaths;
import com.apschulewitz.resdb.refdata.model.dao.ImageTypeDao;
import com.apschulewitz.resdb.refdata.model.entity.HierarchyType;
import com.apschulewitz.resdb.refdata.model.entity.ImageType;
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
public class ImageTypeController extends AbstractController<ImageType, Long> {

  private ImageTypeDao imageTypeDao;

  public ImageTypeController(ImageTypeDao imageTypeDao) {
    this.imageTypeDao = imageTypeDao;
  }

  @RequestMapping(value = RestUrlPaths.IMAGE_TYPE_CONTROLLER_BASE_URL, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<List<ImageType>> findAll() {

    List<ImageType> imageTypes = new ArrayList<>();
    Iterable<ImageType> iter = imageTypeDao.findByStatusIn(VersionStatus.getLiveStatuses());
    StreamSupport.stream(iter.spliterator(), false)
      .forEach(imageTypes::add);
    log.info("findAll: {} image types found", imageTypes.size());
    return new ResponseEntity<>(imageTypes, HttpStatus.OK);
  }

  @RequestMapping(value = RestUrlPaths.IMAGE_TYPE_CONTROLLER_BASE_URL, method = RequestMethod.POST)
  public ResponseEntity<ImageType> add(HttpServletRequest request, @RequestBody ImageType toBeSaved) {
    log.info("Save new image type: {}", toBeSaved);
    ImageType saved = imageTypeDao.save(toBeSaved);
    return new ResponseEntity<>(saved, HttpStatus.CREATED);
  }

  @RequestMapping(value = RestUrlPaths.IMAGE_TYPE_CONTROLLER_BASE_URL + "/{id}", method = RequestMethod.DELETE)
  public ResponseEntity<ImageType> delete(@PathVariable long id) {
    log.info("Marking image type [{}] for deletion", id);
    Optional<ImageType> existing = imageTypeDao.findById(id);

    if (existing.isEmpty()) {
      log.error("No existing image type found for id {} - unable to mark for deletion", id);
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    existing.get().setStatus(VersionStatus.Cancel);
    ImageType saved = imageTypeDao.save(existing.get());
    return new ResponseEntity<>(saved, HttpStatus.OK);
  }

  @RequestMapping(value = RestUrlPaths.IMAGE_TYPE_CONTROLLER_BASE_URL, method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<ImageType> update(@RequestBody ImageType toBeSaved) {
    log.info("Update existing image type: {}", toBeSaved);
    Optional<ImageType> existing = imageTypeDao.findById(toBeSaved.getId());

    if (existing.isEmpty()) {
      log.error("No existing image type found for id {} - update aborted for: {}", toBeSaved.getId(), toBeSaved);
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    ImageType saved = imageTypeDao.save(toBeSaved);
    return new ResponseEntity<>(saved, HttpStatus.OK);
  }

}
