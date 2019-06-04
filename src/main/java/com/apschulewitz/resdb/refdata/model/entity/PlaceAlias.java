package com.apschulewitz.resdb.refdata.model.entity;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;
import org.hibernate.envers.Audited;

import javax.persistence.*;

@Data
@Builder
@Entity
@Table(name = "resdb_place_alias")
@Audited
public class PlaceAlias {

	private static final long serialVersionUID = 1369535747704341904L;

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="from_place_id", referencedColumnName = "id", nullable = false)
	private Place fromPlace;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="to_place_id", referencedColumnName = "id", nullable = false)
	private Place toPlace;

    @Tolerate
    public PlaceAlias() {

    }
}
