package com.apschulewitz.resdb.refdata.service;

import com.apschulewitz.resdb.common.model.entity.VersionStatus;
import com.apschulewitz.resdb.refdata.model.dao.ReferenceTypeDao;
import com.apschulewitz.resdb.refdata.model.dto.ReferenceTypeDto;
import com.apschulewitz.resdb.refdata.model.entity.ReferenceType;
import com.apschulewitz.resdb.refdata.model.mapper.ReferenceTypeMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

@Service
@Slf4j
public class ReferenceTypeService {

  private ReferenceTypeDao referenceTypeDao;

  private ReferenceTypeMapper referenceTypeMapper;

  public ReferenceTypeService(ReferenceTypeDao referenceTypeDao, ReferenceTypeMapper referenceTypeMapper) {
    this.referenceTypeDao = referenceTypeDao;
    this.referenceTypeMapper = referenceTypeMapper;
  }

  public ReferenceTypeDto add(ReferenceTypeDto dto) {
    log.info("Adding new reference type {}", dto);

    if (dto.getId() != null) {
      log.error(String.format("Reference type ID [{}] is set", dto.getId()));
      return null;
    }

    ReferenceType entity = referenceTypeMapper.toEntity(dto);
    ReferenceType saved = referenceTypeDao.save(entity);

    log.info("Saved reference type [{}]", saved);

    return referenceTypeMapper.toDto(saved);
  }

  public ReferenceTypeDto deleteById(Long id) {
    log.info("Marking reference type with id {} as deleted", id);

    Optional<ReferenceType> found = referenceTypeDao.findById(id);
    if (found.isEmpty()) {
      log.error("No reference type entity found for id {}", id);
      return null;
    }

    if (!VersionStatus.isActive(found.get().getStatus())) {
      log.error("Unable to mark an inactive reference type as deleted");
      return null;
    }

    ReferenceType toBeDeleted = found.get();
    toBeDeleted.setStatus(VersionStatus.Cancel);
    toBeDeleted.setLastUpdated(LocalDateTime.now());
    // TODO set lastUpdatedBY to current logged in user
    ReferenceType deleted = referenceTypeDao.save(toBeDeleted);

    log.info("Reference type with id {} marked as deleted", id);

    return referenceTypeMapper.toDto(deleted);
  }

  public List<ReferenceTypeDto> findAll(boolean onlyActive) {
    log.info("Finding all reference type");
    List<ReferenceTypeDto> referenceTypes = new ArrayList<>();
    Iterable<ReferenceType> iter;
    if (onlyActive) {
      iter = referenceTypeDao.findByStatusIn(VersionStatus.getLiveStatuses());
    } else {
      iter = referenceTypeDao.findAll();
    }
    StreamSupport.stream(iter.spliterator(), false)
      .forEach(e -> referenceTypes.add(referenceTypeMapper.toDto(e)));
    log.info("Found {} reference types", referenceTypes.size());
    return referenceTypes;
  }

  public ReferenceTypeDto findById(Long id) {
    log.info("Finding reference type for id {}", id);

    Optional<ReferenceType> found = referenceTypeDao.findById(id);
    if (found.isPresent()) {
      log.debug("Found reference type [{}]", found.get());
      return referenceTypeMapper.toDto(found.get());
    }

    log.error("No reference type found for id {}", id);

    return null;
  }

  public ReferenceTypeDto update(ReferenceTypeDto dto) {
    log.info("Updating reference type [{}]", dto);

    if (dto.getId() == null) {
      log.error("Cannot update a non-existent reference type");
      return null;
    }

    dto.setLastUpdated(LocalDateTime.now());
    // TODO set lastUpdatedBy to currrent logged in user
    VersionStatus status = VersionStatus.getInstance(dto.getStatus());
    if (VersionStatus.New.equals(status)) {
      dto.setStatus(VersionStatus.Amend.name());
    }

    ReferenceType entity = referenceTypeMapper.toEntity(dto);
    ReferenceType saved = referenceTypeDao.save(entity);

    log.info("Saved reference type [{}]", saved);

    return referenceTypeMapper.toDto(saved);
  }
}
