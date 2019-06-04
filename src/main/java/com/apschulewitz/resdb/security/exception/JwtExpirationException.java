package com.apschulewitz.resdb.security.exception;

public class JwtExpirationException extends RuntimeException {

    public JwtExpirationException(String message) {
        super(message);
    }
}
