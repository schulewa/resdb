package com.apschulewitz.resdb.security.controller;

import com.apschulewitz.resdb.common.ResponseStatus;
import com.apschulewitz.resdb.security.service.UserAuthenticationService;
import com.apschulewitz.resdb.security.model.dto.JwtUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class UserAuthenticationProvider implements AuthenticationProvider {

    private UserAuthenticationService authService;

    @Autowired
    public UserAuthenticationProvider(UserAuthenticationService userAuthenticationService) {
        this.authService = userAuthenticationService;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        log.info("Authenticating user {}", authentication.getPrincipal());
        JwtUser user = new JwtUser();
        String userName = (String) authentication.getPrincipal();
        String password = (String) authentication.getCredentials();
        authentication.setAuthenticated(ResponseStatus.OK.equals(authService.authenticateUser(userName, password)));
        return authentication;
    }

    @Override
    public boolean supports(Class<?> authentication) {

        return JwtUser.class.isAssignableFrom(authentication);
    }
}
