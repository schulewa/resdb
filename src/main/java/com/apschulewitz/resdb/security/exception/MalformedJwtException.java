package com.apschulewitz.resdb.security.exception;

public class MalformedJwtException extends RuntimeException {

    public MalformedJwtException(String message) {
        super(message);
    }
}
