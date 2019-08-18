package com.apschulewitz.resdb.common.model.entity;

import javax.validation.constraints.NotBlank;

/**
 * Created by adrianschulewitz on 15/08/2016.
 */
public enum MenuOwnerType {

     GROUP("G"), USER("U");

    private String code;

    MenuOwnerType(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public static MenuOwnerType getTypeFor(@NotBlank String code) {
        switch(code) {
            case "G":
                return GROUP;
            case "U":
                return USER;
            default:
                throw new IllegalArgumentException("Invalid code for MenuOwnerType: " + code);
        }
    }
}
