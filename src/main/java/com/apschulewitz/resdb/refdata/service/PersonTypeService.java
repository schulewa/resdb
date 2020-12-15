package com.apschulewitz.resdb.refdata.service;

import com.apschulewitz.resdb.common.model.EntityTypeEnum;
import com.apschulewitz.resdb.common.model.entity.VersionStatus;
import com.apschulewitz.resdb.common.utils.LoggingUtils;
import com.apschulewitz.resdb.refdata.model.dao.PersonTypeDao;
import com.apschulewitz.resdb.refdata.model.dto.PersonTypeDto;
import com.apschulewitz.resdb.refdata.model.entity.PersonType;
import com.apschulewitz.resdb.refdata.model.mapper.PersonTypeMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

@Service
@Slf4j
public class PersonTypeService {

  private PersonTypeDao personTypeDao;

  private PersonTypeMapper personTypeMapper;

  public PersonTypeService(PersonTypeDao personTypeDao, PersonTypeMapper personTypeMapper) {
    this.personTypeDao = personTypeDao;
    this.personTypeMapper = personTypeMapper;
  }

  public PersonTypeDto add(PersonTypeDto dto) {
    LoggingUtils.logStartOfAddRequest(EntityTypeEnum.PERSON_TYPE, dto);

    PersonType entity = personTypeMapper.toEntity(dto);
    PersonType saved = personTypeDao.save(entity);

    LoggingUtils.logAddedEntity(EntityTypeEnum.PERSON_TYPE, saved);

    return personTypeMapper.toDto(saved);
  }

  public PersonTypeDto deleteById(Long id) {
    LoggingUtils.logAttemptingToMarkEntityAsDeleted(EntityTypeEnum.PERSON_TYPE, id);

    Optional<PersonType> found = personTypeDao.findById(id);
    if (found.isEmpty()) {
      LoggingUtils.logNoEntityFoundForId(EntityTypeEnum.PERSON_TYPE, id);
      return null;
    }

    if (!VersionStatus.isActive(found.get().getStatus())) {
      LoggingUtils.logUnableToMarkInactiveEntityAsDeleted(EntityTypeEnum.PERSON_TYPE, id);
      return null;
    }

    PersonType toBeDeleted = found.get();
    toBeDeleted.setStatus(VersionStatus.Cancel);
    toBeDeleted.setLastUpdated(LocalDateTime.now());
    // TODO set lastUpdatedBY to current logged in user
    PersonType deleted = personTypeDao.save(toBeDeleted);

    LoggingUtils.logEntityMarkedAsDeleted(EntityTypeEnum.PERSON_TYPE, id);

    return personTypeMapper.toDto(deleted);
  }

  public List<PersonTypeDto> findAll(boolean onlyActive) {
    LoggingUtils.logStartOfFindAllRequest(EntityTypeEnum.PERSON_TYPE);
    List<PersonTypeDto> personTypes = new ArrayList<>();
    Iterable<PersonType> iter;
    if (onlyActive) {
      iter = personTypeDao.findByStatusIn(VersionStatus.getLiveStatuses());
    } else {
      iter = personTypeDao.findAll();
    }
    StreamSupport.stream(iter.spliterator(), false)
      .forEach(e -> personTypes.add(personTypeMapper.toDto(e)));
      LoggingUtils.logFoundCountEntities(EntityTypeEnum.PERSON_TYPE, onlyActive, personTypes.size());

    return personTypes;
  }

  public PersonTypeDto findById(Long id) {
    LoggingUtils.logAttemptingToFindEntityForId(EntityTypeEnum.PERSON_TYPE, id);

    Optional<PersonType> found = personTypeDao.findById(id);
    if (found.isPresent()) {
      LoggingUtils.logFoundEntityForId(EntityTypeEnum.PERSON_TYPE, id);
      return personTypeMapper.toDto(found.get());
    }

    LoggingUtils.logFoundNoEntityForId(EntityTypeEnum.PERSON_TYPE, id);

    return null;
  }

  public PersonTypeDto update(PersonTypeDto dto) {
    LoggingUtils.logAttemptingToUpdateEntity(EntityTypeEnum.PERSON_TYPE, dto);

    if (dto.getId() == null) {
      LoggingUtils.logUnableToUpdateEntityWithNoId(EntityTypeEnum.PERSON_TYPE, dto);
      return null;
    }

    dto.setLastUpdated(LocalDateTime.now());
    // TODO set lastUpdatedBy to currrent logged in user
    VersionStatus status = VersionStatus.getInstance(dto.getStatus());
    if (VersionStatus.New.equals(status)) {
      dto.setStatus(VersionStatus.Amend.name());
    }

    PersonType entity = personTypeMapper.toEntity(dto);
    PersonType saved = personTypeDao.save(entity);

    PersonTypeDto updated = personTypeMapper.toDto(saved);

    LoggingUtils.logUpdatedEntity(EntityTypeEnum.PERSON_TYPE, updated);

    return updated;
  }
}
