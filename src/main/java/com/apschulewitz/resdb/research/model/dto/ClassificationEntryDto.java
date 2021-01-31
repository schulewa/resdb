/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2020 All Rights Reserved.
 */
package com.apschulewitz.resdb.research.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Adrian.Schulewitz
 * @version $Id: ClassificationEntryDto.java, v 0.1 2020-10-18 13:47 adrianschulewitz.hds Exp $$
 */
//@Builder
@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ClassificationEntryDto {

  private Long id;

  private String name;

  private ClassificationCollectionDto owningCollection;

  /**
   * if parentEntry is null then this entry is at the top of the classification tree
   */
  private ClassificationEntryDto parentEntry;

  private List<ClassificationEntryDto> entries = new ArrayList<>();

  public void addEntry(String name) {
    ClassificationEntryDto entry = new ClassificationEntryDto();
//      .name(name)
//      .owningCollection(getOwningCollection())
//      .parentEntry(this)
//      .build();
    entry.setName(name);
    entry.setParentEntry(this);
    entry.setOwningCollection(getOwningCollection());
    entries.add(entry);
  }

  @Override
  public String toString() {
    StringBuilder buffer = new StringBuilder();
    buffer
      .append("ClassificationEntryDto: ")
      .append("id=" + id + " ")
      .append("name=" + name + " ");

    if (owningCollection != null) {
      buffer
        .append("owningCollection: ")
        .append("id=" + owningCollection.getId() + " ")
        .append("name=" + owningCollection.getName() + " ");
    }

    if (parentEntry != null) {
      buffer.append("parentEntry: ").append("id=" + parentEntry.getId() + " name=" + parentEntry.getName());
    }

    if (CollectionUtils.isEmpty(entries)) {
      buffer.append("entries: ");
      entries.forEach(e -> buffer.append("id=" + e.getId()).append("name=" + e.getName()));
    }

    return buffer.toString();
  }
}
