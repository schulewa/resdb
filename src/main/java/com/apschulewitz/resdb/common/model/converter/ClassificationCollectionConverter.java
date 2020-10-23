/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2020 All Rights Reserved.
 */
package com.apschulewitz.resdb.common.model.converter;

import com.apschulewitz.resdb.research.model.dto.ClassificationCollectionDto;
import com.apschulewitz.resdb.research.model.entity.ClassificationCollection;
import org.springframework.stereotype.Component;

/**
 * @author Adrian.Schulewitz
 * @version $Id: ClassificationCollectionConverter.java, v 0.1 2020-10-18 17:40 adrianschulewitz.hds Exp $$
 */
@Component
public class ClassificationCollectionConverter
  implements EntityToDtoConverter<ClassificationCollection, ClassificationCollectionDto>,
              DtoToEntityConverter<ClassificationCollectionDto, ClassificationCollection> {

  private ClassificationEntryConverter classificationEntryConverter;

  public ClassificationCollectionConverter(ClassificationEntryConverter classificationEntryConverter) {
    this.classificationEntryConverter = classificationEntryConverter;
  }

  @Override
  public ClassificationCollectionDto toDto(ClassificationCollection entity) {
    ClassificationCollectionDto dto = new ClassificationCollectionDto();
    dto.setId(entity.getId());
    dto.setName(entity.getName());
    dto.setStatus(entity.getStatus());

    entity.getEntries().forEach(e -> dto.getEntries().add(classificationEntryConverter.toDto(e)));

    return dto;
  }

  @Override
  public ClassificationCollection toEntity(ClassificationCollectionDto dto) {
    ClassificationCollection entity = new ClassificationCollection();
    entity.setId(dto.getId());
    entity.setName(dto.getName());
    entity.setStatus(dto.getStatus());

    dto.getEntries().forEach(e -> entity.getEntries().add(classificationEntryConverter.toEntity(e)));

    return entity;
  }
}
