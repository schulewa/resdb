package com.apschulewitz.resdb.refdata.model.entity;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;
import org.hibernate.envers.Audited;

import javax.persistence.*;

@Data
@Builder
@Entity
@Table(name = "resdb_deity_alias")
//@Audited
public class DeityAlias {

	private static final long serialVersionUID = 4847388893750200873L;

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "from_deity_id")
	private Deity fromDeity;

	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "to_alias_id")
	private Deity toDeity;

    @Tolerate
    public DeityAlias() {

    }
}
