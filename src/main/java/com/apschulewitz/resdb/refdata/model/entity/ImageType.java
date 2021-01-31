package com.apschulewitz.resdb.refdata.model.entity;

import com.apschulewitz.resdb.common.model.VersionableDataEntity;
import com.apschulewitz.resdb.common.model.entity.DataOperation;
import com.apschulewitz.resdb.common.model.entity.VersionStatus;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import java.time.ZonedDateTime;

@Data
@Builder
@Entity
@Table(name = "resdb_image_type", uniqueConstraints = @UniqueConstraint(columnNames = {"name"}))
public class ImageType implements VersionableDataEntity<Long> {

  private static final long serialVersionUID = -297230137006844035L;

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @Column(nullable = false, length = 30)
  private String name;

  @Column
  private VersionStatus status;

  @Column(length = 30)
  private String createdBy;

  @Column(length = 30)
  private String updatedBy;

  @Column(name = "version_no")
  private Long versionNumber;

  @Column(name = "last_updated")
  private ZonedDateTime lastUpdated;

  private transient DataOperation operation;

  @Tolerate
  public ImageType() {

  }
}
