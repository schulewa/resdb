/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2020 All Rights Reserved.
 */
package com.apschulewitz.resdb.common.model.converter;

import com.apschulewitz.resdb.research.model.dto.ClassificationCollectionDto;
import com.apschulewitz.resdb.research.model.dto.ClassificationEntryDto;
import com.apschulewitz.resdb.research.model.entity.ClassificationCollection;
import com.apschulewitz.resdb.research.model.entity.ClassificationEntry;
import org.springframework.stereotype.Component;

/**
 * @author Adrian.Schulewitz
 * @version $Id: ClassificationEntryConverter.java, v 0.1 2020-10-18 15:18 adrianschulewitz.hds Exp $$
 */
@Component
public class ClassificationEntryConverter
  implements EntityToDtoConverter<ClassificationEntry, ClassificationEntryDto>,
  DtoToEntityConverter<ClassificationEntryDto, ClassificationEntry> {

  @Override
  public ClassificationEntryDto toDto(ClassificationEntry entity) {
    if (entity == null) {
      throw new IllegalArgumentException("Null ClassificationEntry object supplied for conversion");
    }

    ClassificationEntryDto entryDto = new ClassificationEntryDto();

    if (entity.getParentEntry() != null) {
      ClassificationEntryDto parentEntry = new ClassificationEntryDto();
      parentEntry.setId(entity.getParentEntry().getId());
      parentEntry.setName(entity.getParentEntry().getName());
      entryDto.setParentEntry(parentEntry);
    }

    entryDto.setId(entity.getId());
    entryDto.setName(entity.getName());

    return entryDto;
  }

  @Override
  public ClassificationEntry toEntity(ClassificationEntryDto dto) {
    if (dto == null) {
      throw new IllegalArgumentException("Null ClassificationEntryDto object supplied for conversion");
    }

    ClassificationEntry entry = new ClassificationEntry();

    if (dto.getParentEntry() != null) {
      ClassificationEntry parentEntry = new ClassificationEntry();
      parentEntry.setId(dto.getParentEntry().getId());
      parentEntry.setName(dto.getParentEntry().getName());
      entry.setParentEntry(parentEntry);
    }

    entry.setId(dto.getId());
    entry.setName(dto.getName());

    return entry;
  }
}
