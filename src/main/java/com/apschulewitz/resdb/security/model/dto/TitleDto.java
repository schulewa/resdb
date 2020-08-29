package com.apschulewitz.resdb.security.model.dto;

import com.apschulewitz.resdb.common.model.entity.Gender;
import com.apschulewitz.resdb.common.model.entity.TitleType;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import java.time.LocalDateTime;

@Builder
@Getter
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class TitleDto {

    private Long id;
    private String title;
    private String description;
    private String appliesTo;
    private TitleType titleType;
    private String status;
    private String createdBy;
    private String updatedBy;
    private LocalDateTime lastUpdated;

}
