package com.apschulewitz.resdb.refdata.model.entity;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;
import org.hibernate.envers.Audited;

import javax.persistence.*;

@Data
@Builder
@Entity
@Table(name = "resdb_calendar", uniqueConstraints = @UniqueConstraint(columnNames = {"name"}))
//@Audited
public class Calendar {

	private static final long serialVersionUID = 592832984708975080L;

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "calendar_type_id")
	private CalendarType calendarType;

	@Column(nullable = false)
	private String name;

    @Tolerate
    public Calendar() {

    }
}
