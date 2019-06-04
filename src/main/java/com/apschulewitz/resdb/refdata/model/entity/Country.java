package com.apschulewitz.resdb.refdata.model.entity;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;
import org.hibernate.envers.Audited;

import javax.persistence.*;

@Data
@Builder
@Entity
@Table(name = "resdb_country", uniqueConstraints = @UniqueConstraint(columnNames = {"name"}))
@Audited
public class Country {

	private static final long serialVersionUID = 375293409398115258L;

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

	@Column(nullable = false)
	private String name;

    @Tolerate
    public Country() {

    }
}
