package com.apschulewitz.resdb.security.exception;

public class JwtBadSignatureException extends RuntimeException {

    public JwtBadSignatureException(String message) {
        super(message);
    }
}
