package com.apschulewitz.resdb.common.model.entity;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;
import org.hibernate.envers.Audited;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * @author adrian
 */
@Data
@Builder
@Entity
@Table(name = "resdb_title", uniqueConstraints = @UniqueConstraint(columnNames = {"title"}))
//@Audited
public class Title {

  private static final long serialVersionUID = -1411917831665423698L;

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @Column(nullable = false, length = 30)
  private String title;

  @Column(length = 250)
  private String description;

  @Column(nullable = false, length = 1, name = "applies_to")
  private Gender appliesTo;

  @Column(nullable = false, length = 1)
  private TitleType titleType;

  @Column
  private VersionStatus status;

  @Column
  private String createdBy;

  @Column
  private String updatedBy;

  @Version
  @Column(name = "last_updated")
  private LocalDateTime lastUpdated;

  private transient DataOperation operation;

  @Tolerate
  public Title() {

  }
}
