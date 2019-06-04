package com.apschulewitz.resdb.refdata.model.entity;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;
import org.hibernate.envers.Audited;

import javax.persistence.*;

@Data
@Builder
@Entity
@Table(name = "resdb_artefact_img_pttrn_smbl")
//@Audited
public class ArtefactImagePatternSymbol {

	private static final long serialVersionUID = -8598881903311091956L;

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "artefact_image_id")
	private ArtefactImage artefactImage;

	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pattern_symbol_id")
	private PatternSymbol patternSymbol;

    @Tolerate
    public ArtefactImagePatternSymbol() {

    }
}
