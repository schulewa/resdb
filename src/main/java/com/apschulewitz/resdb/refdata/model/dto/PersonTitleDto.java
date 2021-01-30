package com.apschulewitz.resdb.refdata.model.dto;

import com.apschulewitz.resdb.common.model.VersionableDataDto;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Data;

import java.time.ZonedDateTime;

@Builder
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class PersonTitleDto implements VersionableDataDto<Long> {

  private Long id;
  private PersonDto person;
  private TitleDto title;
  private Integer position;
  private String status;
  private String createdBy;
  private String updatedBy;
  private ZonedDateTime lastUpdated;
  private Long versionNumber;

}
