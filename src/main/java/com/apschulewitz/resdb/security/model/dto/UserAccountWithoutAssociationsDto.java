package com.apschulewitz.resdb.security.model.dto;


import lombok.Builder;
import lombok.ToString;

import java.time.LocalDateTime;

/**
 *
 * @author adrian
 */
@ToString
@Builder
public class UserAccountWithoutAssociationsDto {

    private Long id;

    private String logonName;

    private String firstName;

    private String familyName;

    private String status;

    private Integer invalidAccessCount;

    private LocalDateTime passwordUpdated;

    private LocalDateTime lastLogon;

}
