package com.apschulewitz.resdb.common.model.entity;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * Created with IntelliJ IDEA.
 * User: Adrian
 * Date: 19/05/13
 * Time: 16:13
 * To change this template use File | Settings | File Templates.
 */
@Data
@Builder
@Embeddable
@Access(AccessType.PROPERTY)
public class HistoricalDate {

    @Column(name = "year", nullable = true)
    private Integer year;

    @Column(name = "month", nullable = true)
    private Integer month;

    @Column(name = "day", nullable = true)
    private Integer day;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "calendar_id", insertable = true, updatable = true, nullable = true) // might not initially know the calendar
//    private Calendar calendar;

    @Tolerate
    public HistoricalDate() {

    }
}
