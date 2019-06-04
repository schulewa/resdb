package com.apschulewitz.resdb.refdata.model.entity;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;
import org.hibernate.envers.Audited;

import javax.persistence.*;

@Data
@Builder
@Entity
@Table(name = "resdb_library_location")
//@Audited
public class LibraryLocation {

	private static final long serialVersionUID = -326843948364369936L;

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

	@OneToOne
	private Publication publication;

	@OneToOne
	private EntityInstance entity;

    @Tolerate
    public LibraryLocation() {

    }
}
