package com.apschulewitz.resdb.security.model.dto;

import com.apschulewitz.resdb.refdata.model.entity.AccountStatus;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.Collection;

//@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
/**
 * The purpose of <CODE>UserDto</CODE> is to hold user details for sending to the server
 * and returning data. This is not intended for use in authentication. For that use UserLogonDto.
 */
public class UserDto {

//    public UserDto() {}

    @JsonProperty
    private Long id;

    @JsonProperty
    private String logonName;

    @JsonProperty
    private String firstName;

    @JsonProperty
    private String familyName;

    @JsonProperty
    private AccountStatus status;

    @JsonProperty
    private Collection<PermissionDto> permissions;

    @JsonProperty
    private String token;
}
