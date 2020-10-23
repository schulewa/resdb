/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2020 All Rights Reserved.
 */
package com.apschulewitz.resdb.research.service;

import com.apschulewitz.resdb.common.model.converter.ClassificationCollectionConverter;
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

/**
 * @author Adrian.Schulewitz
 * @version $Id: ClassificationService.java, v 0.1 2020-10-18 20:02 adrianschulewitz.hds Exp $$
 */
@Service
@Slf4j
public class ClassificationService {

  private ClassificationDao classificationDao;

  private ClassificationCollectionConverter classificationCollectionConverter;

  public ClassificationService(ClassificationDao classificationDao,
                               ClassificationCollectionConverter classificationCollectionConverter) {
    this.classificationDao = classificationDao;
    this.classificationCollectionConverter = classificationCollectionConverter;
  }

  public List<ClassificationCollectionDto> findAllLiveCollections() {
    List<ClassificationCollection> classificationCollections =  classificationDao.findByStatusIn(VersionStatus.getLiveStatuses());
    List<ClassificationCollectionDto> classificationCollectionDtos = classificationCollections.stream()
      .map(classificationCollectionConverter::toDto)
      .collect(Collectors.toList());
    return classificationCollectionDtos;
  }

  public List<ClassificationCollectionDto> findByNameStartsWith(String name) {
    List<ClassificationCollection> classificationCollections = classificationDao.findByStatusInAndNameStartsWith(VersionStatus.getLiveStatuses(), name);
    List<ClassificationCollectionDto> classificationCollectionDtos = classificationCollections.stream()
      .map(classificationCollectionConverter::toDto)
      .collect(Collectors.toList());
    return classificationCollectionDtos;
  }

  public ClassificationCollectionDto findById(Long id) {
    Optional<ClassificationCollection> optionalClassificationCollection = classificationDao.findById(id);
    return optionalClassificationCollection
      .map(classificationCollectionConverter::toDto)
      .orElse(constructNullClassificationCollectionDto());
  }

  public boolean delete(Long id) {
    if (id == null) {
      log.info("Classification collection identifier is not set so unable to delete");
      return false;
    }

    log.info("Marking collection as deleted for identifier {}", id);
    Optional<ClassificationCollection> optionalClassificationCollection = classificationDao.findById(id);
    if (optionalClassificationCollection.isPresent()) {
      ClassificationCollection collection = optionalClassificationCollection.get();
      if (VersionStatus.getLiveStatuses().contains(collection.getStatus())) {
        collection.setStatus(VersionStatus.Cancel);
        classificationDao.save(collection);
        return true;
      } else {
        log.info("Collection for identifier {} is not live so unable to mark as deleted", id);
        return false;
      }
    } else {
      log.info("Collection not found for identifier {} so unable to mark as deleted", id);
      return false;
    }
  }

  public ClassificationCollectionDto save(ClassificationCollectionDto dtoToBeSaved) {
    ClassificationCollection entityToBeSaved = classificationCollectionConverter.toEntity(dtoToBeSaved);
    entityToBeSaved.setLastUpdated(LocalDateTime.now());
    // TODO how to retrieve current logged in user to set on entity
    ClassificationCollection savedEntity = classificationDao.save(entityToBeSaved);
    return classificationCollectionConverter.toDto(savedEntity);
  }

  protected ClassificationCollectionDto constructNullClassificationCollectionDto() {
    return null;
  }
}
