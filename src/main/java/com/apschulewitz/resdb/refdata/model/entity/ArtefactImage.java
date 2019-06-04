package com.apschulewitz.resdb.refdata.model.entity;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;
import org.hibernate.envers.Audited;

import javax.persistence.*;

@Data
@Builder
@Entity
@Table(name = "resdb_artefact_image")
//@Audited
public class ArtefactImage {

	private static final long serialVersionUID = 4370261469233331349L;

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	private Artefact artefact;

	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "image_collection_header_id")
	private ImageCollection imageCollection;

    @Tolerate
    public ArtefactImage() {

    }
}
