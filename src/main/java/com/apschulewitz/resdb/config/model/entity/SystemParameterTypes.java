package com.apschulewitz.resdb.config.model.entity;

import com.apschulewitz.resdb.common.model.entity.DataType;

public enum SystemParameterTypes {

    PASSWORD_MINIMUM_LENGTH(DataType.INTEGER, "PASSWORD_MINIMUM_LENGTH"),
    PASSWORD_MINIMUM_NUMBERS(DataType.INTEGER, "PASSWORD_MINIMUM_NUMBERS"),
    PASSWORD_MINIMUM_SPECIAL_CHARACTERS(DataType.INTEGER, "PASSWORD_MINIMUM_SPECIAL_CHARACTERS"),
    PASSWORD_DAYS_BEFORE_RESET(DataType.INTEGER, "PASSWORD_DAYS_BEFORE_RESET"),
    //
    MAXIMUM_ALLOWED_LOGIN_ATTEMPTS(DataType.INTEGER, "MAXIMUM_ALLOWED_LOGIN_ATTEMPTS");


    private SystemParameterTypes(DataType dataType, String name) {
        this.dataType = dataType;
        this.name = name;
    }

    private DataType dataType;
    private String name;

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
}
