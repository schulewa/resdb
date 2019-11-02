package com.apschulewitz.resdb.common.api.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.ToString;
import net.jcip.annotations.Immutable;

@Immutable
@Getter
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponseError extends ApiResult {
  private final String code;
  private final String message;

  public ApiResponseError(
    @JsonProperty String code,
    @JsonProperty String message) {
    this.code = code;
    this.message = message;
  }

}
