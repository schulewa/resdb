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
 *
 * @author adrian
 */

@Data
@Builder
@Entity
@Table(name = "resdb_entity", uniqueConstraints = @UniqueConstraint(columnNames = {"name"}))
//@Audited
public class EntityInstance {
	private static final long serialVersionUID = -3663847178260085062L;

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

	@Column(nullable = false)
    private String name;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "entity_type_id", nullable = false)
    private EntityType entityType;

    @Tolerate
    public EntityInstance() {

    }
}
