package com.apschulewitz.resdb.security.model.entity;

import javax.validation.constraints.NotNull;

public enum UserRegistrationType {
  AcademicCollaboration("AC"), InterestedAcademic("IA"), InterestedStudent("IS"), Volunteer("V");

  UserRegistrationType(String code) {
    this.code = code;
  }

  public String getCode() {
    return code;
  }

  private String code;

  public static UserRegistrationType getInstance(@NotNull String code) {
    switch (code) {
      case "AC":
        return AcademicCollaboration;
      case "IA":
        return InterestedAcademic;
      case "IS":
        return InterestedStudent;
      case "V":
        return Volunteer;
      default:
        throw new IllegalArgumentException("Unrecognized code for a user registration type: " + code);
    }
  }
}
