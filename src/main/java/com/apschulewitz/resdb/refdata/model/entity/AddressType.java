/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.apschulewitz.resdb.refdata.model.entity;


import com.apschulewitz.resdb.common.model.entity.DataOperation;
import com.apschulewitz.resdb.common.model.entity.EqualsAll;
import com.apschulewitz.resdb.common.model.entity.VersionStatus;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Tolerate;
import org.hibernate.envers.Audited;

import javax.persistence.*;
import java.time.LocalDateTime;


@Data
@Builder
@Entity
@Table(name = "resdb_address_type", uniqueConstraints = @UniqueConstraint(columnNames = {"name"}))
@EqualsAndHashCode
//@Audited
public class AddressType implements EqualsAll {
  private static final long serialVersionUID = -4217533971787415501L;

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @Column(name = "name", nullable = false, length = 30)
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
