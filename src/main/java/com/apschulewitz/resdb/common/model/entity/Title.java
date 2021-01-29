package com.apschulewitz.resdb.common.model.entity;

import com.apschulewitz.resdb.common.model.VersionableDataEntity;
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
@Table(name = "resdb_title", uniqueConstraints = @UniqueConstraint(columnNames = {"title"}))
//@Audited
public class Title implements VersionableDataEntity<Long> {

  private static final long serialVersionUID = -1411917831665423698L;

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @Column(nullable = false, length = 30)
  private String title;

  @Column(length = 250)
  private String description;

  @Column(nullable = false, length = 1) //, name = "applies_to"
  private Gender appliesTo;

  @Column(nullable = false, length = 1)
  private TitleType titleType;

  @Column
  private VersionStatus status;

  @Column(nullable = false, length = 20)
  private String createdBy;

  @Column
  private String updatedBy;

  @Column(name = "version_no")
  private Long versionNumber;

  @Column(name = "last_updated", length = 20)
  private ZonedDateTime lastUpdated;

  private transient DataOperation operation;

  @Tolerate
  public Title() {

  }
}
