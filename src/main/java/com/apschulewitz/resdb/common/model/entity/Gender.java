package com.apschulewitz.resdb.common.model.entity;

import lombok.Getter;
import org.springframework.util.StringUtils;

import javax.validation.constraints.NotBlank;

@Getter
public enum Gender {

    Any("A"), Male("M"), Female("F"), Unknown("U");

    private String code;

    Gender(@NotBlank String code) {
        this.code = code;
    }

    public static Gender getGenderFor(@NotBlank String code) {
        switch (code) {
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
}
