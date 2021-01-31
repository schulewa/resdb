/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2020 All Rights Reserved.
 */
package com.apschulewitz.resdb.research.model.dto;

import com.apschulewitz.resdb.common.model.VersionableDataDto;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.util.CollectionUtils;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Adrian.Schulewitz
 * @version $Id: ClassificationCollectionDto.java, v 0.1 2020-10-18 13:53 adrianschulewitz.hds Exp $$
 */
@Data
//@Builder
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ClassificationCollectionDto implements VersionableDataDto<Long>, Cloneable {

  private Long id;
  private String name;
  private final List<ClassificationEntryDto> entries = new ArrayList<>();
  private String createdBy;
  private String status;
  private ZonedDateTime lastUpdated;
  private String updatedBy;
  private Long versionNumber;

//  public ClassificationCollectionDto() {
//    entries = new ArrayList<>();
//  }

  @Override
  public String toString() {
    StringBuilder buffer = new StringBuilder();

    buffer
      .append("ClassificationCollectionDto: ")
      .append("id=" + id + " ")
      .append("name=" + name + " ");

    if (!CollectionUtils.isEmpty(entries)) {
      buffer.append("entries: ");
      entries.forEach(e -> buffer.append("id=" + e.getId()).append(" name=" + e.getName()));
    }

    buffer.append("status=" + status + " ");
    buffer.append("createdBy=" + createdBy + " ");
    return buffer.toString();
  }

  @Override
  public ClassificationCollectionDto clone() {
    ClassificationCollectionDto cloned = new ClassificationCollectionDto();
    cloned.setId(id);
    cloned.setName(name);
    cloned.setStatus(status);
    cloned.setCreatedBy(createdBy);
    cloned.getEntries().addAll(entries);
    return cloned;
  }
}
