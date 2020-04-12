package com.apschulewitz.resdb.security.model.dto;

import java.time.LocalDateTime;

public interface UserAccountProjection {

    Long getId();
    String getLogonName();
    String getFirstName();
    String getFamilyName();
    // AccountStatus getStatus();
    Integer getInvalidAccessCount();
    LocalDateTime getPasswordUpdated();
    LocalDateTime getLAstLogon();

}
