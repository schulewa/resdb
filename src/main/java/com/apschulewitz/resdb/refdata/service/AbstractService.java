package com.apschulewitz.resdb.refdata.service;

import com.apschulewitz.resdb.common.model.EntityTypeEnum;
import com.apschulewitz.resdb.common.model.VersionableDataDto;
import com.apschulewitz.resdb.common.model.VersionableDataEntity;
import com.apschulewitz.resdb.common.model.dao.DataDao;
import com.apschulewitz.resdb.common.model.entity.VersionStatus;
import com.apschulewitz.resdb.common.model.mapper.VersionableEntityDtoMapper;
import com.apschulewitz.resdb.common.service.DataService;
import com.apschulewitz.resdb.common.utils.LoggingUtils;
import com.apschulewitz.resdb.refdata.model.entity.Region;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.repository.CrudRepository;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

@Slf4j
public abstract class AbstractService<D extends VersionableDataDto<K>,
                                      E extends VersionableDataEntity<K>,
                                      K,
                                      M extends VersionableEntityDtoMapper<E, D>,
                                      R extends DataDao<E, K> & CrudRepository<E, K>>
implements DataService<D, E, K> {

  protected D add(EntityTypeEnum entityType, M mapper, R dao, D dto) {
    LoggingUtils.logStartOfAddRequest(entityType, dto);

    if (dto.getId() != null) {
      LoggingUtils.logUnableToAddEntityWithId(entityType, dto);
      return null;
    }

    E entity = mapper.toEntity(dto);
    entity.setStatus(VersionStatus.New);
    E saved = dao.save(entity);

    LoggingUtils.logEndOfAddRequest(entityType, saved);

    return mapper.toDto(saved);
  }

  protected D deleteById(EntityTypeEnum entityType, M mapper, R dao, K id) {
    LoggingUtils.logStartOfDeleteRequest(entityType, id);

    Optional<E> found = dao.findById(id);
    if (found.isEmpty()) {
      LoggingUtils.logFoundNoEntityForId(entityType, id);
      return null;
    }

    if (!VersionStatus.isActive(found.get().getStatus())) {
      LoggingUtils.logUnableToMarkInactiveEntityAsDeleted(entityType, id);
      return null;
    }

    E toBeDeleted = found.get();
    toBeDeleted.setStatus(VersionStatus.Cancel);
    toBeDeleted.setLastUpdated(ZonedDateTime.now());
    // TODO set lastUpdatedBY to current logged in user
    E deleted = dao.save(toBeDeleted);

    LoggingUtils.logEndOfDeleteRequest(entityType, deleted);

    return mapper.toDto(deleted);
  }

  protected List<D> findAll(EntityTypeEnum entityType, M mapper, R dao, boolean onlyActive) {
    LoggingUtils.logFindingAllEntities(entityType);
    List<D> regions = new ArrayList<>();
    Iterable<E> iter;
    if (onlyActive) {
      iter = dao.findByStatusIn(VersionStatus.getLiveStatuses());
    } else {
      iter = dao.findAll();
    }
    StreamSupport.stream(iter.spliterator(), false)
      .forEach(e -> regions.add(mapper.toDto(e)));
    if (onlyActive) {
      LoggingUtils.logFoundCountActiveEntities(entityType, regions.size());
    } else {
      LoggingUtils.logFoundCountAllEntities(entityType, regions.size());
    }
    return regions;
  }

  protected D findById(EntityTypeEnum entityType, M mapper, R dao, K id) {
    LoggingUtils.logAttemptingToFindEntityForId(entityType, id);

    Optional<E> found = dao.findById(id);
    if (found.isPresent()) {
      LoggingUtils.logFoundEntityForId(entityType, id);
      return mapper.toDto(found.get());
    }

    LoggingUtils.logFoundNoEntityForId(entityType, id);

    return null;
  }

  protected D update(EntityTypeEnum entityType, M mapper, R dao, D dto) {
    LoggingUtils.logStartOfUpdateRequest(entityType, dto);

    if (dto.getId() == null) {
      LoggingUtils.logUnableToUpdateEntityWithNoId(entityType, dto);
      return null;
    }

    dto.setLastUpdated(ZonedDateTime.now());
    // TODO set lastUpdatedBy to currrent logged in user
    VersionStatus status = VersionStatus.getInstance(dto.getStatus());
    if (VersionStatus.New.equals(status)) {
      dto.setStatus(VersionStatus.Amend.name());
    }

    E entity = mapper.toEntity(dto);
    E saved = dao.save(entity);

    LoggingUtils.logEndOfUpdateRequest(entityType, dto);

    return mapper.toDto(saved);
  }
}
