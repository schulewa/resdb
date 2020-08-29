package com.apschulewitz.resdb.security.model.dto;

import com.apschulewitz.resdb.common.model.entity.VersionStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;

@Builder
@Getter
@ToString
public class LanguageDto {

    private Long id;
    private String name;
    private String nativeName;
    private String iso6391Code1;
    private String iso6392CodeAlpha2t;
    private String iso6392CodeAlpha2b;
    private String iso6392CodeAlpha3;
    private Boolean deciphered;
    private Boolean living;
    private Boolean constructed;
    private Boolean macroLanguage;
//    private String territory;
    private LanguageGroupDto languageGroup;
    private String notes;
    private VersionStatus status;
    private String createdBy;
    private LocalDateTime lastUpdated;
    private String updatedBy;
}
