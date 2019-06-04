package com.apschulewitz.resdb.refdata.model.entity;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;
import org.hibernate.envers.Audited;

import javax.persistence.*;

@Data
@Builder
@Entity
@Table(name = "resdb_religion", uniqueConstraints = @UniqueConstraint(columnNames = {"name"}))
@Audited
public class Religion {

	private static final long serialVersionUID = 1766289501148250599L;

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

	@Column(nullable = false)
	private String name;

    @Tolerate
    public Religion() {

    }
}
