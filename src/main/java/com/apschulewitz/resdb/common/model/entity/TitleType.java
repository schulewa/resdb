package com.apschulewitz.resdb.common.model.entity;

import lombok.Getter;
import org.springframework.util.StringUtils;

@Getter
public enum TitleType {

  Prefix("P"), Suffix("S");

  private String code;

  TitleType(String code) {
    this.code = code;
  }

  public static TitleType getTypeFor(String code) {
    if (StringUtils.isEmpty(code))
      return null;

    switch (code) {
      case "P":
        return Prefix;
      case "S":
        return Suffix;
        default:
          return null;
    }
  }
}
