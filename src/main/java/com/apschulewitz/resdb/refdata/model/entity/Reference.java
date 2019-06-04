package com.apschulewitz.resdb.refdata.model.entity;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;
import org.hibernate.envers.Audited;

import javax.persistence.*;

@Data
@Builder
@Entity
@Table(name = "resdb_reference")
//@Audited
public class Reference {

	private static final long serialVersionUID = -3214923893894939513L;

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

	@Column(nullable = false, length = 250)
	private String description;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "reference_type_id")
	private ReferenceType referenceType;

	@ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "publication_id")
	private Publication inPublication;

    @Column(nullable = true, name = "page_number")
    private Integer pageNumber;

    @ManyToOne(fetch = FetchType.LAZY,optional = true)
    @JoinColumn(name = "relates_to_theory_id")
    private Theory relatesToTheory;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "relates_to_tale_id")
    private Tale relatesToTale;

	@Column(nullable = false, name = "location_in_pub", length = 30)
	private String locationInPublication;

    @Tolerate
    public Reference() {

    }
}
