package com.apschulewitz.resdb.common.model.entity;

import lombok.Data;
import lombok.Getter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@Table(name = "resdb_audit_detail")
//@TableGenerator(name = "AuditDetailGenerator", table = "resdb_sequence_number", pkColumnName = "table_name", valueColumnName = "next_id", pkColumnValue = "resdb_audit_detail")
public class AuditDetail implements Serializable {

	private static final long serialVersionUID = -392635307517079928L;

	@Id
//	@GeneratedValue(generator = "AuditDetailGenerator")
//	private Integer id;
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

	@Column(name = "table_name", nullable = false)
	private String tableName;

	@Column(name = "column_name", nullable = false)
	private String columnName;

	@Column(nullable = false, length = 1)
	private String action;

	@Column(name = "audit_date", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date auditDate;

	@Column(name = "old_value", nullable = true)
	private String oldValue;

	@Column(name = "new_value", nullable = true)
	private String newValue;

	@Override
	public boolean equals(Object o) {
		if (o == null)
			return false;

		if (this == o)
			return true;

		if (!(o instanceof AuditDetail))
			return false;

		AuditDetail other = (AuditDetail) o;

		return id == null ? other.getId() == null : id.equals(other.getId());
	}

	@Override
	public int hashCode() {
		return id == null ? 0 : id.hashCode();
	}

	@Override
	public String toString() {
		return id == null ? "null" : id.toString();
	}
}
