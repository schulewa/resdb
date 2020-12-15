package com.apschulewitz.resdb.refdata.model.dto;

import com.apschulewitz.resdb.common.model.dto.AltitudeDto;
import com.apschulewitz.resdb.common.model.dto.LatitudeDto;
import com.apschulewitz.resdb.common.model.dto.LongitudeDto;
import com.apschulewitz.resdb.common.model.dto.RiverDto;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;

@Builder
@Data
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class PlaceDto {

    private Long id;
    private String name;
    private AltitudeDto altitude;
    private LatitudeDto latitude;
    private LongitudeDto longitude;
    private RiverDto river;
    private String status;
    private String createdBy;
    private LocalDateTime lastUpdated;
    private String operation;
}
