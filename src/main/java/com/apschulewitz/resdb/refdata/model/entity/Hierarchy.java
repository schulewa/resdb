package com.apschulewitz.resdb.refdata.model.entity;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;
import org.hibernate.envers.Audited;

import javax.persistence.*;

@Data
@Builder
@Entity
@Table(name="resdb_hierarchy", uniqueConstraints = @UniqueConstraint(columnNames = {"name"}))
//@Audited
public class Hierarchy {

	private static final long serialVersionUID = 7513310481733105383L;

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

	@Column(nullable = false)
	private String name;

	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hierarchy_type_id")
	private HierarchyType hierarchyType;

    @Tolerate
    public Hierarchy() {

    }
}
