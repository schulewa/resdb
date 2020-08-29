package com.apschulewitz.resdb.refdata.model.entity;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;


/**
 * Entity implementation class for Entity: Role
 *
 */
@Data
@Builder
@Entity
@Table(name="resdb_role", uniqueConstraints = @UniqueConstraint(columnNames = {"name"}))
public class Role {

	private static final long serialVersionUID = 4046841340399553632L;

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(nullable=false)
	private String name;

    @Tolerate
    public Role() {

    }
}
