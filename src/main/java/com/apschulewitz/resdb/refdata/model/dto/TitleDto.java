package com.apschulewitz.resdb.refdata.model.dto;

import com.apschulewitz.resdb.common.model.entity.TitleType;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Builder
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class TitleDto {

    private Long id;
    private String title;
    private String description;
    private String appliesTo;
    private String titleType;
    private String status;
    private String createdBy;
    private String updatedBy;
    private LocalDateTime lastUpdated;

}
