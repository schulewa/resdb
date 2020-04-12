package com.apschulewitz.resdb.refdata.model.entity;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;
import org.hibernate.envers.Audited;

import javax.persistence.*;

@Data
@Builder
@Entity
@Table(name="resdb_image", uniqueConstraints = @UniqueConstraint(columnNames = {"file_path", "file_name"}))
//@Audited
public class Image {

	private static final long serialVersionUID = -8827113485084905931L;

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

	@Column(name = "file_path", nullable = false, length = 255)
	private String filePath;

	@Column(name = "file_name", nullable = false, length = 100)
	private String fileName;

	@ManyToOne
    @JoinColumn(name = "image_type_id")
	private ImageType imageType;

    @Tolerate
    public Image() {

    }
}
