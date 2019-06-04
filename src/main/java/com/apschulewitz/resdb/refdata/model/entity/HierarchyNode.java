package com.apschulewitz.resdb.refdata.model.entity;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;
import org.hibernate.envers.Audited;

import javax.persistence.*;

@Data
@Builder
@Entity
@Table(name="resdb_hierarchy_node")
//@Audited
public class HierarchyNode {

	private static final long serialVersionUID = 1125146530396055761L;

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

	@Column(nullable = false)
	private String name;

	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_hierarchy_id")
	private Hierarchy parentHierarchy;

	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "first_child_hierarchy_id")
	private Hierarchy firstChildHierarchy;

	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "last_child_hierarchy_id")
	private Hierarchy lastChildHierarchy;

    @Tolerate
    public HierarchyNode() {

    }
}
