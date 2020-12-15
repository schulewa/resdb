package com.apschulewitz.resdb.refdata.service;

import com.apschulewitz.resdb.common.model.entity.VersionStatus;
import com.apschulewitz.resdb.refdata.model.dao.TechnologyTypeGroupDao;
import com.apschulewitz.resdb.refdata.model.dto.TechnologyTypeGroupDto;
import com.apschulewitz.resdb.refdata.model.entity.TechnologyTypeGroup;
import com.apschulewitz.resdb.refdata.model.mapper.TechnologyTypeGroupMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

@Service
@Slf4j
public class TechnologyTypeGroupService {

  private TechnologyTypeGroupDao technologyTypeGroupDao;

  private TechnologyTypeGroupMapper technologyTypeGroupMapper;

  public TechnologyTypeGroupService(TechnologyTypeGroupDao technologyTypeGroupDao, TechnologyTypeGroupMapper technologyTypeGroupMapper) {
    this.technologyTypeGroupDao = technologyTypeGroupDao;
    this.technologyTypeGroupMapper = technologyTypeGroupMapper;
  }

  public TechnologyTypeGroupDto deleteById(Long id) {
    log.info("Marking technology type group with id {} as deleted", id);

    Optional<TechnologyTypeGroup> found = technologyTypeGroupDao.findById(id);
    if (found.isEmpty()) {
      log.error("No technology type group entity found for id {}", id);
      return null;
    }

    if (!VersionStatus.isActive(found.get().getStatus())) {
      log.error("Unable to mark an inactive technology type group as deleted");
      return null;
    }

    TechnologyTypeGroup toBeDeleted = found.get();
    toBeDeleted.setStatus(VersionStatus.Cancel);
    toBeDeleted.setLastUpdated(LocalDateTime.now());
    // TODO set lastUpdatedBY to current logged in user
    TechnologyTypeGroup deleted = technologyTypeGroupDao.save(toBeDeleted);

    log.info("Technology type group with id {} marked as deleted", id);

    return technologyTypeGroupMapper.toDto(deleted);
  }

  public List<TechnologyTypeGroupDto> findAll(boolean onlyActive) {
    log.info("Finding all technology type groups");
    List<TechnologyTypeGroupDto> technologyTypeGroups = new ArrayList<>();
    Iterable<TechnologyTypeGroup> iter;
    if (onlyActive) {
      iter = technologyTypeGroupDao.findByStatusIn(VersionStatus.getLiveStatuses());
    } else {
      iter = technologyTypeGroupDao.findAll();
    }
    StreamSupport.stream(iter.spliterator(), false)
      .forEach(e -> technologyTypeGroups.add(technologyTypeGroupMapper.toDto(e)));
    log.info("Found {} technology type groups", technologyTypeGroups.size());
    return technologyTypeGroups;
  }

  public TechnologyTypeGroupDto findById(Long id) {
    log.info("Finding technology type group for id {}", id);

    Optional<TechnologyTypeGroup> found = technologyTypeGroupDao.findById(id);
    if (found.isPresent()) {
      log.debug("Found technology type group [{}]", found.get());
      return technologyTypeGroupMapper.toDto(found.get());
    }

    log.error("No technology type group found for id {}", id);

    return null;
  }

  public TechnologyTypeGroupDto add(TechnologyTypeGroupDto dto) {
    log.info("Adding new technology type group {}", dto);

    if (dto.getId() != null) {
      log.error(String.format("Technology type group ID [{}] is set", dto.getId()));
      return null;
    }

    TechnologyTypeGroup entity = technologyTypeGroupMapper.toEntity(dto);
    TechnologyTypeGroup saved = technologyTypeGroupDao.save(entity);

    log.info("Saved technology type group [{}]", saved);

    return technologyTypeGroupMapper.toDto(saved);
  }

  public TechnologyTypeGroupDto update(TechnologyTypeGroupDto dto) {
    log.info("Updating technology type group [{}]", dto);

    if (dto.getId() == null) {
      log.error("Cannot update a non-existent technology type group");
      return null;
    }

       dto.setLastUpdated(LocalDateTime.now());
       // TODO set lastUpdatedBy to currrent logged in user
       VersionStatus status = VersionStatus.getInstance(dto.getStatus());
       if (VersionStatus.New.equals(status)) {
         dto.setStatus(VersionStatus.Amend.name());
       }

     TechnologyTypeGroup entity = technologyTypeGroupMapper.toEntity(dto);
     TechnologyTypeGroup saved = technologyTypeGroupDao.save(entity);

     log.info("Saved technology type group [{}]", saved);

     return technologyTypeGroupMapper.toDto(saved);
  }
}
