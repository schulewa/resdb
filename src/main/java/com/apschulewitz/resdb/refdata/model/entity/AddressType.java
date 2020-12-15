package com.apschulewitz.resdb.refdata.model.entity;

import com.apschulewitz.resdb.common.model.VersionableDataEntity;
import com.apschulewitz.resdb.common.model.entity.DataOperation;
import com.apschulewitz.resdb.common.model.entity.EqualsAll;
import com.apschulewitz.resdb.common.model.entity.VersionStatus;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Tolerate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.persistence.Version;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.ZonedDateTime;

@Data
@Builder
@Entity
@Table(name = "resdb_address_type", uniqueConstraints = @UniqueConstraint(columnNames = {"name"}))
@EqualsAndHashCode
public class AddressType implements EqualsAll, VersionableDataEntity<Long> {
  private static final long serialVersionUID = -4217533971787415501L;

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @Column(name = "name", nullable = false, length = 30)
  @NotBlank
  private String name;

  @Column
  @NotNull
  private VersionStatus status;

  @Column
  @NotBlank
  private String createdBy;

  @Column
  private String updatedBy;

  @Version
  @Column(name = "version_no")
  private Long versionNumber;

  @Column(name = "last_updated")
  private ZonedDateTime lastUpdated;

  private transient DataOperation operation;

  public boolean equalsAll(Object o) {
    if (!(o instanceof AddressType))
      return false;

    boolean isEqual = true;
    AddressType other = (AddressType) o;

    if (!other.getName().equals(getName()) ||
      other.getId() != getId())
      isEqual = false;
    return isEqual;
  }

  @Tolerate
  public AddressType() {

  }

}
