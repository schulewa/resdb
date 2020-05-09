package com.apschulewitz.resdb.common.model.entity;

import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
public enum YesNoChoice {

    Yes("Y"), No("N"), Unknown("U");

    private String code;

    YesNoChoice(@NotBlank String code) {
        this.code = code;
    }

    public static YesNoChoice getChoiceFor(@NotBlank String code) {
        switch (code) {
            case "N":
                return No;
            case "Y":
                return Yes;
            default:
                return Unknown;
        }
    }
}
