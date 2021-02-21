package com.apschulewitz.resdb.security.model.entity;

import com.apschulewitz.resdb.common.model.VersionableDataEntity;
import com.apschulewitz.resdb.common.model.entity.VersionStatus;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;
import lombok.experimental.Tolerate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.ZonedDateTime;

@ToString(exclude = {"groupMemberships", "passwords"})
@Builder
@Data
@Entity
@Table(name = "resdb_user_registration", uniqueConstraints = @UniqueConstraint(columnNames = {"email"}))
public class UserRegistration implements VersionableDataEntity<Long> {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "email", nullable = false, length = 100)
  private String email;

  @Column(name = "verification_code", length = 50)
  private String verificationCode;

  @Column(name = "date_verified")
  private ZonedDateTime emailVerified;

  @Column(name = "first_name", nullable = false, length = 30)
  private String firstName;

  @Column(name = "family_name", nullable = false, length = 50)
  private String familyName;

  @Column(name = "registration_status", nullable = false, length = 1)
  private RegistrationStatus registrationStatus;

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
  public UserRegistration() {

  }
}
