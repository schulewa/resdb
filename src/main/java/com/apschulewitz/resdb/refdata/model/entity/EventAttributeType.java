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
@Table(name = "resdb_event_attribute_type")
//@Audited
public class EventAttributeType {

	private static final long serialVersionUID = -6788107537813658220L;

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "event_id")
    private Event event;

	@Column(nullable = false)
	private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "data_class_association_id")
    private DataClassAssociation dataClassAssociation;


	@Column(nullable = false, name = "is_mandatory")
	private boolean mandatory;

    @Tolerate
    public EventAttributeType() {

    }
}
