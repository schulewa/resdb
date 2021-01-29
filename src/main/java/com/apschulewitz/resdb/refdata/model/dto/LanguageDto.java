package com.apschulewitz.resdb.refdata.model.dto;

import com.apschulewitz.resdb.common.model.VersionableDataDto;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.ToString;

import java.time.ZonedDateTime;

@Builder
@Data
@ToString
public class LanguageDto implements VersionableDataDto<Long> {

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
    private String status;
    private String createdBy;
    private ZonedDateTime lastUpdated;
    private String updatedBy;
  private Long versionNumber;
}
