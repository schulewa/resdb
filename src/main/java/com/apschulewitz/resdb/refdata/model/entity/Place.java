/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.apschulewitz.resdb.refdata.model.entity;

import com.apschulewitz.resdb.common.model.entity.Altitude;
import com.apschulewitz.resdb.common.model.entity.Latitude;
import com.apschulewitz.resdb.common.model.entity.Longitude;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;
import org.hibernate.envers.Audited;

import javax.persistence.*;

/**
 *
 * @author adrian
 */

@Data
@Builder
@Entity
@Table(name = "resdb_place")
@Audited
public class Place {

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

	@ManyToOne(fetch = FetchType.LAZY)
    private River river;

    @Tolerate
    public Place() {

    }
}
