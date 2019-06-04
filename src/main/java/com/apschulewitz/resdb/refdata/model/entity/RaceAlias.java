package com.apschulewitz.resdb.refdata.model.entity;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;
import org.hibernate.envers.Audited;

import javax.persistence.*;

@Data
@Builder
@Entity
@Table(name = "resdb_race_alias", uniqueConstraints = @UniqueConstraint(columnNames = {"from_race_id", "to_race_id"}))
//@Audited
public class RaceAlias {

	private static final long serialVersionUID = 6323092202287820426L;

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "from_race_id")
	private Race fromRace;

	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "to_race_id")
	private Race toRace;

    @Tolerate
    public RaceAlias() {

    }
}
