/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
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

/**
 * @author adrian
 */

@Data
@Builder
@Entity
@Table(name = "resdb_artefact_type", uniqueConstraints = @UniqueConstraint(columnNames = {"name"}))
//@Audited
public class ArtefactType implements /*Serializable*/ VersionableDataEntity<Long> {

  private static final long serialVersionUID = -3849879507291454015L;

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @Column(nullable = false, length = 20)
  private String name;

  @Column(nullable = false)
  private VersionStatus status;

  @Column(nullable = false)
  private String createdBy;

  @Column(name = "version_no")
  private Long versionNumber;

  @Column
  private String updatedBy;

  @Column(name = "last_updated")
  private ZonedDateTime lastUpdated;

  private transient DataOperation operation;

  @Tolerate
  public ArtefactType() {

  }
}
