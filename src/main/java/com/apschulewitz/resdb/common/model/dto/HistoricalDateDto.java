package com.apschulewitz.resdb.common.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class HistoricalDateDto {

    private Integer year;
    private Integer month;
    private Integer day;
    private String calendar;

}
