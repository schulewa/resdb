package com.apschulewitz.resdb.security.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Builder
@Getter
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class HistoricalDateDto {

    private Integer year;
    private Integer month;
    private Integer day;
}
