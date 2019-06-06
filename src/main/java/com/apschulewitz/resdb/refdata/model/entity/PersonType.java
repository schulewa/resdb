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
@Table(name = "resdb_person_type", uniqueConstraints = @UniqueConstraint(columnNames = {"name"}))
//@Audited
public class PersonType {

	private static final long serialVersionUID = -4967178407353108249L;

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

	@Column(nullable = false)
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
    public PersonType() {

    }
}