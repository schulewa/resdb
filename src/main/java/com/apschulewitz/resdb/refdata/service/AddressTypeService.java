package com.apschulewitz.resdb.refdata.service;

import com.apschulewitz.resdb.common.model.EntityTypeEnum;
import com.apschulewitz.resdb.common.model.entity.VersionStatus;
import com.apschulewitz.resdb.common.service.DataService;
import com.apschulewitz.resdb.refdata.model.dao.AddressTypeDao;
import com.apschulewitz.resdb.refdata.model.dao.LanguageGroupDao;
import com.apschulewitz.resdb.refdata.model.dto.AddressTypeDto;
import com.apschulewitz.resdb.refdata.model.dto.LanguageGroupDto;
import com.apschulewitz.resdb.refdata.model.entity.AddressType;
import com.apschulewitz.resdb.refdata.model.entity.LanguageGroup;
import com.apschulewitz.resdb.refdata.model.mapper.AddressTypeMapper;
import com.apschulewitz.resdb.refdata.model.mapper.LanguageGroupMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

@Service
@Slf4j
public class AddressTypeService
  extends AbstractService<AddressTypeDto, AddressType, Long, AddressTypeMapper, AddressTypeDao>
  implements DataService<AddressTypeDto, AddressType, Long> {

  private AddressTypeDao addressTypeDao;

  private AddressTypeMapper addressTypeMapper;

  public AddressTypeService(AddressTypeDao addressTypeDao, AddressTypeMapper addressTypeMapper) {
    this.addressTypeDao = addressTypeDao;
    this.addressTypeMapper = addressTypeMapper;
  }

  public AddressTypeDto add(AddressTypeDto dto) {
//    log.info("Adding new address type {}", dto);
//
//    if (dto.getId() != null) {
//      log.error(String.format("Address type ID [{}] is set", dto.getId()));
//      return null;
//    }
//
//    AddressType entity = addressTypeMapper.toEntity(dto);
//    AddressType saved = addressTypeDao.save(entity);
//
//    log.info("Saved address type [{}]", saved);
//
//    return addressTypeMapper.toDto(saved);
    return add(EntityTypeEnum.ADDRESS_TYPE, addressTypeMapper, addressTypeDao, dto);
  }

  public AddressTypeDto deleteById(Long id) {
//    log.info("Marking address type with id {} as deleted", id);
//
//    Optional<AddressType> found = addressTypeDao.findById(id);
//    if (found.isEmpty()) {
//      log.error("No address type entity found for id {}", id);
//      return null;
//    }
//
//    if (!VersionStatus.isActive(found.get().getStatus())) {
//      log.error("Unable to mark an inactive address type as deleted");
//      return null;
//    }
//
//    AddressType toBeDeleted = found.get();
//    toBeDeleted.setStatus(VersionStatus.Cancel);
//    toBeDeleted.setLastUpdated(ZonedDateTime.now());
//    // TODO set lastUpdatedBY to current logged in user
//    AddressType deleted = addressTypeDao.save(toBeDeleted);
//
//    log.info("Address type with id {} marked as deleted", id);
//
//    return addressTypeMapper.toDto(deleted);
    return deleteById(EntityTypeEnum.ADDRESS_TYPE, addressTypeMapper, addressTypeDao, id);
  }

  public List<AddressTypeDto> findAll(boolean onlyActive) {
//    log.info("Finding all address types");
//    List<AddressTypeDto> addressTypes = new ArrayList<>();
//    Iterable<AddressType> iter;
//    if (onlyActive) {
//      iter = addressTypeDao.findByStatusIn(VersionStatus.getLiveStatuses());
//    } else {
//      iter = addressTypeDao.findAll();
//    }
//    StreamSupport.stream(iter.spliterator(), false)
//      .forEach(e -> addressTypes.add(addressTypeMapper.toDto(e)));
//    log.info("Found {} address types", addressTypes.size());
//    return addressTypes;
    return findAll(EntityTypeEnum.ADDRESS_TYPE, addressTypeMapper, addressTypeDao, onlyActive);
  }

  public AddressTypeDto findById(Long id) {
//    log.info("Finding address type for id {}", id);
//
//    Optional<AddressType> found = addressTypeDao.findById(id);
//    if (found.isPresent()) {
//      log.debug("Found address type [{}]", found.get());
//      return addressTypeMapper.toDto(found.get());
//    }
//
//    log.error("No address type found for id {}", id);
//
//    return null;
    return findById(EntityTypeEnum.ADDRESS_TYPE, addressTypeMapper, addressTypeDao, id);
  }

  public AddressTypeDto update(AddressTypeDto dto) {
//    log.info("Updating address type [{}]", dto);
//
//    if (dto.getId() == null) {
//      log.error("Cannot update a non-existent address type");
//      return null;
//    }
//
//    dto.setLastUpdated(ZonedDateTime.now());
//    // TODO set lastUpdatedBy to currrent logged in user
//    VersionStatus status = VersionStatus.getInstance(dto.getStatus());
//    if (VersionStatus.New.equals(status)) {
//      dto.setStatus(VersionStatus.Amend.name());
//    }
//
//    AddressType entity = addressTypeMapper.toEntity(dto);
//    AddressType saved = addressTypeDao.save(entity);
//
//    log.info("Saved address type [{}]", saved);
//
//    return addressTypeMapper.toDto(saved);
    return update(EntityTypeEnum.ADDRESS_TYPE, addressTypeMapper, addressTypeDao, dto);
  }
}
