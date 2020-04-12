package com.apschulewitz.resdb.security.model.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Builder
@Getter
@ToString
public class LanguageDto {

    private Long id;
    private String iso6391Code;
    private String iso6392CodeAlpha3B;
    private String iso6392CodeAlpha3t;
    private String iso6392CodeAlpha2;
    private String name;
    private String territory;
    private LanguageGroupDto languageGroup;

}
