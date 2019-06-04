package com.apschulewitz.resdb.refdata.model.entity;

import com.apschulewitz.resdb.common.model.entity.HistoricalDate;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;
import org.hibernate.envers.Audited;

import javax.persistence.*;

@Data
@Builder
@Entity
@Table(name = "resdb_event")
//@Audited
public class Event {

	private static final long serialVersionUID = -8208274044480544569L;

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

	@Column(nullable = false)
	private String name;

	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "event_type_id")
	private EventType eventType;

	@Column
	private String description;

//	@ManyToOne(fetch = FetchType.LAZY)
//	private Person involvingPerson;

	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "place_id")
	private Place location;

    @AttributeOverrides({
            @AttributeOverride(name = "year", column = @Column(name = "event_date_year")),
            @AttributeOverride(name = "month", column = @Column(name = "event_date_month")),
            @AttributeOverride(name = "day", column = @Column(name = "event_date_day"))
    })
    @Embedded()
    private HistoricalDate eventDate;

    @Tolerate
    public Event() {

    }
}
