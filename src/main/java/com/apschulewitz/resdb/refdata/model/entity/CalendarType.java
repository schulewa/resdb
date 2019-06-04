package com.apschulewitz.resdb.refdata.model.entity;

import com.apschulewitz.resdb.common.model.entity.DataOperation;
import com.apschulewitz.resdb.common.model.entity.VersionStatus;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Builder
@Entity
@Table(name = "resdb_calendar_type", uniqueConstraints = @UniqueConstraint(columnNames = {"name"}))
//@Audited
public class CalendarType implements Serializable {

  private static final long serialVersionUID = 4392365581681351718L;

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @Column(nullable = false, length = 30)
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
  public CalendarType() {
  }

}
