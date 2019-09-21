package com.apschulewitz.resdb.common.model.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@EqualsAndHashCode(exclude = { "logonName", "auditDate" })
@Data
@Entity
@Table(name = "resdb_audit_header")
//@TableGenerator(name = "AuditHeaderGenerator", table = "resdb_sequence_number", pkColumnName = "table_name", valueColumnName = "next_id", pkColumnValue = "resdb_audit_header")
public class AuditEntry implements Serializable {

	private static final long serialVersionUID = -3980291910325328890L;

	@Id
//	@GeneratedValue(generator = "AuditHeaderGenerator")
//	private Integer id;
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

	@Column(name = "logon_name", nullable = false)
	private String logonName;

	@Column(name = "audit_date", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date auditDate;

	//private List<AuditDetail> changes;

	public AuditEntry() {
		//changes = new ArrayList<AuditDetail>();
	}

}
