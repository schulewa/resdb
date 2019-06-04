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
@Table(name = "resdb_river", uniqueConstraints = @UniqueConstraint(columnNames = {"name"}))
@Audited
public class River {
	private static final long serialVersionUID = 1014223124989151689L;

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

	@Column(nullable = false)
    private String name;

    @Tolerate
    public River() {

    }
}
