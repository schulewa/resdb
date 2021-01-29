/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.apschulewitz.resdb.refdata.model.entity;

import com.apschulewitz.resdb.common.model.VersionableDataEntity;
import com.apschulewitz.resdb.common.model.entity.DataOperation;
import com.apschulewitz.resdb.common.model.entity.Gender;
import com.apschulewitz.resdb.common.model.entity.HistoricalDate;
import com.apschulewitz.resdb.common.model.entity.Title;
import com.apschulewitz.resdb.common.model.entity.VersionStatus;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.time.ZonedDateTime;
import java.util.List;

/**
 * @author adrian
 */

@Data
@Builder
@Entity
@Table(name = "resdb_person")
public class Person implements VersionableDataEntity<Long> {

//    private static final long serialVersionUID = -2639006949864237790L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "first_name", nullable = false, length = 30)
    private String firstName;

    @Column(name = "middle_name", length = 30)
    private String middleName;

    @Column(name = "family_name", nullable = false, length = 50)
    private String familyName;

    @Column(name = "gender")
    private Gender gender;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "day", column = @Column(name = "birth_day")),
            @AttributeOverride(name = "month", column = @Column(name = "birth_month")),
            @AttributeOverride(name = "year", column = @Column(name = "birth_year")),
            //@AttributeOverride(name = "calendar", column = @Column(name = "birth_calendar_id"))
    })
    private HistoricalDate dateOfBirth;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "day", column = @Column(name = "death_day")),
            @AttributeOverride(name = "month", column = @Column(name = "death_month")),
            @AttributeOverride(name = "year", column = @Column(name = "death_year")),
    })
    private HistoricalDate dateOfDeath;

    // default prefix title used when listing person
    @ManyToOne(cascade = CascadeType.ALL)
    private Title prefixTitle;

    // default suffix title used when listing person
    @ManyToOne(cascade = CascadeType.ALL)
    private Title suffixTitle;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "birth_place_id")
    private Place birthPlace;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "death_place_id")
    private Place deathPlace;

    @OneToMany(mappedBy = "person", cascade = CascadeType.ALL)
    private List<PersonTitle> titles;

//	@OneToMany(mappedBy="fromPerson", fetch = FetchType.LAZY)
//	private Set<PersonAlias>  fromAliases;
//
//	@OneToMany(mappedBy="toPerson", fetch = FetchType.LAZY)
//	private Set<PersonAlias> toAliases;

//	@OneToMany(mappedBy="person", fetch = FetchType.LAZY)
//	private Set<PersonDefinition> definition;

    //@ManyToOne(fetch = FetchType.LAZY)
    //private Set<PersonRole>  personRoles;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinTable(name="resdb_person_definition")
//    @JoinColumn(name = "person_type_id")
//    private PersonType personType;

//    @OneToOne
//    @JoinColumn(name = "person_definition_id", nullable = true)
//    PersonDefinition personDefinition;

    @Column
    private VersionStatus status;

    @Column
    private String createdBy;

    @Column
    private String updatedBy;

    @Column(name = "version_no")
    private Long versionNumber;

    @Column(name = "last_updated")
    private ZonedDateTime lastUpdated;

    private transient DataOperation operation;

    @Tolerate
    public Person() {

    }
}
