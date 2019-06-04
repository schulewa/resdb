package com.apschulewitz.resdb.refdata.model.entity;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;
import org.hibernate.envers.Audited;

import javax.persistence.*;

@Data
@Builder
@Entity
@Table(name = "resdb_event_type", uniqueConstraints = @UniqueConstraint(columnNames = {"name"}))
//@Audited
public class EventType {

	private static final long serialVersionUID = -7400857047540246516L;

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

	@Column(nullable = false)
	private String name;

	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "event_type_group_id")
	private EventTypeGroup eventTypeGroup;

    @Tolerate
    public EventType() {

    }
}
