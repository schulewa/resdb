package com.apschulewitz.resdb.refdata.model.entity;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;
import org.hibernate.envers.Audited;

import javax.persistence.*;

@Data
@Builder
@Entity
@Table(name = "resdb_pattern_symbol")
//@Audited
public class PatternSymbol {

	private static final long serialVersionUID = 8082106723852041374L;

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

	@Column(nullable = false)
	private String name;

	@Column(nullable = false)
	private String description;

	@ManyToOne(fetch = FetchType.LAZY)
	private Image image;

	@ManyToOne(fetch = FetchType.LAZY)
	private Race race;

	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reference_id")
	private Reference confirmedByReference;

    @Tolerate
    public PatternSymbol() {

    }
}
