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
@Table(name = "resdb_material", uniqueConstraints = @UniqueConstraint(columnNames = {"name"}))
@Audited
public class Material {
	private static final long serialVersionUID = -5799435528378551262L;

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

	@Column(nullable = false)
    private String name;

    @Tolerate
    public Material() {

    }
}
