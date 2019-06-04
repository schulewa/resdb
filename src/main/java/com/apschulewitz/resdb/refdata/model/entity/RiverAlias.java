package com.apschulewitz.resdb.refdata.model.entity;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;
import org.hibernate.envers.Audited;

import javax.persistence.*;

@Data
@Builder
@Entity
@Table(name = "resdb_river_alias", uniqueConstraints = @UniqueConstraint(columnNames = {"from_river_id", "to_river_id"}))
@Audited
public class RiverAlias {

	private static final long serialVersionUID = 7279029041845738728L;

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "from_river_id")
	private River fromRiver;

	@OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "to_river_id")
	private River toRiver;

    @Tolerate
    public RiverAlias() {

    }
}
