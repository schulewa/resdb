package com.apschulewitz.resdb.refdata.service;

import com.apschulewitz.resdb.common.model.entity.VersionStatus;
import com.apschulewitz.resdb.refdata.model.dao.TaleTypeDao;
import com.apschulewitz.resdb.refdata.model.dto.TaleTypeDto;
import com.apschulewitz.resdb.refdata.model.entity.ReferenceType;
import com.apschulewitz.resdb.refdata.model.entity.TaleType;
import com.apschulewitz.resdb.refdata.model.mapper.TaleTypeMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

@Service
@Slf4j
public class TaleTypeService {

  private TaleTypeDao taleTypeDao;

  private TaleTypeMapper taleTypeMapper;

  public TaleTypeService(TaleTypeDao taleTypeDao, TaleTypeMapper taleTypeMapper) {
    this.taleTypeDao = taleTypeDao;
    this.taleTypeMapper = taleTypeMapper;
  }

  public TaleTypeDto add(TaleTypeDto dto) {
    log.info("Adding new tale type {}", dto);

    if (dto.getId() != null) {
      log.error(String.format("Tale type ID [{}] is set", dto.getId()));
      return null;
    }

    TaleType entity = taleTypeMapper.toEntity(dto);
    TaleType saved = taleTypeDao.save(entity);

    log.info("Saved tale type [{}]", saved);

    return taleTypeMapper.toDto(saved);
  }

  public TaleTypeDto deleteById(Long id) {
    log.info("Marking tale type with id {} as deleted", id);

    Optional<TaleType> found = taleTypeDao.findById(id);
    if (found.isEmpty()) {
      log.error("No tale type entity found for id {}", id);
      return null;
    }

    if (!VersionStatus.isActive(found.get().getStatus())) {
      log.error("Unable to mark an inactive tale type as deleted");
      return null;
    }

    TaleType toBeDeleted = found.get();
    toBeDeleted.setStatus(VersionStatus.Cancel);
    toBeDeleted.setLastUpdated(LocalDateTime.now());
    // TODO set lastUpdatedBY to current logged in user
    TaleType deleted = taleTypeDao.save(toBeDeleted);

    log.info("Tale type with id {} marked as deleted", id);

    return taleTypeMapper.toDto(deleted);
  }

  public List<TaleTypeDto> findAll(boolean onlyActive) {
    log.info("Finding all tale types");
    List<TaleTypeDto> taleTypes = new ArrayList<>();
    Iterable<TaleType> iter;
    if (onlyActive) {
      iter = taleTypeDao.findByStatusIn(VersionStatus.getLiveStatuses());
    } else {
      iter = taleTypeDao.findAll();
    }
    StreamSupport.stream(iter.spliterator(), false)
      .forEach(e -> taleTypes.add(taleTypeMapper.toDto(e)));
    log.info("Found {} tale types", taleTypes.size());
    return taleTypes;
  }

  public TaleTypeDto findById(Long id) {
    log.info("Finding tale type for id {}", id);

    Optional<TaleType> found = taleTypeDao.findById(id);
    if (found.isPresent()) {
      log.debug("Found tale type [{}]", found.get());
      return taleTypeMapper.toDto(found.get());
    }

    log.error("No tale type found for id {}", id);

    return null;
  }

  public TaleTypeDto update(TaleTypeDto dto) {
    log.info("Updating tale type [{}]", dto);

    if (dto.getId() == null) {
      log.error("Cannot update a non-existent tale type");
      return null;
    }

    dto.setLastUpdated(LocalDateTime.now());
    // TODO set lastUpdatedBy to currrent logged in user
    VersionStatus status = VersionStatus.getInstance(dto.getStatus());
    if (VersionStatus.New.equals(status)) {
      dto.setStatus(VersionStatus.Amend.name());
    }

    TaleType entity = taleTypeMapper.toEntity(dto);
    TaleType saved = taleTypeDao.save(entity);

    log.info("Saved tale type [{}]", saved);

    return taleTypeMapper.toDto(saved);
  }
}
