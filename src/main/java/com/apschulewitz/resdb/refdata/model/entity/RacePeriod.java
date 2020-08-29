package com.apschulewitz.resdb.refdata.model.entity;

import com.apschulewitz.resdb.common.model.entity.HistoricalDate;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Data
@Builder
@Entity
@Table(name = "resdb_race_period", uniqueConstraints = @UniqueConstraint(columnNames = {"race_id", "period_id"}))
/**
 * <CODE>RacePeriod</CODE> holds the details for a period in history for a specified race.
 * The dates are optional but, if set, form the accepted time range for the period for the specified race.
 * If the dates are not specified then the dates on Period will be used (if present).
 */
public class RacePeriod {

	private static final long serialVersionUID = -2824975574490912087L;

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

	@ManyToOne
	private Race race;

	@ManyToOne
    private Period period;

	@Column(length = 1)
    private Boolean startAppromixated;

	@Embedded
	@AttributeOverrides({
			@AttributeOverride(name="year", column = @Column(name="period_start_year")),
			@AttributeOverride(name="month", column = @Column(name="period_start_month")),
			@AttributeOverride(name="day", column = @Column(name="period_start_day"))
	})
    private HistoricalDate fromDate;

    @Column(length = 1)
    private Boolean endAppromixated;

	@Embedded
	@AttributeOverrides({
			@AttributeOverride(name="year", column = @Column(name="period_end_year")),
			@AttributeOverride(name="month", column = @Column(name="period_end_month")),
			@AttributeOverride(name="day", column = @Column(name="period_end_day"))
	})
	private HistoricalDate toDate;

    @Tolerate
    public RacePeriod() {

    }
}
