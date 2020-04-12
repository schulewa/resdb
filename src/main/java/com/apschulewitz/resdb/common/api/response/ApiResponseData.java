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
public class ApiResponseData<T> extends ApiResult {

  private final T data;

  public ApiResponseData(
    @JsonProperty T data) {
    this.data = data;
  }

}
