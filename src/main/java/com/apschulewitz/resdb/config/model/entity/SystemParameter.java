package com.apschulewitz.resdb.config.model.entity;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;
import org.hibernate.envers.Audited;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Builder
@Entity
@Table(name = "resdb_system_parameters", uniqueConstraints = @UniqueConstraint(columnNames = {"name"}))
@Audited
public class SystemParameter {


	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

	@Column(nullable = false)
	private String name;

    @Column
    private String description;

    @Column
    private String stringValue;

    @Column
    private Integer integerValue;

    @Tolerate
    public SystemParameter() {

    }
}
