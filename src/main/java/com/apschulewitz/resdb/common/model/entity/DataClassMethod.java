package com.apschulewitz.resdb.common.model.entity;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;
import org.hibernate.envers.Audited;

import javax.persistence.*;

@Data
@Builder
@Entity
@Table(name = "resdb_data_class_method", uniqueConstraints = @UniqueConstraint(columnNames = { "data_class_key", "method_name"}))
@Audited
public class DataClassMethod {

	private static final long serialVersionUID = -5938409575777487278L;

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

	@Column(name = "data_class_key", nullable = false, length = 30)
	private String key;

	@Column(name = "method_name", nullable = false, length = 250)
	private String methodName;

    @Tolerate
    public DataClassMethod() {

    }
}
