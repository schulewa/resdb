/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2020 All Rights Reserved.
 */
package com.apschulewitz.resdb.common.model.mapper;

import com.apschulewitz.resdb.research.model.dto.ClassificationCollectionDto;
import com.apschulewitz.resdb.research.model.entity.ClassificationCollection;
import org.springframework.stereotype.Component;

/**
 * @author Adrian.Schulewitz
 * @version $Id: ClassificationCollectionConverter.java, v 0.1 2020-10-18 17:40 adrianschulewitz.hds Exp $$
 */
@Component
public class ClassificationCollectionMapper implements EntityMapper<ClassificationCollection, ClassificationCollectionDto> {

  private ClassificationEntryMapper classificationEntryMapper;

  public ClassificationCollectionMapper(ClassificationEntryMapper classificationEntryMapper) {
    this.classificationEntryMapper = classificationEntryMapper;
  }

  @Override
  public ClassificationCollectionDto toDto(ClassificationCollection entity) {
    ClassificationCollectionDto dto = new ClassificationCollectionDto();
    dto.setId(entity.getId());
    dto.setName(entity.getName());
    dto.setStatus(entity.getStatus());
    dto.setCreatedBy(entity.getCreatedBy());

    entity.getEntries().forEach(e -> dto.getEntries().add(classificationEntryMapper.toDto(e)));

    return dto;
  }

  @Override
  public ClassificationCollection toEntity(ClassificationCollectionDto dto) {
    ClassificationCollection entity = new ClassificationCollection();
    entity.setId(dto.getId());
    entity.setName(dto.getName());
    entity.setStatus(dto.getStatus());
    entity.setCreatedBy(dto.getCreatedBy());

    dto.getEntries().forEach(e -> entity.getEntries().add(classificationEntryMapper.toEntity(e)));

    return entity;
  }

//  @Override
//  public ClassificationCollectionDto toDto(ClassificationCollection entity, boolean onlyActive) {
//    return null;
//  }
//
//  @Override
//  public ClassificationCollection toEntity(ClassificationCollectionDto dto, boolean onlyActive) {
//    return null;
//  }
}
