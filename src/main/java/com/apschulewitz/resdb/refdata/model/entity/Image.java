package com.apschulewitz.resdb.refdata.model.entity;

import com.apschulewitz.resdb.common.model.VersionableDataEntity;
import com.apschulewitz.resdb.common.model.entity.VersionStatus;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import java.time.ZonedDateTime;

@Data
@Builder
@Entity
@Table(name="resdb_image", uniqueConstraints = @UniqueConstraint(columnNames = {"file_path", "file_name"}))
public class Image implements VersionableDataEntity<Long> {

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

  @Column
  private VersionStatus status;

  @Column(length = 30)
  private String createdBy;

  @Column(length = 30)
  private String updatedBy;

  @Column(name = "version_no")
  private Long versionNumber;

  @Column(name = "last_updated")
  private ZonedDateTime lastUpdated;

  @Tolerate
    public Image() {

    }
}
