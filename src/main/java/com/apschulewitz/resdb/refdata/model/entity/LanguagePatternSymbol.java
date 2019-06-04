package com.apschulewitz.resdb.refdata.model.entity;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;
import org.hibernate.envers.Audited;

import javax.persistence.*;

@Data
@Builder
@Entity
@Table(name = "resdb_language_pattern_symbol", uniqueConstraints = @UniqueConstraint(columnNames = {"language_id", "pattern_symbol_id"}))
//@Audited
public class LanguagePatternSymbol {

	private static final long serialVersionUID = 7828951586097060415L;

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;


	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "language_id")
	private Language language;

	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pattern_symbol_id")
	private PatternSymbol patternSymbol;

    @Tolerate
    public LanguagePatternSymbol() {

    }
}
