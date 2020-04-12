package com.apschulewitz.resdb.refdata.model.entity;

import com.apschulewitz.resdb.common.model.entity.DataOperation;
import com.apschulewitz.resdb.common.model.entity.VersionStatus;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;
import org.hibernate.envers.Audited;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Builder
@Entity
@Table(name = "resdb_image_type", uniqueConstraints = @UniqueConstraint(columnNames = {"name"}))
//@Audited
public class ImageType {

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

  @Version
  @Column(name = "last_updated")
  private LocalDateTime lastUpdated;

  private transient DataOperation operation;

  @Tolerate
  public ImageType() {

  }
}
