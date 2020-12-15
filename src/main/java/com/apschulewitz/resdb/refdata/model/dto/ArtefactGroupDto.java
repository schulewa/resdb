package com.apschulewitz.resdb.refdata.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.time.LocalDateTime;

@Builder
@Data
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class ArtefactGroupDto {

  private Long id;
  private String name;
  private String status;
  private String createdBy;
  private String updatedBy;
  private LocalDateTime lastUpdated;

}
