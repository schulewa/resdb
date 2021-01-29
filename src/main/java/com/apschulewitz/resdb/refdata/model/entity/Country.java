package com.apschulewitz.resdb.refdata.model.entity;

import com.apschulewitz.resdb.common.model.VersionableDataEntity;
import com.apschulewitz.resdb.common.model.entity.DataOperation;
import com.apschulewitz.resdb.common.model.entity.VersionStatus;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import java.time.ZonedDateTime;

@Data
@Builder
@Entity
@Table(name = "resdb_country", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"code"}),
        @UniqueConstraint(columnNames = {"name"})
})
//@Audited
public class Country implements VersionableDataEntity<Long> {

	private static final long serialVersionUID = 375293409398115258L;

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

	@Column(nullable = false, length = 2)
    private String code; // ISO 3166 2 character code

	@Column(nullable = false, length = 75)
	private String name;

    @Column(nullable = false, length = 150)
    private String stateName;

    @Column(nullable = false, length = 50)
    private String sovereignty;

    @ManyToOne
    @JoinColumn(name = "flag_image_id", insertable = false, updatable = false) //
    private Image flagImage;

    @Column(nullable = false)
    private VersionStatus status;

    @Column(nullable = false, length = 30)
    private String createdBy;

    @Column(length = 30)
    private String updatedBy;

    @Column(name = "version_no")
    private Long versionNumber;

    @Column(name = "last_updated")
    private ZonedDateTime lastUpdated;

    private transient DataOperation operation;

    @Tolerate
    public Country() {

    }
}
