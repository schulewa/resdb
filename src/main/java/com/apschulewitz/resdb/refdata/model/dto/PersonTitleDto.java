package com.apschulewitz.resdb.refdata.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class PersonTitleDto {

  private Long id;
  private PersonDto person;
  private TitleDto title;
  private Integer position;

}
