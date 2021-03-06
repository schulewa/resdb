package com.apschulewitz.resdb.refdata.model.entity;

import com.apschulewitz.resdb.common.model.entity.VersionStatus;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import java.time.LocalDateTime;

@Data
@Builder
@Entity
@Table(name = "resdb_measure", uniqueConstraints = @UniqueConstraint(columnNames = {"name", "measure_type_id"}))
public class Measure {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(length = 500)
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "measure_type_id")
    private MeasureType measureType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "associated_with_race_id")
    private Race associatedWith;

    @Column
    private VersionStatus status;

    @Column
    private String createdBy;

    @Column
    private String updatedBy;

    @Column(name = "last_updated")
    private LocalDateTime lastUpdated;

    @Tolerate
    public Measure() {
        super();
    }
}
