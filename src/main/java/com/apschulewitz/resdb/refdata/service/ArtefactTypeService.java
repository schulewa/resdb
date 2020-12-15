package com.apschulewitz.resdb.refdata.service;

import com.apschulewitz.resdb.common.model.entity.VersionStatus;
import com.apschulewitz.resdb.refdata.model.dao.ArtefactTypeDao;
import com.apschulewitz.resdb.refdata.model.dto.ArtefactTypeDto;
import com.apschulewitz.resdb.refdata.model.entity.ArtefactType;
import com.apschulewitz.resdb.refdata.model.mapper.ArtefactTypeMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

@Service
@Slf4j
public class ArtefactTypeService {

  private ArtefactTypeDao artefactTypeDao;

  private ArtefactTypeMapper artefactTypeMapper;

  public ArtefactTypeService(ArtefactTypeDao artefactTypeDao, ArtefactTypeMapper artefactTypeMapper) {
    this.artefactTypeDao = artefactTypeDao;
    this.artefactTypeMapper = artefactTypeMapper;
  }

  public ArtefactTypeDto add(ArtefactTypeDto dto) {
    log.info("Adding new artefact type {}", dto);

    if (dto.getId() != null) {
      log.error(String.format("Artefact type ID [{}] is set", dto.getId()));
      return null;
    }

    ArtefactType entity = artefactTypeMapper.toEntity(dto);
    ArtefactType saved = artefactTypeDao.save(entity);

    log.info("Saved artefact type [{}]", saved);

    return artefactTypeMapper.toDto(saved);
  }

  public ArtefactTypeDto deleteById(Long id) {
    log.info("Marking artefact type with id {} as deleted", id);

    Optional<ArtefactType> found = artefactTypeDao.findById(id);
    if (found.isEmpty()) {
      log.error("No artefact type entity found for id {}", id);
      return null;
    }

    if (!VersionStatus.isActive(found.get().getStatus())) {
      log.error("Unable to mark an inactive artefact type as deleted");
      return null;
    }

    ArtefactType toBeDeleted = found.get();
    toBeDeleted.setStatus(VersionStatus.Cancel);
    toBeDeleted.setLastUpdated(LocalDateTime.now());
    // TODO set lastUpdatedBY to current logged in user
    ArtefactType deleted = artefactTypeDao.save(toBeDeleted);

    log.info("Artefact type with id {} marked as deleted", id);

    return artefactTypeMapper.toDto(deleted);
  }

  public List<ArtefactTypeDto> findAll(boolean onlyActive) {
    log.info("Finding all artefact types");
    List<ArtefactTypeDto> artefactTypes = new ArrayList<>();
    Iterable<ArtefactType> iter;
    if (onlyActive) {
      iter = artefactTypeDao.findByStatusIn(VersionStatus.getLiveStatuses());
    } else {
      iter = artefactTypeDao.findAll();
    }
    StreamSupport.stream(iter.spliterator(), false)
      .forEach(e -> artefactTypes.add(artefactTypeMapper.toDto(e)));
    log.info("Found {} artefact types", artefactTypes.size());
    return artefactTypes;
  }

  public ArtefactTypeDto findById(Long id) {
    log.info("Finding artefact type for id {}", id);

    Optional<ArtefactType> found = artefactTypeDao.findById(id);
    if (found.isPresent()) {
      log.debug("Found artefact type [{}]", found.get());
      return artefactTypeMapper.toDto(found.get());
    }

    log.error("No artefact type found for id {}", id);

    return null;
  }

  public ArtefactTypeDto update(ArtefactTypeDto dto) {
    log.info("Updating artefact type [{}]", dto);

    if (dto.getId() == null) {
      log.error("Cannot update a non-existent artefact type");
      return null;
    }

    dto.setLastUpdated(LocalDateTime.now());
    // TODO set lastUpdatedBy to currrent logged in user
    VersionStatus status = VersionStatus.getInstance(dto.getStatus());
    if (VersionStatus.New.equals(status)) {
      dto.setStatus(VersionStatus.Amend.name());
    }

    ArtefactType entity = artefactTypeMapper.toEntity(dto);
    ArtefactType saved = artefactTypeDao.save(entity);

    log.info("Saved artefact type [{}]", saved);

    return artefactTypeMapper.toDto(saved);
  }
}
