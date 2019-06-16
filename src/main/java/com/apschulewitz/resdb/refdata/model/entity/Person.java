/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.apschulewitz.resdb.refdata.model.entity;

import com.apschulewitz.resdb.common.model.entity.DataOperation;
import com.apschulewitz.resdb.common.model.entity.HistoricalDate;
import com.apschulewitz.resdb.common.model.entity.Title;
import com.apschulewitz.resdb.common.model.entity.VersionStatus;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;
import org.hibernate.envers.Audited;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

/**
 *
 * @author adrian
 */

@Data
@Builder
@Entity
@Table(name = "resdb_person")
//@Audited
public class Person {

	private static final long serialVersionUID = -2639006949864237790L;

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

	@Column(name = "first_name", nullable = false)
    private String firstName;

	@Column(name = "middle_name")
    private String middleName;

	@Column(name = "family_name", nullable = false)
    private String familyName;

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

	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "prefix_title_id")
    private Title prefixTitle;

	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "suffix_title_id")
    private Title suffixTitle;

	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "birth_place_id")
    private Place birthPlace;

	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "death_place_id")
    private Place deathPlace;

//	@OneToMany(mappedBy = "person_id", cascade = CascadeType.ALL)
//  private List<PersonTitle> titles;

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

  @Version
  @Column(name = "last_updated")
  private LocalDateTime lastUpdated;

  private transient DataOperation operation;

  @Tolerate
  public Person() {

  }
}
