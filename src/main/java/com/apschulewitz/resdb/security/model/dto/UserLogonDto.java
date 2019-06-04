package com.apschulewitz.resdb.security.model.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * <CODE>UserLogonDto</CODE> does not have Lombok annotations as these
 * do not support the combination of builder plus getter methods along
 * with a default constructor. The latter is needed in the server code
 * to automatically convert between JSON and the entity.
 */
//@Builder
//@Data
//@NoArgsConstructor
//@AllArgsConstructor
//@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
/**
 * Use <CODE>UserLogonDto</CODE> in authentication requests.
 */
public class UserLogonDto {

    @JsonCreator
    public UserLogonDto() {
        super();
    }

    @JsonProperty
    private String userName;

    @JsonProperty
    private String password;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
