package com.apschulewitz.resdb.security.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ErrorMessage {

    private String message;
    private String code;
    private String detail;

    public ErrorMessage(String message, String code) {
        this(message, code, "");
    }

    public ErrorMessage(String message) {
        this(message, "", "");
    }

}
