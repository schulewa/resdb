/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2020 All Rights Reserved.
 */
package com.apschulewitz.resdb.common.model.mapper;

import com.apschulewitz.resdb.common.model.entity.VersionStatus;
import com.apschulewitz.resdb.research.model.dto.ClassificationCollectionDto;
import com.apschulewitz.resdb.research.model.entity.ClassificationCollection;
import org.springframework.stereotype.Component;

/**
 * @author Adrian.Schulewitz
 * @version $Id: ClassificationCollectionConverter.java, v 0.1 2020-10-18 17:40 adrianschulewitz.hds Exp $$
 */
@Component
public class ClassificationCollectionMapper implements VersionableEntityDtoMapper<ClassificationCollection, ClassificationCollectionDto> {

  private ClassificationEntryMapper classificationEntryMapper;

  public ClassificationCollectionMapper(ClassificationEntryMapper classificationEntryMapper) {
    this.classificationEntryMapper = classificationEntryMapper;
  }

  @Override
  public ClassificationCollection toEntity(ClassificationCollectionDto dto) {
    if (dto == null) {
      throw new IllegalArgumentException("Null classification collection cannot be mapped to entity");
    }

    VersionStatus status = dto.getStatus() == null ? null : VersionStatus.getInstance(dto.getStatus());
    ClassificationCollection entity = new ClassificationCollection();
    entity.setCreatedBy(dto.getCreatedBy());
    entity.setId(dto.getId());
    entity.setLastUpdated(dto.getLastUpdated());
    entity.setName(dto.getName());
    entity.setStatus(status);
    entity.setUpdatedBy(dto.getUpdatedBy());
    entity.setVersionNumber(dto.getVersionNumber());

    dto.getEntries().forEach(e -> entity.getEntries().add(classificationEntryMapper.toEntity(e)));

    return entity;
  }

  @Override
  public ClassificationCollectionDto toDto(ClassificationCollection entity) {
    if (entity == null) {
      throw new IllegalArgumentException("Null classification collection cannot be mapped to dto");
    }

    String status = entity.getStatus() == null ? null : entity.getStatus().name();
    ClassificationCollectionDto dto = new ClassificationCollectionDto();
    dto.setCreatedBy(entity.getCreatedBy());
    dto.setId(entity.getId());
    dto.setLastUpdated(entity.getLastUpdated());
    dto.setName(entity.getName());
    dto.setStatus(status);
    dto.setUpdatedBy(entity.getUpdatedBy());
    dto.setVersionNumber(entity.getVersionNumber());

    entity.getEntries().forEach(e -> dto.getEntries().add(classificationEntryMapper.toDto(e)));

    return dto;
  }

  @Override
  public ClassificationCollectionDto toDto(ClassificationCollection entity, boolean onlyActive) {
    if (entity == null) {
      throw new IllegalArgumentException("Null classification collection cannot be mapped to dto");
    }

    if (VersionStatus.getLiveStatuses().contains(entity.getStatus()) || !onlyActive) {
      return toDto(entity);
    }
    return null;
  }

  @Override
  public ClassificationCollection toEntity(ClassificationCollectionDto dto, boolean onlyActive) {
    if (dto == null) {
      throw new IllegalArgumentException("Null classification collection cannot be mapped to entity");
    }

    if (VersionStatus.getLiveStatuses().contains(dto.getStatus()) || !onlyActive) {
      return toEntity(dto);
    }
    return null;
  }
}
