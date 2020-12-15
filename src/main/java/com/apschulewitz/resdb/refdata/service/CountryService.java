package com.apschulewitz.resdb.refdata.service;

import com.apschulewitz.resdb.common.model.EntityTypeEnum;
import com.apschulewitz.resdb.common.model.entity.VersionStatus;
import com.apschulewitz.resdb.common.utils.LoggingUtils;
import com.apschulewitz.resdb.refdata.model.dao.CountryDao;
import com.apschulewitz.resdb.refdata.model.dto.CountryDto;
import com.apschulewitz.resdb.refdata.model.entity.Country;
import com.apschulewitz.resdb.refdata.model.mapper.CountryMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

@Service
@Slf4j
public class CountryService {

  private CountryDao countryDao;

  private CountryMapper countryMapper;

  public CountryService(CountryDao countryDao, CountryMapper countryMapper) {
    this.countryDao = countryDao;
    this.countryMapper = countryMapper;
  }

  public CountryDto add(CountryDto dto) {
    log.info("Adding new country {}", dto);

    if (dto.getId() != null) {
      log.error(String.format("Country ID [{}] is not set", dto.getId()));
      return null;
    }

    Country entity = countryMapper.toEntity(dto);
    Country saved = countryDao.save(entity);

    log.info("Saved country [{}]", saved);

    return countryMapper.toDto(saved);
  }

  public CountryDto deleteById(Long id) {
    LoggingUtils.logAttemptingToMarkEntityAsDeleted(EntityTypeEnum.COUNTRY, id);

    Optional<Country> found = countryDao.findById(id);
    if (found.isEmpty()) {
      LoggingUtils.logNoEntityFoundForId(EntityTypeEnum.COUNTRY, id);
      return null;
    }

    if (!VersionStatus.isActive(found.get().getStatus())) {
      LoggingUtils.logUnableToMarkInactiveEntityAsDeleted(EntityTypeEnum.COUNTRY, id);
      return null;
    }

    Country toBeDeleted = found.get();
    toBeDeleted.setStatus(VersionStatus.Cancel);
    toBeDeleted.setLastUpdated(LocalDateTime.now());
    // TODO set lastUpdatedBY to current logged in user
    Country deleted = countryDao.save(toBeDeleted);

    LoggingUtils.logEntityMarkedAsDeleted(EntityTypeEnum.COUNTRY, id);

    return countryMapper.toDto(deleted);
  }

  public List<CountryDto> findAll(boolean onlyActive) {
    LoggingUtils.logStartOfFindAllRequest(EntityTypeEnum.COUNTRY);
    List<CountryDto> countries = new ArrayList<>();
    Iterable<Country> iter;
    if (onlyActive) {
      iter = countryDao.findByStatusIn(VersionStatus.getLiveStatuses());
    } else {
      iter = countryDao.findAll();
    }
    StreamSupport.stream(iter.spliterator(), false)
      .forEach(e -> countries.add(countryMapper.toDto(e)));
      LoggingUtils.logFoundCountEntities(EntityTypeEnum.COUNTRY, onlyActive, countries.size());

    return countries;
  }

  public CountryDto findById(Long id) {
    LoggingUtils.logAttemptingToFindEntityForId(EntityTypeEnum.COUNTRY, id);

    Optional<Country> found = countryDao.findById(id);
    if (found.isPresent()) {
      LoggingUtils.logFoundEntityForId(EntityTypeEnum.COUNTRY, id);
      return countryMapper.toDto(found.get());
    }

    LoggingUtils.logFoundNoEntityForId(EntityTypeEnum.COUNTRY, id);

    return null;
  }

  public CountryDto update(CountryDto dto) {
    LoggingUtils.logAttemptingToUpdateEntity(EntityTypeEnum.COUNTRY, dto);

    if (dto.getId() == null) {
      LoggingUtils.logUnableToUpdateEntityWithNoId(EntityTypeEnum.COUNTRY, dto);
      return null;
    }

    dto.setLastUpdated(LocalDateTime.now());
    // TODO set lastUpdatedBy to currrent logged in user
    VersionStatus status = VersionStatus.getInstance(dto.getStatus());
    if (VersionStatus.New.equals(status)) {
      dto.setStatus(VersionStatus.Amend.name());
    }

    Country entity = countryMapper.toEntity(dto);
    Country saved = countryDao.save(entity);

    CountryDto updated = countryMapper.toDto(saved);

    LoggingUtils.logUpdatedEntity(EntityTypeEnum.COUNTRY, updated);

    return updated;
  }
}
