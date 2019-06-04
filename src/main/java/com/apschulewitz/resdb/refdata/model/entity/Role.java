package com.apschulewitz.resdb.refdata.model.entity;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;
import org.hibernate.envers.Audited;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Role
 *
 */
@Data
@Builder
@Entity
@Table(name="resdb_role", uniqueConstraints = @UniqueConstraint(columnNames = {"name"}))
//@Audited
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
