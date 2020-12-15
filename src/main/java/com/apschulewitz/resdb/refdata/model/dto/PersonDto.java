package com.apschulewitz.resdb.refdata.model.dto;

import com.apschulewitz.resdb.common.model.dto.HistoricalDateDto;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@Data
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class PersonDto {

  @JsonProperty
  private Long id;
  @JsonProperty
  private String firstName;
  @JsonProperty
  private String middleName;
  @JsonProperty
  private String familyName;
  @JsonProperty
  private String gender;

  @JsonProperty
  private HistoricalDateDto dateOfBirth;
  @JsonProperty
  private HistoricalDateDto dateOfDeath;

  @JsonProperty
  private PlaceDto birthPlace;
  @JsonProperty
  private PlaceDto deathPlace;

  @JsonProperty
  private TitleDto prefixTitle;
  @JsonProperty
  private TitleDto suffixTitle;

  @JsonProperty
  private List<PersonTitleDto> titles;

  @JsonProperty
  private String status;
  @JsonProperty
  private String createdBy;
  @JsonProperty
  private String updatedBy;
  @JsonProperty
  private LocalDateTime lastUpdated;

}
