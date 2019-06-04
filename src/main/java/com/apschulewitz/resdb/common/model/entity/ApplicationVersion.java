package com.apschulewitz.resdb.common.model.entity;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;

import javax.persistence.*;

@Data
@Builder
@Entity
@Table(name="resdb_application_version",
        uniqueConstraints = @UniqueConstraint(columnNames = {"major_version", "minor_version", "bug_fix_version"}))
public class ApplicationVersion  implements EqualsAll {

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


    public boolean equalsAll(Object o)
	{
        boolean same = true;

        if (o == null)
            return false;
        else if (!(o instanceof ApplicationVersion))
            return false;

		ApplicationVersion other = (ApplicationVersion)o;
		same = bugFixVersion == null ? other.getBugFixVersion() == null : bugFixVersion.equals(other.getBugFixVersion());

        if (same) {
            same = id == null ? other.getId() == null : id.equals(other.getId());

            if (same) {
                same = majorVersion == null ? other.getMajorVersion() == null : majorVersion.equals(other.getMajorVersion());

                if (same) {
                    same = minorVersion == null ? other.getMinorVersion() == null : minorVersion.equals(other.getMinorVersion());

                }
            }
        }

		return same;
	}

    @Tolerate
    public ApplicationVersion() {

    }

}
