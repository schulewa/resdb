package com.apschulewitz.resdb.refdata.model.entity;

import com.apschulewitz.resdb.security.model.entity.UserGroupMembership;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.experimental.Tolerate;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.Collection;


/**
 * Created by adrianschulewitz on 07/08/2015.
 */
@Entity
@Table(name = "resdb_person_definition")
public class PersonDefinition {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne
    @JoinColumn(name = "person_id", nullable = false)
    Person person;

    @OneToMany(mappedBy = "roleName", cascade = CascadeType.ALL)
    private Collection<PersonRole> personRoles;

    @Tolerate
    public PersonDefinition() {

    }
}
