package com.apschulewitz.resdb.refdata.model.entity;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;
import org.hibernate.envers.Audited;

import javax.persistence.*;

@Data
@Builder
@Entity
@Table(name = "resdb_event_attribute")
//@Audited
public class EventAttribute {

	private static final long serialVersionUID = 7826363536151403887L;

	//private Artefact artefact;

	//private ArtefactAttribute artefactAttribute;

	@OneToOne
	private Event event;

	@OneToOne
    @JoinColumn(name = "event_attribute_type_id")
	private EventAttributeType eventAttributeType;

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Tolerate
    public EventAttribute() {

    }
}
