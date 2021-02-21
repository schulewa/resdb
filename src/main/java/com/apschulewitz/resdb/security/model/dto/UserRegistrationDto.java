package com.apschulewitz.resdb.security.model.dto;

import com.apschulewitz.resdb.common.model.VersionableDataDto;
import com.apschulewitz.resdb.refdata.model.dto.LanguageDto;
import com.apschulewitz.resdb.security.model.entity.AccountStatus;
import com.apschulewitz.resdb.security.model.entity.RegistrationStatus;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.Collection;

@Builder
@Data
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserRegistrationDto implements VersionableDataDto<Long> {

    private String createdBy;
    private Long id;
    private String email;
    private ZonedDateTime emailVerified;
    private String firstName;
    private String familyName;
    private ZonedDateTime lastUpdated;
    private String registrationStatus;
    private String status;
    private String updatedBy;
    private String verificationCode;
    private Long versionNumber;

}
