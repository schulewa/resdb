package com.apschulewitz.resdb.refdata.model.entity;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;
import org.hibernate.envers.Audited;

import javax.persistence.*;

@Data
@Builder
@Entity
@Table(name = "resdb_country_region")
//@Audited
public class CountryRegion {

  private static final long serialVersionUID = 4133328198668165705L;

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY)
  private Country country;

  @ManyToOne(fetch = FetchType.LAZY)
  private Region region;

  @Tolerate
  public CountryRegion() {

  }
}
