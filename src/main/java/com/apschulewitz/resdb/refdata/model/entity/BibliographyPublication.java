package com.apschulewitz.resdb.refdata.model.entity;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;
import org.hibernate.envers.Audited;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Builder
@Entity
@Table(name = "resdb_bibliography_publication")
//@Audited
public class BibliographyPublication implements Serializable {


	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	private Bibliography bibliography;

	@ManyToOne(fetch = FetchType.LAZY)
	private Publication publication;

    @Tolerate
    public BibliographyPublication() {

    }
}
