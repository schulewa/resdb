package com.apschulewitz.resdb.security.model.dto;

import com.apschulewitz.resdb.common.model.VersionableDataDto;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.ZonedDateTime;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
/**
 * The purpose of <CODE>AuthenticationConfigurationDto</CODE> is to hold details for the authentication configuration,
 * for use by administrators configuring the authentication rules.
 * AuthenticationConfigurationDto holds the details of the system authentication configuration.
 */
public class AuthenticationConfigurationDto implements VersionableDataDto<Long> {

  @JsonProperty
  private Long id;

  @JsonProperty
  private Integer minimumPasswordLength;

  @JsonProperty
  private Integer maximumPasswordLength;

  @JsonProperty
  private Integer minimumLowercaseCharacters;

  @JsonProperty
  private Integer minimumUppercaseCharacters;

  @JsonProperty
  private Integer minimumNumberCharacters;

  @JsonProperty
  private Integer minimumSpecialCharacters;

  @JsonProperty
  private Integer maximumPasswordAgeInDays;

  @JsonProperty
  private String status;

  @JsonProperty
  private String createdBy;

  @JsonProperty
  private String updatedBy;

  @JsonProperty
  private Long versionNumber;

  @JsonProperty
  private ZonedDateTime lastUpdated;
}
