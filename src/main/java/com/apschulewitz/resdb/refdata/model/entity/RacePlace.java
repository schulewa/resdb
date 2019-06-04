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
@Table(name = "resdb_race_place")
//@Audited
public class RacePlace {

	private static final long serialVersionUID = 3847823885252657385L;

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	private Race race;

	@Embedded
	@AttributeOverrides({
			@AttributeOverride(name="year", column = @Column(name="from_year")),
			@AttributeOverride(name="month", column = @Column(name="from_month")),
			@AttributeOverride(name="day", column = @Column(name="from_day"))
	})
	private HistoricalDate fromDate;

	@Embedded
	@AttributeOverrides({
			@AttributeOverride(name="year", column = @Column(name="to_year")),
			@AttributeOverride(name="month", column = @Column(name="to_month")),
			@AttributeOverride(name="day", column = @Column(name="to_day"))
	})
	private HistoricalDate toDate;

    @Tolerate
    public RacePlace() {

    }
}
