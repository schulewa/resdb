package com.apschulewitz.resdb.refdata.model.entity;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;
import org.hibernate.envers.Audited;

import javax.persistence.*;

@Data
@Builder
@Entity
@Table(name = "resdb_calendar_race")
//@Audited
public class CalendarRace {

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	private Calendar calendar;

	@ManyToOne(fetch = FetchType.LAZY)
	private Race race;

    @Tolerate
    public CalendarRace() {

    }
}
