package com.apschulewitz.resdb.common.service;

import com.apschulewitz.resdb.common.model.EntityTypeEnum;
import com.apschulewitz.resdb.common.model.VersionableDataDto;
import com.apschulewitz.resdb.common.model.VersionableDataEntity;
import com.apschulewitz.resdb.common.model.dao.DataDao;
import com.apschulewitz.resdb.common.model.entity.VersionStatus;
import com.apschulewitz.resdb.common.model.mapper.VersionableEntityDtoMapper;
import com.apschulewitz.resdb.common.utils.LoggingUtils;
import com.apschulewitz.resdb.common.utils.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.repository.CrudRepository;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.ZoneOffset;
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
    entity.setCreatedBy(getCurrentAuthenticatedUser());
    //
    // manual setting of versionNumber due to issue with Spring Data JPA
    // creating entirely new entry with new ID
    //
    entity.setVersionNumber(1L);

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
    toBeDeleted.setLastUpdated(ZonedDateTime.now(ZoneOffset.UTC));
    toBeDeleted.setUpdatedBy(getCurrentAuthenticatedUser());
    //
    // manual setting of versionNumber due to issue with Spring Data JPA
    // creating entirely new entry with new ID
    //
    toBeDeleted.setVersionNumber(toBeDeleted.getVersionNumber() + 1);

    E deleted = dao.save(toBeDeleted);

    LoggingUtils.logEndOfDeleteRequest(entityType, deleted);

    return mapper.toDto(deleted);
  }

  protected List<D> findAll(EntityTypeEnum entityType, M mapper, R dao, boolean onlyActive) {
    LoggingUtils.logFindingAllEntities(entityType);
    List<D> dtoList = new ArrayList<>();
    Iterable<E> iter;
    if (onlyActive) {
      iter = dao.findByStatusIn(VersionStatus.getLiveStatuses());
    } else {
      iter = dao.findAll();
    }
    StreamSupport.stream(iter.spliterator(), false)
      .forEach(e -> dtoList.add(mapper.toDto(e)));
    if (onlyActive) {
      LoggingUtils.logFoundCountActiveEntities(entityType, dtoList.size());
    } else {
      LoggingUtils.logFoundCountAllEntities(entityType, dtoList.size());
    }
    return dtoList;
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

    dto.setLastUpdated(ZonedDateTime.now(ZoneOffset.UTC));
    dto.setUpdatedBy(getCurrentAuthenticatedUser());
    VersionStatus status = VersionStatus.getInstance(dto.getStatus());
    if (VersionStatus.New.equals(status)) {
      dto.setStatus(VersionStatus.Amend.name());
    }
    //
    // manual setting of versionNumber due to issue with Spring Data JPA
    // creating entirely new entry with new ID
    //
    dto.setVersionNumber(dto.getVersionNumber() + 1);

    E entity = mapper.toEntity(dto);
    E saved = dao.save(entity);

    LoggingUtils.logEndOfUpdateRequest(entityType, dto);

    return mapper.toDto(saved);
  }

  private String getCurrentAuthenticatedUser() {
    String username = null;
    Object principal = null;
    if (SecurityContextHolder.getContext() != null && SecurityContextHolder.getContext().getAuthentication() != null)  {
      principal = SecurityContextHolder.getContext().getAuthentication();
    }
    if (principal instanceof UserDetails) {
      username = ((UserDetails) principal).getUsername();
    } else if (principal instanceof UsernamePasswordAuthenticationToken) {
      Object obj = ((UsernamePasswordAuthenticationToken)principal).getPrincipal();
      if (obj instanceof User) {
        username = ((User)obj).getUsername();
      } else if (obj instanceof UserDetails) {
        username = ((UserDetails) principal).getUsername();
      } else {
        log.warn("Principal is an unrecognized type of object [{}], unable to extract current username", obj);
      }
    } else if (principal != null) {
      username = principal.toString();
    }
    return username;
  }
}
