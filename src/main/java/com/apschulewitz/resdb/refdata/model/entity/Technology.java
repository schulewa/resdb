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
@Table(name = "resdb_technology", uniqueConstraints = @UniqueConstraint(columnNames = {"name"}))
//@Audited
public class Technology {

	private static final long serialVersionUID = 1353730608087210073L;

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

	@Column(nullable = false, length = 30)
    private String name;

	@Column(nullable = true, length = 250)
    private String description;

	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "technology_type_id")
    private TechnologyType technologyType;

    @Tolerate
    public Technology() {

    }
}
