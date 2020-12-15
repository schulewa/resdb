package com.apschulewitz.resdb.refdata.service;

import com.apschulewitz.resdb.common.model.entity.VersionStatus;
import com.apschulewitz.resdb.refdata.model.dao.ArtefactGroupDao;
import com.apschulewitz.resdb.refdata.model.dao.ReferenceTypeDao;
import com.apschulewitz.resdb.refdata.model.dto.ArtefactGroupDto;
import com.apschulewitz.resdb.refdata.model.dto.ReferenceTypeDto;
import com.apschulewitz.resdb.refdata.model.entity.ArtefactGroup;
import com.apschulewitz.resdb.refdata.model.entity.ReferenceType;
import com.apschulewitz.resdb.refdata.model.mapper.ArtefactGroupMapper;
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
public class ArtefactGroupService {

  private ArtefactGroupDao artefactGroupDao;

  private ArtefactGroupMapper artefactGroupMapper;

  public ArtefactGroupService(ArtefactGroupDao artefactGroupDao, ArtefactGroupMapper artefactGroupMapper) {
    this.artefactGroupDao = artefactGroupDao;
    this.artefactGroupMapper = artefactGroupMapper;
  }

  public ArtefactGroupDto add(ArtefactGroupDto dto) {
    log.info("Adding new artefact group {}", dto);

    if (dto.getId() != null) {
      log.error(String.format("Artefact group ID [{}] is set", dto.getId()));
      return null;
    }

    ArtefactGroup entity = artefactGroupMapper.toEntity(dto);
    ArtefactGroup saved = artefactGroupDao.save(entity);

    log.info("Saved artefact group [{}]", saved);

    return artefactGroupMapper.toDto(saved);
  }

  public ArtefactGroupDto deleteById(Long id) {
    log.info("Marking artefact group with id {} as deleted", id);

    Optional<ArtefactGroup> found = artefactGroupDao.findById(id);
    if (found.isEmpty()) {
      log.error("No artefact group entity found for id {}", id);
      return null;
    }

    if (!VersionStatus.isActive(found.get().getStatus())) {
      log.error("Unable to mark an inactive artefact group as deleted");
      return null;
    }

    ArtefactGroup toBeDeleted = found.get();
    toBeDeleted.setStatus(VersionStatus.Cancel);
    toBeDeleted.setLastUpdated(LocalDateTime.now());
    // TODO set lastUpdatedBY to current logged in user
    ArtefactGroup deleted = artefactGroupDao.save(toBeDeleted);

    log.info("Artefact group with id {} marked as deleted", id);

    return artefactGroupMapper.toDto(deleted);
  }

  public List<ArtefactGroupDto> findAll(boolean onlyActive) {
    log.info("Finding all artefact group");
    List<ArtefactGroupDto> artefactGroups = new ArrayList<>();
    Iterable<ArtefactGroup> iter;
    if (onlyActive) {
      iter = artefactGroupDao.findByStatusIn(VersionStatus.getLiveStatuses());
    } else {
      iter = artefactGroupDao.findAll();
    }
    StreamSupport.stream(iter.spliterator(), false)
      .forEach(e -> artefactGroups.add(artefactGroupMapper.toDto(e)));
    log.info("Found {} artefact groups", artefactGroups.size());
    return artefactGroups;
  }

  public ArtefactGroupDto findById(Long id) {
    log.info("Finding artefact group for id {}", id);

    Optional<ArtefactGroup> found = artefactGroupDao.findById(id);
    if (found.isPresent()) {
      log.debug("Found artefact group [{}]", found.get());
      return artefactGroupMapper.toDto(found.get());
    }

    log.error("No artefact group found for id {}", id);

    return null;
  }

  public ArtefactGroupDto update(ArtefactGroupDto dto) {
    log.info("Updating artefact group [{}]", dto);

    if (dto.getId() == null) {
      log.error("Cannot update a non-existent artefact group");
      return null;
    }

    dto.setLastUpdated(LocalDateTime.now());
    // TODO set lastUpdatedBy to currrent logged in user
    VersionStatus status = VersionStatus.getInstance(dto.getStatus());
    if (VersionStatus.New.equals(status)) {
      dto.setStatus(VersionStatus.Amend.name());
    }

    ArtefactGroup entity = artefactGroupMapper.toEntity(dto);
    ArtefactGroup saved = artefactGroupDao.save(entity);

    log.info("Saved artefact group [{}]", saved);

    return artefactGroupMapper.toDto(saved);
  }
}
