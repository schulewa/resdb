package com.apschulewitz.resdb.refdata.model.entity;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;

import javax.persistence.*;

@Data
@Builder
@Entity
@Table(name = "resdb_person_role")
public class PersonRole {

	private static final long serialVersionUID = -4967178407353108249L;

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

	@Column(nullable = false, length = 30, name = "role_name")
	private String roleName;

    @Column(nullable = false, name = "user_modifiable")
    private boolean userModifiable;

    @Tolerate
    public PersonRole() {

    }
}
