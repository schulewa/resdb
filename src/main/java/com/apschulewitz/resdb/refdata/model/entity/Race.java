package com.apschulewitz.resdb.refdata.model.entity;


import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;
import org.hibernate.envers.Audited;

import javax.persistence.*;

@Data
@Builder
@Entity
@Table(name = "resdb_race", uniqueConstraints = @UniqueConstraint(columnNames = {"name"}))
//@Audited
public class Race {

	private static final long serialVersionUID = -797677931237066395L;

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

	@Column(nullable = false)
	private String name;

    @Tolerate
    public Race() {

    }
}
