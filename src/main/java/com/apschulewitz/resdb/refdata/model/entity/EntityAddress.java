/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
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
@Table(name = "resdb_entity_address")
//@Audited
public class EntityAddress {
  private static final long serialVersionUID = 8579077908223447744L;

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @ManyToOne
  @JoinColumn(name = "entity_id")
  private EntityInstance ownerEntity;

  @ManyToOne
  @JoinColumn(name = "address_type_id")
  private AddressType addressType;

  @Tolerate
  public EntityAddress() {

  }
}
