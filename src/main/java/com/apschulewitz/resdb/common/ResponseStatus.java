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
    // data related
    NO_DATA_FOUND;

}
