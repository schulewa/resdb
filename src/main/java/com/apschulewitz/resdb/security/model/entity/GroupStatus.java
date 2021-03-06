package com.apschulewitz.resdb.security.model.entity;

import org.springframework.util.StringUtils;

public enum GroupStatus {

    Unknown("X"),
    Active("A"),
    Inactive("I"),
    Locked("L"),
    PasswordNeedsResetting("R"),
    Suspended("S");

    private GroupStatus(String code) {
        this.code = code;
    }

    private String code;

    public String getCode() {
        return code;
    }

    public static GroupStatus getStatusFor(String code) {
        if (StringUtils.isEmpty(code))
            return Unknown;

        switch (code) {
            case "A":
                return Active;
            case "I":
                return Inactive;
            case "L":
                return Locked;
            case "R":
                return PasswordNeedsResetting;
            case "S":
                return Suspended;
            default:
                return Unknown;
        }
    }
}
