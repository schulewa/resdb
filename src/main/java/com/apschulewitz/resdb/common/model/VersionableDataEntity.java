package com.apschulewitz.resdb.common.model;

import com.apschulewitz.resdb.common.model.entity.DataEntityId;
import com.apschulewitz.resdb.common.model.entity.VersionStatus;

import java.time.ZonedDateTime;

/**
 *
 * @param <K> is the Key type for the data entity
 */
public interface VersionableDataEntity<K> extends DataEntityId<K> {

  K getId();

  void setId(K key);

  VersionStatus getStatus();

  void setStatus(VersionStatus status);

  String getCreatedBy();

  void setCreatedBy(String createdBy);

  Long getVersionNumber();

  void setVersionNumber(Long versionNumber);

  String getUpdatedBy();

  void setUpdatedBy(String updatedBy);

  ZonedDateTime getLastUpdated();

  void setLastUpdated(ZonedDateTime lastUpdated);

}
