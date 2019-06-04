package com.apschulewitz.resdb.common.model.entity;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;

import javax.persistence.*;

@Data
@Builder
@Entity
@Table(name = "resdb_dropdown_class", uniqueConstraints = @UniqueConstraint(columnNames = {"name"}))
public class DropdownClass {

	private static final long serialVersionUID = -1102048074966467319L;

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

	@Column(nullable = false)
	private String name;

    @Tolerate
    public DropdownClass() {

    }
}
