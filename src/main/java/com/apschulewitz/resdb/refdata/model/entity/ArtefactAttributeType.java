package com.apschulewitz.resdb.refdata.model.entity;

import com.apschulewitz.resdb.common.model.entity.DataClassAssociation;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;
import org.hibernate.envers.Audited;

import javax.persistence.*;

@Data
@Builder
@Entity
@Table(name = "resdb_artefact_attribute_type", uniqueConstraints = @UniqueConstraint(columnNames = {"name"}))
@Audited
public class ArtefactAttributeType {

	private static final long serialVersionUID = 2158468585363017973L;

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(nullable = false, length=20)
	private String name;

	@Column(name = "is_mandatory", nullable = false)
	private boolean mandatory;

    @ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "data_class_association_id", nullable = true)
	private DataClassAssociation dataClassAssociation;

    @Tolerate
    public ArtefactAttributeType() {

    }
}
