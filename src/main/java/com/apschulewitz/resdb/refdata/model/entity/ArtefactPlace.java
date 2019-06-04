package com.apschulewitz.resdb.refdata.model.entity;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;
import org.hibernate.envers.Audited;

import javax.persistence.*;

@Data
@Builder
@Entity
@Table(name = "resdb_artefact_place")
//@Audited
public class ArtefactPlace {

	private static final long serialVersionUID = -6786897896431036880L;

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "artefact_id")
	private Artefact artefact;

	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "place_id")
	private Place place;

	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "held_at_entity_id")
	private EntityInstance heldAtEntity;

    @Tolerate
    public ArtefactPlace() {

    }
}
