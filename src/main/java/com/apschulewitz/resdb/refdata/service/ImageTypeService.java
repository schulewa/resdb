package com.apschulewitz.resdb.refdata.service;

import com.apschulewitz.resdb.common.model.EntityTypeEnum;
import com.apschulewitz.resdb.common.model.entity.VersionStatus;
import com.apschulewitz.resdb.common.utils.LoggingUtils;
import com.apschulewitz.resdb.refdata.model.dao.ImageTypeDao;
import com.apschulewitz.resdb.refdata.model.dto.EventTypeGroupDto;
import com.apschulewitz.resdb.refdata.model.dto.ImageTypeDto;
import com.apschulewitz.resdb.refdata.model.entity.ImageType;
import com.apschulewitz.resdb.refdata.model.mapper.ImageTypeMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

@Service
@Slf4j
public class ImageTypeService {

  private ImageTypeDao imageTypeDao;

  private ImageTypeMapper imageTypeMapper;

  public ImageTypeService(ImageTypeDao imageTypeDao, ImageTypeMapper imageTypeMapper) {
    this.imageTypeDao = imageTypeDao;
    this.imageTypeMapper = imageTypeMapper;
  }

  public ImageTypeDto add(ImageTypeDto dto) {
    LoggingUtils.logStartOfAddRequest(EntityTypeEnum.IMAGE_TYPE, dto);

    ImageType entity = imageTypeMapper.toEntity(dto);
    ImageType saved = imageTypeDao.save(entity);

    LoggingUtils.logAddedEntity(EntityTypeEnum.IMAGE_TYPE, saved);

    return imageTypeMapper.toDto(saved);
  }

  public ImageTypeDto deleteById(Long id) {
    LoggingUtils.logAttemptingToMarkEntityAsDeleted(EntityTypeEnum.IMAGE_TYPE, id);

    Optional<ImageType> found = imageTypeDao.findById(id);
    if (found.isEmpty()) {
      LoggingUtils.logNoEntityFoundForId(EntityTypeEnum.IMAGE_TYPE, id);
      return null;
    }

    if (!VersionStatus.isActive(found.get().getStatus())) {
      LoggingUtils.logUnableToMarkInactiveEntityAsDeleted(EntityTypeEnum.IMAGE_TYPE, id);
      return null;
    }

    ImageType toBeDeleted = found.get();
    toBeDeleted.setStatus(VersionStatus.Cancel);
    toBeDeleted.setLastUpdated(LocalDateTime.now());
    // TODO set lastUpdatedBY to current logged in user
    ImageType deleted = imageTypeDao.save(toBeDeleted);

    LoggingUtils.logEntityMarkedAsDeleted(EntityTypeEnum.IMAGE_TYPE, id);

    return imageTypeMapper.toDto(deleted);
  }

  public List<ImageTypeDto> findAll(boolean onlyActive) {
    LoggingUtils.logStartOfFindAllRequest(EntityTypeEnum.IMAGE_TYPE);
    List<ImageTypeDto> imageTypes = new ArrayList<>();
    Iterable<ImageType> iter;
    if (onlyActive) {
      iter = imageTypeDao.findByStatusIn(VersionStatus.getLiveStatuses());
    } else {
      iter = imageTypeDao.findAll();
    }
    StreamSupport.stream(iter.spliterator(), false)
      .forEach(e -> imageTypes.add(imageTypeMapper.toDto(e)));
      LoggingUtils.logFoundCountEntities(EntityTypeEnum.IMAGE_TYPE, onlyActive, imageTypes.size());

    return imageTypes;
  }

  public ImageTypeDto findById(Long id) {
    LoggingUtils.logAttemptingToFindEntityForId(EntityTypeEnum.IMAGE_TYPE, id);

    Optional<ImageType> found = imageTypeDao.findById(id);
    if (found.isPresent()) {
      LoggingUtils.logFoundEntityForId(EntityTypeEnum.IMAGE_TYPE, id);
      return imageTypeMapper.toDto(found.get());
    }

    LoggingUtils.logFoundNoEntityForId(EntityTypeEnum.IMAGE_TYPE, id);

    return null;
  }

  public ImageTypeDto update(ImageTypeDto dto) {
    LoggingUtils.logAttemptingToUpdateEntity(EntityTypeEnum.IMAGE_TYPE, dto);

    if (dto.getId() == null) {
      LoggingUtils.logUnableToUpdateEntityWithNoId(EntityTypeEnum.IMAGE_TYPE, dto);
      return null;
    }

    dto.setLastUpdated(LocalDateTime.now());
    // TODO set lastUpdatedBy to currrent logged in user
    VersionStatus status = VersionStatus.getInstance(dto.getStatus());
    if (VersionStatus.New.equals(status)) {
      dto.setStatus(VersionStatus.Amend.name());
    }

    ImageType entity = imageTypeMapper.toEntity(dto);
    ImageType saved = imageTypeDao.save(entity);

    ImageTypeDto updated = imageTypeMapper.toDto(saved);

    LoggingUtils.logUpdatedEntity(EntityTypeEnum.IMAGE_TYPE, updated);

    return updated;
  }
}
