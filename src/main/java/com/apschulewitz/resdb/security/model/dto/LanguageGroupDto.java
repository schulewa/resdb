package com.apschulewitz.resdb.security.model.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;

@Builder
@Getter
@ToString
public class LanguageGroupDto {

    private Long id;
    private String name;
    private String status;
    private String createdBy;
    private String updatedBy;
    private LocalDateTime lastUpdated;

}
