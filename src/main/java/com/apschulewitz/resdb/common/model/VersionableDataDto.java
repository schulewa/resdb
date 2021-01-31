package com.apschulewitz.resdb.common.model;

import com.apschulewitz.resdb.common.model.entity.DataEntityId;
import com.apschulewitz.resdb.common.model.entity.VersionStatus;

import java.time.ZonedDateTime;

/**
 *
 * @param <K> is the Key type for the data transfer object
 */
public interface VersionableDataDto<K> extends DataEntityId<K> {

  K getId();

  void setId(K key);

  String getStatus();

  void setStatus(String status);

  String getCreatedBy();

  void setCreatedBy(String createdBy);

  String getUpdatedBy();

  void setUpdatedBy(String updatedBy);

  ZonedDateTime getLastUpdated();

  void setLastUpdated(ZonedDateTime lastUpdated);

  Long getVersionNumber();

  void setVersionNumber(Long versionNumber);
}
