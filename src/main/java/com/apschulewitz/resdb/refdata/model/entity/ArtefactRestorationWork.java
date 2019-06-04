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
@Table(name = "resdb_artefact_rstrtn_work")
//@Audited
public class ArtefactRestorationWork {

	private static final long serialVersionUID = 8312566960623917066L;

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

	@Column(nullable = false)
	private String description;

	@ManyToOne(fetch = FetchType.LAZY)
	private Artefact artefact;

    @AttributeOverrides({
            @AttributeOverride(name = "year", column = @Column(name = "date_started_year")),
            @AttributeOverride(name = "month", column = @Column(name = "date_started_month")),
            @AttributeOverride(name = "day", column = @Column(name = "date_started_day"))
    })
    @Embedded()
    private HistoricalDate dateStarted;

    @AttributeOverrides({
            @AttributeOverride(name = "year", column = @Column(name = "date_finished_year")),
            @AttributeOverride(name = "month", column = @Column(name = "date_finished_month")),
            @AttributeOverride(name = "day", column = @Column(name = "date_finished_day"))
    })
    @Embedded()
    private HistoricalDate dateFinished;

    @Tolerate
    public ArtefactRestorationWork() {

    }
}
