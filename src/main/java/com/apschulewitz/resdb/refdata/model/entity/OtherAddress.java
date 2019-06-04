package com.apschulewitz.resdb.refdata.model.entity;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;
import org.hibernate.envers.Audited;

import javax.persistence.*;

/**
 * @author adrian
 */

@Data
@Builder
@Entity
@Table(name = "resdb_other_address", uniqueConstraints = @UniqueConstraint(columnNames = {"address_id"}))
//@Audited
public class OtherAddress {
  private static final long serialVersionUID = 3219625298627096476L;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "address_id")
  private EntityAddress entityAddress;

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @Column(name = "address_text", nullable = false)
  private String addressText;

  @Tolerate
  public OtherAddress() {

  }
}
