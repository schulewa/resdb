package com.apschulewitz.resdb.refdata.model.entity;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;
import org.hibernate.envers.Audited;

import javax.persistence.*;

@Data
@Builder
@Entity
@Table(name = "resdb_measure_converter", uniqueConstraints = @UniqueConstraint(columnNames = {"name"}))
//@Audited
//@NamedEntityGraph(name = "graph.MeasureConverter.all",
//					attributeNodes = {
//							@NamedAttributeNode("id"),
//							@NamedAttributeNode("name"),
//							@NamedAttributeNode("fromMeasure"),
//							@NamedAttributeNode("fromQuantity"),
//							@NamedAttributeNode("toMeasure"),
//							@NamedAttributeNode("toQuantity")
//					})
public class MeasureConverter {

	private static final long serialVersionUID = 6011035349397597286L;

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

	@Column(nullable = false)
	private String name;

	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "from_measure_id")
	private Measure fromMeasure;

	@Column(name = "from_quantity", nullable = false)
	private Double fromQuantity;

	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "to_measure_id")
	private Measure toMeasure;

	@Column(name = "to_quantity", nullable = false)
	private Double toQuantity;

    @Tolerate
    public MeasureConverter() {
		super();
    }
}
