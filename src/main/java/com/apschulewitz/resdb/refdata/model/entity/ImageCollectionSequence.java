package com.apschulewitz.resdb.refdata.model.entity;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;
import org.hibernate.envers.Audited;

import javax.persistence.*;

@Data
@Builder
@Entity
@Table(name = "resdb_image_collection_seq")
//@Audited
public class ImageCollectionSequence {

	private static final long serialVersionUID = -5799957949119584439L;

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "image_collection_id")
	private ImageCollection imageCollection;

	@Column(name = "row_number", nullable = false)
	private Integer rowNumber;

	@Column(name = "column_number", nullable = false)
	private Integer columnNumber;

	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "artefact_image_id")
	private ArtefactImage artefactImage;

    @Tolerate
    public ImageCollectionSequence() {

    }
}
