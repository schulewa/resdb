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
@Table(name = "resdb_period", uniqueConstraints = @UniqueConstraint(columnNames = {"name"}))
/**
 * <CODE>Period</CODE> holds the details for a period in history.
 * The dates are optional but, if set, form the accepted time range for the period.
 */
public class Period {

	private static final long serialVersionUID = -2824975574490912087L;

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

	@Column(length = 50, nullable = false)
	private String name;

    @Column(length = 1, nullable = false)
    private Boolean startAppromixated;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name="year", column = @Column(name="period_start_year")),
            @AttributeOverride(name="month", column = @Column(name="period_start_month")),
            @AttributeOverride(name="day", column = @Column(name="period_start_day"))
    })
    private HistoricalDate fromDate;

    @Column(nullable = false, length = 1)
    private Boolean endAppromixated;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name="year", column = @Column(name="period_end_year")),
            @AttributeOverride(name="month", column = @Column(name="period_end_month")),
            @AttributeOverride(name="day", column = @Column(name="period_end_day"))
    })
    private HistoricalDate toDate;

    @Tolerate
    public Period() {

    }
}
