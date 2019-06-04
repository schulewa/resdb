package com.apschulewitz.resdb.common.model.entity;

import org.springframework.util.StringUtils;

/**
 * Created by adrianschulewitz on 15/08/2016.
 */
public enum MenuOwnerType {

     GROUP("G"), USER("U");

    private String code;

    private MenuOwnerType(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public static MenuOwnerType getTypeFor(String code) {
        if (StringUtils.isEmpty(code))
            return null;

        switch(code) {
            case "G":
                return GROUP;
            case "U":
                return USER;
            default:
                return null;
        }
    }
}
