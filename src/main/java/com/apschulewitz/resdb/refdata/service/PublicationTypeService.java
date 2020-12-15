package com.apschulewitz.resdb.refdata.service;

import com.apschulewitz.resdb.common.model.EntityTypeEnum;
import com.apschulewitz.resdb.common.model.entity.VersionStatus;
import com.apschulewitz.resdb.common.utils.LoggingUtils;
import com.apschulewitz.resdb.refdata.model.dao.PublicationTypeDao;
import com.apschulewitz.resdb.refdata.model.dto.PublicationTypeDto;
import com.apschulewitz.resdb.refdata.model.entity.PublicationType;
import com.apschulewitz.resdb.refdata.model.mapper.PublicationTypeMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

@Service
@Slf4j
public class PublicationTypeService {

  private PublicationTypeDao publicationTypeDao;

  private PublicationTypeMapper publicationTypeMapper;

  public PublicationTypeService(PublicationTypeDao publicationTypeDao, PublicationTypeMapper publicationTypeMapper) {
    this.publicationTypeDao = publicationTypeDao;
    this.publicationTypeMapper = publicationTypeMapper;
  }

  public PublicationTypeDto add(PublicationTypeDto dto) {
    LoggingUtils.logStartOfAddRequest(EntityTypeEnum.PUBLICATION_TYPE, dto);

    PublicationType entity = publicationTypeMapper.toEntity(dto);
    PublicationType saved = publicationTypeDao.save(entity);

    LoggingUtils.logAddedEntity(EntityTypeEnum.PUBLICATION_TYPE, saved);

    return publicationTypeMapper.toDto(saved);
  }

  public PublicationTypeDto deleteById(Long id) {
    LoggingUtils.logAttemptingToMarkEntityAsDeleted(EntityTypeEnum.PUBLICATION_TYPE, id);

    Optional<PublicationType> found = publicationTypeDao.findById(id);
    if (found.isEmpty()) {
      LoggingUtils.logNoEntityFoundForId(EntityTypeEnum.PUBLICATION_TYPE, id);
      return null;
    }

    if (!VersionStatus.isActive(found.get().getStatus())) {
      LoggingUtils.logUnableToMarkInactiveEntityAsDeleted(EntityTypeEnum.PUBLICATION_TYPE, id);
      return null;
    }

    PublicationType toBeDeleted = found.get();
    toBeDeleted.setStatus(VersionStatus.Cancel);
    toBeDeleted.setLastUpdated(LocalDateTime.now());
    // TODO set lastUpdatedBY to current logged in user
    PublicationType deleted = publicationTypeDao.save(toBeDeleted);

    LoggingUtils.logEntityMarkedAsDeleted(EntityTypeEnum.PUBLICATION_TYPE, id);

    return publicationTypeMapper.toDto(deleted);
  }

  public List<PublicationTypeDto> findAll(boolean onlyActive) {
    LoggingUtils.logStartOfFindAllRequest(EntityTypeEnum.PUBLICATION_TYPE);
    List<PublicationTypeDto> publicationTypes = new ArrayList<>();
    Iterable<PublicationType> iter;
    if (onlyActive) {
      iter = publicationTypeDao.findByStatusIn(VersionStatus.getLiveStatuses());
    } else {
      iter = publicationTypeDao.findAll();
    }
    StreamSupport.stream(iter.spliterator(), false)
      .forEach(e -> publicationTypes.add(publicationTypeMapper.toDto(e)));
      LoggingUtils.logFoundCountEntities(EntityTypeEnum.PUBLICATION_TYPE, onlyActive, publicationTypes.size());

    return publicationTypes;
  }

  public PublicationTypeDto findById(Long id) {
    LoggingUtils.logAttemptingToFindEntityForId(EntityTypeEnum.PUBLICATION_TYPE, id);

    Optional<PublicationType> found = publicationTypeDao.findById(id);
    if (found.isPresent()) {
      LoggingUtils.logFoundEntityForId(EntityTypeEnum.PUBLICATION_TYPE, id);
      return publicationTypeMapper.toDto(found.get());
    }

    LoggingUtils.logFoundNoEntityForId(EntityTypeEnum.PUBLICATION_TYPE, id);

    return null;
  }

  public PublicationTypeDto update(PublicationTypeDto dto) {
    LoggingUtils.logAttemptingToUpdateEntity(EntityTypeEnum.PUBLICATION_TYPE, dto);

    if (dto.getId() == null) {
      LoggingUtils.logUnableToUpdateEntityWithNoId(EntityTypeEnum.PUBLICATION_TYPE, dto);
      return null;
    }

    dto.setLastUpdated(LocalDateTime.now());
    // TODO set lastUpdatedBy to currrent logged in user
    VersionStatus status = VersionStatus.getInstance(dto.getStatus());
    if (VersionStatus.New.equals(status)) {
      dto.setStatus(VersionStatus.Amend.name());
    }

    PublicationType entity = publicationTypeMapper.toEntity(dto);
    PublicationType saved = publicationTypeDao.save(entity);

    PublicationTypeDto updated = publicationTypeMapper.toDto(saved);

    LoggingUtils.logUpdatedEntity(EntityTypeEnum.PUBLICATION_TYPE, updated);

    return updated;
  }
}
