package com.apschulewitz.resdb.common.model.entity;

import lombok.Getter;
import org.springframework.util.StringUtils;

import javax.validation.constraints.NotBlank;

@Getter
public enum TitleType {

  Prefix("P"), Suffix("S");

  private String code;

  TitleType(@NotBlank String code) {
    this.code = code;
  }

  public static TitleType getTypeFor(@NotBlank String code) {
    switch (code) {
      case "P":
        return Prefix;
      case "S":
        return Suffix;
        default:
          throw new IllegalArgumentException("Invalid code for TitleType " + code);
    }
  }
}
