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
public class LanguageGroupDto implements VersionableDataDto<Long> {

  private Long id;
  private String name;
  private RegionDto region;
  private String status;
  private String createdBy;
  private Long versionNumber;
  private String updatedBy;
  private ZonedDateTime lastUpdated;

}
