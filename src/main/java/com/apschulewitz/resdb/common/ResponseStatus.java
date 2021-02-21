package com.apschulewitz.resdb.common;

/**
 * Created by adrianschulewitz on 22/04/2017.
 */
public enum ResponseStatus {

    // normal responses
    OK, ERROR,
    // authentication responses
    AUTHENTICATED,
    INVALID_CREDENTIALS,
    ACCOUNT_LOCKED,
    PASSWORD_NEEDS_RESET,
    // registration responses
    USER_REGISTRATION_INITIATED,
    USER_REGISTRATION_ACCEPTED,
    USER_REGISTRATION_REJECTED,
    USER_REGISTRATION_NEW_USER_CREATED,
    USER_REGISTRATION_EXCEPTION,
    USER_REGISTRATION_VERIFICATION_EXCEPTION,
    USER_REGISTRATION_VERIFIED,
    USER_REGISTRATION_NEW_VERIFICATION_CODE,
    // data related
    NO_DATA_FOUND;

}
