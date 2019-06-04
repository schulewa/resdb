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
@Table(name = "resdb_postal_address")
//@Audited
public class PostalAddress {
	private static final long serialVersionUID = -1594807961352233143L;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="entity_address_id", nullable = false)
    private EntityAddress entityAddress;

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

	@Column(nullable = false, name = "address_line_1", length = 50)
    private String addressLine1;

	@Column(nullable = true, name = "address_line_2", length = 50)
    private String addressLine2;

	@Column(nullable = true, name = "address_line_3", length = 50)
    private String addressLine3;

	@Column(nullable = true, name = "address_line_4", length = 50)
    private String addressLine4;

	@Column(nullable = true, name = "address_line_5", length = 50)
    private String addressLine5;

    @Tolerate
    public PostalAddress() {

    }
}
