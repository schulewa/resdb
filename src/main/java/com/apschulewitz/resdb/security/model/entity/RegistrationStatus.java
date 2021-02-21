package com.apschulewitz.resdb.security.model.entity;

import javax.validation.constraints.NotBlank;

public enum RegistrationStatus {

    Initiated("I"),
    Verified("V"),
    Accepted("A"),
    Rejected("R"),
    NewUserCreated("N"),
    Exception("X");

    RegistrationStatus(String code) {
        this.code = code;
    }

    private String code;

    public String getCode() {
        return code;
    }

    public static boolean requiresFurtherProcessing(@NotBlank RegistrationStatus registrationStatus) {
      switch (registrationStatus) {
        case Initiated:
        case Verified:
        case Accepted:
          return true;
      }
      return false;
    }

    public static boolean isValidStatus(@NotBlank String status) {
      return RegistrationStatus.getStatusFor(status) != null;
    }

    public static RegistrationStatus getStatusFor(@NotBlank String code) {
        switch (code) {
            case "A":
                return Accepted;
            case "I":
                return Initiated;
            case "R":
                return Rejected;
            case "N":
                return NewUserCreated;
          case "V":
            return Verified;
            default:
                return Exception;
        }
    }
}
