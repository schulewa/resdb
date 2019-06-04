package com.apschulewitz.resdb.refdata.model.entity;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;
import org.hibernate.envers.Audited;

import javax.persistence.*;

@Data
@Builder
@Entity
@Table(name = "resdb_tale")
//@Audited
public class Tale {

	private static final long serialVersionUID = -8239405984505316585L;

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

	@Column(nullable = false)
	private String name;

	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tale_type_id")
	private TaleType taleType;

    @Tolerate
    public Tale() {

    }
}
