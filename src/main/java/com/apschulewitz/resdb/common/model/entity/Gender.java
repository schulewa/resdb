package com.apschulewitz.resdb.common.model.entity;

import lombok.Getter;
import org.springframework.util.StringUtils;

@Getter
public enum Gender {

  Male("M"), Female("F");

  private String code;

  Gender(String code) {
    this.code = code;
  }

  public static Gender getGenderFor(String code) {
    if (StringUtils.isEmpty(code))
      return null;

    switch (code) {
      case "F":
        return Female;
      case "M":
        return Male;
        default:
          return null;
    }
  }
}
