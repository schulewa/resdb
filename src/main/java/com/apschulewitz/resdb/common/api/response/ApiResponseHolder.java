package com.apschulewitz.resdb.common.api.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import net.jcip.annotations.Immutable;

@Immutable
public class ApiResponseHolder implements ApiResponse {

  private final ApiResult result;

  public ApiResponseHolder(
    @JsonProperty ApiResult result) {
    this.result = result;
  }

  @Override
  public ApiResult getResult() {
    return result;
  }
}
