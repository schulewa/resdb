/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.apschulewitz.resdb.refdata.model.entity;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;

import javax.persistence.*;

/**
 *
 * @author adrian
 */

@Data
@Builder
@Entity
@Table(name = "resdb_person_alias")
public class PersonAlias {

	private static final long serialVersionUID = -5799377971329722483L;

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

	@OneToOne
	private Person fromPerson;

	@OneToOne
	private Person toPerson;

    @Tolerate
    public PersonAlias() {

    }
}
