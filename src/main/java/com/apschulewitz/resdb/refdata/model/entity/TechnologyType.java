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
@Table(name = "resdb_technology_type", uniqueConstraints = @UniqueConstraint(columnNames = {"name"}))
//@Audited
public class TechnologyType {
	private static final long serialVersionUID = 1675481278314104160L;

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

	@Column(nullable = false)
    private String name;

	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tech_type_grp_id")
    private TechnologyTypeGroup technologyTypeGroup;

    @Tolerate
    public TechnologyType() {

    }
}
