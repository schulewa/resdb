package com.apschulewitz.resdb.security.model.dto;

import com.apschulewitz.resdb.refdata.model.dto.LanguageDto;
import com.apschulewitz.resdb.refdata.model.entity.AccountStatus;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.Collection;

@Builder
@Getter
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserAccountDto {

    private Long id;
    private String logonName;
    private String firstName;
    private String familyName;
    private AccountStatus status;
    private Integer invalidAccessCount;
    private LocalDateTime passwordUpdated;
    private LocalDateTime lastLogon;
    private LanguageDto preferredLanguage;
    private Boolean templateUser;
    private Collection<UserGroupMembershipDto> groupMemberships;

}
