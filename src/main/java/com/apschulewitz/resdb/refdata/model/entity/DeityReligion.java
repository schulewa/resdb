package com.apschulewitz.resdb.refdata.model.entity;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;
import org.hibernate.envers.Audited;

import javax.persistence.*;

@Data
@Builder
@Entity
@Table(name = "resdb_deity_religion")
//@Audited
public class DeityReligion {

	private static final long serialVersionUID = 18814218276258561L;

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
	private Deity deity;

	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "religion_id")
	private Religion religion;

    @Tolerate
    public DeityReligion() {

    }
}
