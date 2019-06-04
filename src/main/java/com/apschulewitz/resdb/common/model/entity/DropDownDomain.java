package com.apschulewitz.resdb.common.model.entity;

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
@Table(name = "resdb_dropdown_domain")
public class DropDownDomain {
	private static final long serialVersionUID = 3689837171557349802L;

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "dropdown_class_id", nullable = false)
	private DropdownClass dropdownClass;

	@Column(name = "short_code", nullable = false)
    private String shortCode;

	@Column(nullable = false)
    private String description;

    @Tolerate
    public DropDownDomain() {

    }
}
