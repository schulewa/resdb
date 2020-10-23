/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2020 All Rights Reserved.
 */
package com.apschulewitz.resdb.research.model.dto;

import com.apschulewitz.resdb.common.model.entity.VersionStatus;
import lombok.Data;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Adrian.Schulewitz
 * @version $Id: ClassificationCollectionDto.java, v 0.1 2020-10-18 13:53 adrianschulewitz.hds Exp $$
 */
@Data
public class ClassificationCollectionDto implements Cloneable {

  private Long id;
  private String name;
  private List<ClassificationEntryDto> entries;
  private VersionStatus status;

  public ClassificationCollectionDto() {
    entries = new ArrayList<>();
  }

  @Override
  public String toString() {
    StringBuilder buffer = new StringBuilder();

    buffer
      .append("ClassificationCollectionDto: ")
      .append("id=" + id + " ")
      .append("name=" + name + " ");

    if (!CollectionUtils.isEmpty(entries)) {
      buffer.append("entries: ");
      entries.forEach(e -> buffer.append("id=" + e.getId()).append("name=" + e.getName()));
    }

    buffer.append("status=" + status);
    return buffer.toString();
  }

  @Override
  public ClassificationCollectionDto clone() {
    ClassificationCollectionDto cloned = new ClassificationCollectionDto();
    cloned.setId(id);
    cloned.setName(name);
    cloned.setStatus(status);
    cloned.setEntries(entries);
    return cloned;
  }
}
