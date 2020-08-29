package com.apschulewitz.resdb.refdata.model.entity;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Data
@Builder
@Entity
@Table(name = "resdb_race", uniqueConstraints = @UniqueConstraint(columnNames = {"name"}))
public class Race {

	private static final long serialVersionUID = -797677931237066395L;

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

	@Column(nullable = false)
	private String name;

	@OneToOne
    private RaceType raceType;

    @Tolerate
    public Race() {

    }
}
