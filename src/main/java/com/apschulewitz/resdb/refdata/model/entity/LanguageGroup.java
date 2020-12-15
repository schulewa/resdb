/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
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
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.persistence.Version;
import java.time.ZonedDateTime;

/**
 * @author adrian
 */

@Data
@Builder
@Entity
@Table(name = "resdb_language_group", uniqueConstraints = @UniqueConstraint(columnNames = {"name"}))
public class LanguageGroup implements VersionableDataEntity<Long> {
    private static final long serialVersionUID = 2565908687969803475L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;


    @ManyToOne
    private Region region;

    @Column
    private VersionStatus status;

    @Column
    private String createdBy;

    @Column
    private String updatedBy;

    @Version
    @Column(name = "version_no")
    private Long versionNumber;

    @Column(name = "last_updated")
    private ZonedDateTime lastUpdated;

    private transient DataOperation operation;

    @Tolerate
    public LanguageGroup() {

    }
}
