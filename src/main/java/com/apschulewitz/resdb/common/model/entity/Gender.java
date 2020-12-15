package com.apschulewitz.resdb.common.model.entity;

import lombok.Getter;
import org.springframework.util.StringUtils;

import javax.validation.constraints.NotBlank;
import java.util.Arrays;
import java.util.Optional;

@Getter
public enum Gender {

    Any("A"), Male("M"), Female("F"), Unknown("U");

    private String code;

    Gender(@NotBlank String code) {
        this.code = code;
    }

    public static Gender getGenderFor(@NotBlank String codeOrName) {
      Optional<Gender> gender = Arrays.stream(values())
        .filter(g -> g.name().equals(codeOrName))
        .findFirst();

      if (gender.isPresent())
        return gender.get();

        switch (codeOrName) {
            case "A":
                return Any;
            case "F":
                return Female;
            case "M":
                return Male;
            default:
                return Unknown;
        }
    }

    public static boolean isValid(String codeOrName) {
      if (StringUtils.isEmpty(codeOrName)) {
        return false;
      }

      boolean isValid = Arrays.stream(values())
        .map(e -> e.getCode())
        .anyMatch(e -> e.equals(codeOrName));

      if (isValid) {
        return true;
      }

      Gender gender = getGenderFor(codeOrName);

      return Gender.Unknown.equals(gender);
    }
}
