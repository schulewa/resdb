package com.apschulewitz.resdb.refdata.model.dto;

import com.apschulewitz.resdb.common.model.VersionableDataDto;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.time.ZonedDateTime;

@Builder
@Data
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class CountryDto implements VersionableDataDto<Long> {

  private Long id;
  private String code;
  private String name;
  private String stateName;
  private String sovereignty;
  private ImageDto flagImage;
  private String status;
  private String createdBy;
  private String updatedBy;
  private ZonedDateTime lastUpdated;
  private Long versionNumber;

}
