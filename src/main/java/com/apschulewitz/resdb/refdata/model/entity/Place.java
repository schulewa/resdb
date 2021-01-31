/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.apschulewitz.resdb.refdata.model.entity;

import com.apschulewitz.resdb.common.model.VersionableDataEntity;
import com.apschulewitz.resdb.common.model.entity.Altitude;
import com.apschulewitz.resdb.common.model.entity.DataOperation;
import com.apschulewitz.resdb.common.model.entity.Latitude;
import com.apschulewitz.resdb.common.model.entity.Longitude;
import com.apschulewitz.resdb.common.model.entity.VersionStatus;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;
import org.hibernate.envers.Audited;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.ZonedDateTime;

/**
 *
 * @author adrian
 */

@Data
@Builder
@Entity
@Table(name = "resdb_place")
@Audited
public class Place implements VersionableDataEntity<Long> {

	private static final long serialVersionUID = 997091095625787583L;

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

	@Column(nullable = false)
    private String name;

	@Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "value", column = @Column(name = "altitude"))
    })
    private Altitude altitude;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "value", column = @Column(name = "latitude"))
    })
    private Latitude latitude;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "value", column = @Column(name = "longitude"))
    })
    private Longitude longitude;

//    @OneToMany(mappedBy="fromPlace", fetch = FetchType.LAZY)
//    @PrimaryKeyJoinColumn
//	private Set<PlaceAlias>  fromAliases;
//
//	@OneToMany(mappedBy="toPlace", fetch = FetchType.LAZY)
//	private Set<PlaceAlias>  toAliases;

	@ManyToOne// (fetch = FetchType.LAZY)
    private River river;

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
    public Place() {

    }
}
