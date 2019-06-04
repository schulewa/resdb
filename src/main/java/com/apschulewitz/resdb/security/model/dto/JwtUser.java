package com.apschulewitz.resdb.security.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.Date;

@Data
//@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class JwtUser implements Authentication {

    private String username;
    private String password;
    private Date creationDate;
    private Collection<? extends GrantedAuthority> authorities;
    private boolean authenticated;
    private String token;

    public JwtUser() {
        authenticated = false;
    }

    public JwtUser(String username, Date creationDate, Collection<? extends GrantedAuthority> permissions) {
        this.username = username;
        this.creationDate = creationDate;
        this.authorities = permissions;
    }

    @Override
    public Object getCredentials() {
        return password;
    }

    @Override
    public Object getDetails() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return username;
    }

    @Override
    public boolean isAuthenticated() {
        return authenticated;
    }

    @Override
    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
        authenticated = isAuthenticated;
    }

    @Override
    public String getName() {
        return username;
    }
}
