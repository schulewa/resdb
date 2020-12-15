package com.apschulewitz.resdb.refdata.model.entity;

import com.apschulewitz.resdb.common.model.entity.Title;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "resdb_person_title")
public class PersonTitle {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @ManyToOne
  @JoinColumn(name = "person_id")
  private Person person;

  @ManyToOne
  private Title title;

  @Column(nullable = false)
  private Integer position;
}
