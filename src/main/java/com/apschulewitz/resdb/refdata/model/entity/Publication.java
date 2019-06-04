package com.apschulewitz.resdb.refdata.model.entity;

import com.apschulewitz.resdb.common.model.entity.HistoricalDate;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;
import org.hibernate.envers.Audited;

import javax.persistence.*;

@Data
@Builder
@Entity
@Table(name = "resdb_publication")
//@Audited
public class Publication {

	private static final long serialVersionUID = -3453453187746150787L;

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

	@Column(nullable = false, length = 75)
	private String title;

	@Column(nullable = false, length = 20)
	private String isbn;

    @OneToOne()
    @JoinColumn(name = "author_person_id", nullable = true)
    private Person primaryAuthor;

	@ManyToOne(fetch = FetchType.LAZY)
//	@Column(name = "publication_type_id", nullable = false)
    @JoinColumn(name = "publication_type_id")
	private PublicationType publicationType;

	@Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "year", column = @Column(name = "published_year", nullable = true)),
            @AttributeOverride(name = "month", column = @Column(name = "published_month", nullable = true)),
            @AttributeOverride(name = "day", column = @Column(name = "published_day", nullable = true))
    })
	private HistoricalDate datePublished;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="publisher_entity_id")
	private EntityInstance publisher;

//	@Column(nullable = false)
//	private Boolean primary;

	@Column(nullable = true, length = 250)
	private String keywords;

	@Column(nullable = true, length = 250)
	private String assessment;

    @Tolerate
    public Publication() {

    }
}
