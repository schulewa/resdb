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
 * The purpose of <CODE>UserDto</CODE> is to hold details for an authenticated user along with their permissions. and is
 * It is sent back to the server.
 * UserLogonDto holds the details of a user who needs to be authenticated.
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
