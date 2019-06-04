/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.apschulewitz.resdb.refdata.model.entity;

import com.apschulewitz.resdb.common.model.entity.HistoricalDate;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;
import org.hibernate.envers.Audited;

import javax.persistence.*;

/**
 *
 * @author adrian
 */
@Data
@Builder
@Entity
@Table(name = "resdb_artefact", uniqueConstraints = @UniqueConstraint(columnNames = {"name"}))
//@Audited
public class Artefact {

	private static final long serialVersionUID = 3621647821369051687L;

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

	@Column(nullable = false, length = 30)
    private String name;

	@Column(nullable = false, length = 500)
    private String description;

    @AttributeOverrides({
            @AttributeOverride(name = "year", column = @Column(name = "date_found_year")),
            @AttributeOverride(name = "month", column = @Column(name = "date_found_month")),
            @AttributeOverride(name = "day", column = @Column(name = "date_found_day"))
    })
    @Embedded()
    private HistoricalDate dateFound;

	@ManyToOne
	@JoinColumn(name = "artefact_type_id", nullable = false)
    private ArtefactType artefactType;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "found_by_person_id", nullable = true)
    private Person foundByPerson;

	@Column(name = "owner_identifier", nullable = true, length = 30)
    private String ownerIdentifier;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "technology_id", nullable = true)
    private Technology technology;

    // TODO add List of composition Material's
//    @ManyToOne
//    @JoinColumn(name = "composition_material_id", nullable = true)
//    private Material compositionMaterial;

    @Tolerate
    public Artefact() {

    }
}
