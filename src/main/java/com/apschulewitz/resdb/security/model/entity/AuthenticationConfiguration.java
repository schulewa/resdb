package com.apschulewitz.resdb.security.model.entity;

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
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.ZonedDateTime;

/**
 *
 * @author adrian
 */
@Data
@Builder
@Entity
@Table(name = "resdb_auth_config")
public class AuthenticationConfiguration implements VersionableDataEntity<Long> {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column
  private Integer minimumPasswordLength;

  @Column
  private Integer maximumPasswordLength;

  @Column
  private Integer minimumLowercaseCharacters;

  @Column
  private Integer minimumUppercaseCharacters;

  @Column
  private Integer minimumNumberCharacters;

  @Column
  private Integer minimumSpecialCharacters;

  @Column
  private Integer maximumPasswordAgeInDays;

  @Column
  @NotNull
  private VersionStatus status;

  @Column
  @NotBlank
  private String createdBy;

  @Column
  private String updatedBy;

  @Column(name = "version_no")
  private Long versionNumber;

  @Column(name = "last_updated")
  private ZonedDateTime lastUpdated;

  @Tolerate
  public AuthenticationConfiguration() {

  }
}
