package com.apschulewitz.resdb.common.api.request;

import lombok.Getter;

@Getter
public enum ApiOperationType {

  ADD("add"), DELETE("delete"), UPDATE("update"), FIND_ALL("find all"), FIND_ALL_ACTIVE("find all active");

  private final String action;

  ApiOperationType(String action) {
    this.action = action;
  }
}
