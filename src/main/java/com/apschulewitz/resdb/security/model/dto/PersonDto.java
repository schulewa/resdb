package com.apschulewitz.resdb.security.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Builder
@Getter
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class PersonDto {

    private Long id;
    private String firstName;
    private String middleName;
    private String familyName;
    private String gender;

    private HistoricalDateDto dateOfBirth;
    private HistoricalDateDto dateOfDeath;

    private PlaceDto birthPlace;
    private PlaceDto deathPlace;

    private TitleDto prefixTitle;
    private TitleDto suffixTitle;

    private String status;
    private String createdBy;
    private String updatedBy;

}
