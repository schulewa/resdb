package com.apschulewitz.resdb.refdata.model.entity;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;
import org.hibernate.envers.Audited;

import javax.persistence.*;

@Data
@Builder
@Entity
@Table(name = "resdb_theory", uniqueConstraints = @UniqueConstraint(columnNames = {"name"}))
//@Audited
public class Theory {

	private static final long serialVersionUID = -7545363867126895221L;

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

	@Column(nullable = false)
	private String name;

	@Column(nullable = false)
	private String description;

	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_person_id")
	private Person author;

    @Tolerate
    public Theory() {

    }
}
