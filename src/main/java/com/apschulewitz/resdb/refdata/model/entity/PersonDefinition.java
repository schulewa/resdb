package com.apschulewitz.resdb.refdata.model.entity;

import lombok.experimental.Tolerate;
import org.hibernate.envers.Audited;

import javax.persistence.*;

/**
 * Created by adrianschulewitz on 07/08/2015.
 */
@Entity
@Table(name = "resdb_person_definition")
//@Audited
public class PersonDefinition {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne
    @JoinColumn(name = "person_id", nullable = false)
    Person person;

    @Tolerate
    public PersonDefinition() {

    }
}
