package com.apschulewitz.resdb.refdata.model.entity;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;
import org.hibernate.envers.Audited;

import javax.persistence.*;

@Data
@Builder
@Entity
@Table(name = "resdb_measure", uniqueConstraints = @UniqueConstraint(columnNames = {"name"}))
//@Audited
public class Measure {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "measure_type_id")
    private MeasureType measureType;

    @Tolerate
    public Measure() {
        super();
    }
}
