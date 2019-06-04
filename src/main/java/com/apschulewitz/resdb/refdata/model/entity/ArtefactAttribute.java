package com.apschulewitz.resdb.refdata.model.entity;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;
import org.hibernate.envers.Audited;

import javax.persistence.*;

@Data
@Builder
@Entity
@Table(name = "resdb_artefact_attribute",
        uniqueConstraints = @UniqueConstraint(columnNames = {"artefact_id", "artefact_attribute_type_id"}))
//@Audited
public class ArtefactAttribute {

	private static final long serialVersionUID = -7523134873471623312L;

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

	@ManyToOne()
	@JoinColumn(name = "artefact_id", nullable = false)
	private Artefact artefact;

	@ManyToOne()
	@JoinColumn(name = "artefact_attribute_type_id", nullable = false)
	private ArtefactAttributeType artefactAttributeType;

    @Tolerate
    public ArtefactAttribute() {

    }
}
