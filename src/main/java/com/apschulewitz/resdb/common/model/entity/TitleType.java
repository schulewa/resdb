package com.apschulewitz.resdb.common.model.entity;

import lombok.Getter;
import org.springframework.util.StringUtils;

import javax.validation.constraints.NotBlank;
import java.util.Arrays;

@Getter
public enum TitleType {

  Prefix("P"), Suffix("S");

  private String code;

  TitleType(@NotBlank String code) {
    this.code = code;
  }

  public static TitleType getTypeForCode(@NotBlank String code) {
    switch (code) {
      case "P":
        return Prefix;
      case "S":
        return Suffix;
        default:
          throw new IllegalArgumentException("Invalid code for TitleType " + code);
    }
  }

  public static TitleType getTypeFor(String codeOrName) {
    TitleType titleType;
    try {
      titleType = TitleType.valueOf(codeOrName);
    } catch (IllegalArgumentException ex) {
      titleType = TitleType.getTypeForCode(codeOrName);
    }
    if (titleType == null) {
      throw new IllegalArgumentException("Invalid value supplied");
    }
    return titleType;
  }

  public static boolean isValidCode(String code) {
    if (StringUtils.isEmpty(code)) {
      return false;
    }

    return Arrays.stream(values())
      .map(e -> e.getCode())
      .anyMatch(e -> e.equals(code));
  }

  public static boolean isValidName(String name) {
    if (StringUtils.isEmpty(name)) {
      return false;
    }

    return Arrays.stream(values())
      .map(t -> t.name())
      .anyMatch(n -> n.equals(name));
  }
}
