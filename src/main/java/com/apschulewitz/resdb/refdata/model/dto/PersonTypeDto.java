package com.apschulewitz.resdb.refdata.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Builder
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class PersonTypeDto {

  private Long id;
  private String name;
  private String status;
  private String createdBy;
  private String updatedBy;
  private LocalDateTime lastUpdated;

}
