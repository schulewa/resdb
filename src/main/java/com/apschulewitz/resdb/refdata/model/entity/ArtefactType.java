/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.apschulewitz.resdb.refdata.model.entity;

import com.apschulewitz.resdb.common.model.entity.DataOperation;
import com.apschulewitz.resdb.common.model.entity.VersionStatus;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;
import org.hibernate.envers.Audited;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author adrian
 */

@Data
@Builder
@Entity
@Table(name = "resdb_artefact_type", uniqueConstraints = @UniqueConstraint(columnNames = {"name"}))
//@Audited
public class ArtefactType implements Serializable {

  private static final long serialVersionUID = -3849879507291454015L;

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @Column(nullable = false, length = 20)
  private String name;

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
  public ArtefactType() {

  }
}
