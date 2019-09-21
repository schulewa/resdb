package com.apschulewitz.resdb.common.model.entity;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Tolerate;

import javax.persistence.*;

@Data
@Builder
@EqualsAndHashCode
@Entity
@Table(name="resdb_application_version",
        uniqueConstraints = @UniqueConstraint(columnNames = {"major_version", "minor_version", "bug_fix_version"}))
public class ApplicationVersion {

	private static final long serialVersionUID = 6831966538769270034L;

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

	@Column(name="major_version", nullable = false)
	private Integer majorVersion;

	@Column(name="minor_version", nullable=false)
	private Integer minorVersion;

	@Column(name="bug_fix_version", nullable=false)
	private Integer bugFixVersion;

    @Column(length = 100)
    private String description;

    @Tolerate
    public ApplicationVersion() {

    }

}
