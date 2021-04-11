package com.apschulewitz.resdb.config.model.entity;

import com.apschulewitz.resdb.common.model.entity.DataType;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public enum SystemParameterTypes {

    PASSWORD_MINIMUM_LENGTH(DataType.INTEGER, "PASSWORD_MINIMUM_LENGTH"),
    PASSWORD_MINIMUM_NUMBERS(DataType.INTEGER, "PASSWORD_MINIMUM_NUMBERS"),
    PASSWORD_MINIMUM_SPECIAL_CHARACTERS(DataType.INTEGER, "PASSWORD_MINIMUM_SPECIAL_CHARACTERS"),
    PASSWORD_DAYS_BEFORE_RESET(DataType.INTEGER, "PASSWORD_DAYS_BEFORE_RESET"),
    //
    MAXIMUM_ALLOWED_LOGIN_ATTEMPTS(DataType.INTEGER, "MAXIMUM_ALLOWED_LOGIN_ATTEMPTS"),
    // user registration process
    SEND_USER_VERIFICATION_EMAIL_WHEN_REGISTERING(DataType.BOOLEAN, "SEND_USER_VERIFICATION_EMAIL_WHEN_REGISTERING");

    SystemParameterTypes(DataType dataType, String name) {
        this.dataType = dataType;
        this.name = name;

        switch (dataType) {
          case BOOLEAN -> booleanTypes.add(name) ;
          case INTEGER -> integerTypes.add(name);
          case STRING -> stringTypes.add(name);
        }
    }

    private DataType dataType;
    private String name;

    private final Set<String> booleanTypes = new HashSet<>();
    private final Set<String> integerTypes = new HashSet<>();
    private final Set<String> stringTypes = new HashSet<>();

    public DataType getDataType() {
        return dataType;
    }

    public void setDataType(DataType dataType) {
        this.dataType = dataType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isBooleanParameterType() {
      return booleanTypes.contains(getName());
    }

    public boolean isIntegerParameterType() {
      return integerTypes.contains(getName());
    }

    public boolean isStringParameterType() {
      return stringTypes.contains(getName());
    }
}
