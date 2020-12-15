package com.apschulewitz.resdb.common.model.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RiverDto {

  private Long id;
  private String name;

}
