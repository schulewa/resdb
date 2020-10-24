package com.apschulewitz.resdb.refdata.model.entity;

import javax.validation.constraints.NotBlank;

public enum AccountStatus {

    Unknown("X"),
    Active("A"),
    Inactive("I"),
    Locked("L"),
    PasswordNeedsResetting("R"),
    Suspended("S");

    AccountStatus(String code) {
        this.code = code;
    }

    private String code;

    public String getCode() {
        return code;
    }

    public static boolean isActiveStatus(@NotBlank AccountStatus accountStatus) {
      switch (accountStatus) {
        case Inactive:
          return false;
      }
      return true;
    }

    public static AccountStatus getStatusFor(@NotBlank String code) {
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
