package com.apschulewitz.resdb.refdata.model.entity;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;
import org.hibernate.envers.Audited;

import javax.persistence.*;

@Data
@Builder
@Entity
@Table(name = "resdb_image_collection", uniqueConstraints = @UniqueConstraint(columnNames = {"name"}))
@Audited
public class ImageCollection {

	private static final long serialVersionUID = -8654066865371078776L;

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

	@Column(nullable = false)
	private String name;

	@Column(name = "number_of_rows", nullable = false)
	private Integer numberOfRows;

	@Column(name = "number_of_columns", nullable = false)
	private Integer numberOfColumns;

    @Tolerate
    public ImageCollection() {

    }
}
