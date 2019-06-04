package com.apschulewitz.resdb.security.model;

/**
 * Created by adrianschulewitz on 05/01/2017.
 */
public enum AuthenticationResult {

    AccountLocked,                  // account is locked
    PasswordRequiresReset,          // password requires resetting (e.g. after registration and before first successful authentication)
    InvalidUserPasswordCombination, // either user or password not set at time of authentication or password does not match
    UnrecognizedUser,               // no user found in the database for the logon name
    TooManyLogonAttempts,           // user has attempted to logon more than the system specified number of attempts
    InactiveAccount,                // account is currently inactive
    SuspendedAccount,               // account has been suspended
    UnauthenticatedUser,            // default status when user attempts to logon
    AuthenticatedUser;              // user has been successfully authenticated
}
