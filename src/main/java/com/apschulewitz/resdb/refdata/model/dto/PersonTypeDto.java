package com.apschulewitz.resdb.refdata.model.dto;

import com.apschulewitz.resdb.common.model.VersionableDataDto;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;

@Builder
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class PersonTypeDto implements VersionableDataDto<Long> {

  private Long id;
  private String name;
  private String status;
  private String createdBy;
  private String updatedBy;
  private ZonedDateTime lastUpdated;
  private Long versionNumber;

}
