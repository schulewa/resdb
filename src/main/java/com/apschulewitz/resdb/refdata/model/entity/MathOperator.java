package com.apschulewitz.resdb.refdata.model.entity;

import lombok.Getter;

@Getter
public enum MathOperator {

    ADD("+"), DIVIDE("/"), MULTIPLY("*"), SUBTRACT("-");

    private String operator;

    private MathOperator(String operator) {
        this.operator = operator;
    }
}
