package com.apschulewitz.resdb.refdata.model.entity;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;
import org.hibernate.envers.Audited;

import javax.persistence.*;

@Data
@Builder
@Entity
@Table(name = "resdb_bibliography", uniqueConstraints = @UniqueConstraint(columnNames = {"name"}))
@Audited
public class Bibliography {

	private static final long serialVersionUID = -9131599682894755000L;

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

	@Column(nullable = false, length = 30)
	private String name;

    @Tolerate
    public Bibliography() {

    }
}
