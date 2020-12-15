/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2020 All Rights Reserved.
 */
package com.apschulewitz.resdb.research.service;

import com.apschulewitz.resdb.common.model.mapper.ClassificationCollectionMapper;
import com.apschulewitz.resdb.common.model.entity.VersionStatus;
import com.apschulewitz.resdb.research.model.dao.ClassificationDao;
import com.apschulewitz.resdb.research.model.dto.ClassificationCollectionDto;
import com.apschulewitz.resdb.research.model.entity.ClassificationCollection;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 * @author Adrian.Schulewitz
 * @version $Id: ClassificationService.java, v 0.1 2020-10-18 20:02 adrianschulewitz.hds Exp $$
 */
@Service
@Slf4j
public class ClassificationService {

  private ClassificationDao classificationDao;

  private ClassificationCollectionMapper classificationCollectionMapper;

  public ClassificationService(ClassificationDao classificationDao,
                               ClassificationCollectionMapper classificationCollectionMapper) {
    this.classificationDao = classificationDao;
    this.classificationCollectionMapper = classificationCollectionMapper;
  }

  public List<ClassificationCollectionDto> findAll() {
    Iterable<ClassificationCollection> classificationCollectionIter =  classificationDao.findAll();
    Stream<ClassificationCollection> stream = StreamSupport.stream(classificationCollectionIter.spliterator(), false);
    List<ClassificationCollectionDto> classificationCollectionDtos = stream
      .map(classificationCollectionMapper::toDto)
      .collect(Collectors.toList());
    return classificationCollectionDtos;
  }

  public List<ClassificationCollectionDto> findAllActive() {
    List<ClassificationCollection> classificationCollections =  classificationDao.findByStatusIn(VersionStatus.getLiveStatuses());
    List<ClassificationCollectionDto> classificationCollectionDtos = classificationCollections.stream()
      .map(classificationCollectionMapper::toDto)
      .collect(Collectors.toList());
    return classificationCollectionDtos;
  }

  public List<ClassificationCollectionDto> findByNameStartsWith(String name) {
    List<ClassificationCollection> classificationCollections = classificationDao.findByStatusInAndNameStartsWith(VersionStatus.getLiveStatuses(), name);
    List<ClassificationCollectionDto> classificationCollectionDtos = classificationCollections.stream()
      .map(classificationCollectionMapper::toDto)
      .collect(Collectors.toList());
    return classificationCollectionDtos;
  }

  public ClassificationCollectionDto findById(Long id) {
    Optional<ClassificationCollection> optionalClassificationCollection = classificationDao.findById(id);
    return optionalClassificationCollection
      .map(classificationCollectionMapper::toDto)
      .orElse(constructNullClassificationCollectionDto());
  }

  public ClassificationCollectionDto delete(Long id) {
    if (id == null) {
      log.error("Classification collection identifier is not set so unable to delete");
      return null;
    }

    log.info("Marking collection as deleted for identifier {}", id);
    Optional<ClassificationCollection> optionalClassificationCollection = classificationDao.findById(id);
    if (optionalClassificationCollection.isPresent()) {
      ClassificationCollection collection = optionalClassificationCollection.get();
      if (VersionStatus.getLiveStatuses().contains(collection.getStatus())) {
        collection.setStatus(VersionStatus.Cancel);
        collection.setLastUpdated(LocalDateTime.now());
        // TODO set updatedBy from current logged in user
        ClassificationCollection saved = classificationDao.save(collection);
        return classificationCollectionMapper.toDto(saved);
      } else {
        log.info("Collection for identifier {} is not live so unable to mark as deleted", id);
        return null;
      }
    } else {
      log.info("Collection not found for identifier {} so unable to mark as deleted", id);
      return null;
    }
  }

  public ClassificationCollectionDto save(ClassificationCollectionDto dtoToBeSaved) {
    ClassificationCollection entityToBeSaved = classificationCollectionMapper.toEntity(dtoToBeSaved);
    if (entityToBeSaved.getId() != null) {
      entityToBeSaved.setLastUpdated(LocalDateTime.now());
      // TODO how to retrieve current logged in user to set on entity
    }
    ClassificationCollection savedEntity = classificationDao.save(entityToBeSaved);
    return classificationCollectionMapper.toDto(savedEntity);
  }

  protected ClassificationCollectionDto constructNullClassificationCollectionDto() {
    return null;
  }
}
