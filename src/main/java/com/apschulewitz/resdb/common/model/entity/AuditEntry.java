package com.apschulewitz.resdb.common.model.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

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

//	public Integer getId() {
//		return id;
//	}
//
//	public void setId(Integer id) {
//		this.id = id;
//	}


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogonName() {
		return logonName;
	}

	public void setLogonName(String logonName) {
		this.logonName = logonName;
	}

	public Date getAuditDate() {
		return auditDate;
	}

	public void setAuditDate(Date auditDate) {
		this.auditDate = auditDate;
	}

/*	public List<AuditDetail> getChanges() {
		return changes;
	}

	public void setChanges(List<AuditDetail> changes) {
		this.changes = changes;
	}*/

	@Override
	public boolean equals(Object o) {
		if (o == null)
			return false;

		if (this == o)
			return true;

		if (!(o instanceof AuditEntry))
			return false;

		AuditEntry other = (AuditEntry) o;

		return id == null ? other.getId() == null : id.equals(other.getId());
	}

	@Override
	public int hashCode() {
		return id == null ? 0 : id.hashCode();
	}

	public String toString() {
		return id == null ? "null" : id.toString();
	}


}
