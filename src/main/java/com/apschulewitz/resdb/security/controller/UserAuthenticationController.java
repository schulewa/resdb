package com.apschulewitz.resdb.security.controller;

import com.apschulewitz.resdb.common.ApplicationResponse;
import com.apschulewitz.resdb.config.RestUrlPaths;
import com.apschulewitz.resdb.security.UserAuthenticationService;
import com.apschulewitz.resdb.security.model.dto.UserDto;
import com.apschulewitz.resdb.security.model.dto.UserLogonDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Arrays;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600, allowedHeaders = {"x-auth-token", "x-requested-with", "x-xsrf-token"})
@Slf4j
public class UserAuthenticationController {

    private UserAuthenticationService userAuthenticationService;

    @Value("${authentication.expiryInMinutes}")
    private Integer expiryInMinutes;

    @Autowired
    public UserAuthenticationController(UserAuthenticationService userAuthenticationService) {
        this.userAuthenticationService = userAuthenticationService;
    }

    @GetMapping(value = RestUrlPaths.TEST_PAGE_URL)
    public ResponseEntity<String> sayHello() {
        String message = "Hello world at " + LocalDateTime.now();
        log.info("Processing request to say hello: " + message);

        ApplicationResponse<String> applicationResponse = new ApplicationResponse<>();

        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);

        applicationResponse.setData(Arrays.asList(message));

        return ResponseEntity.ok(message);
    }

    @PostMapping(value = RestUrlPaths.TEST_PAGE_WITH_DATA_URL, consumes = MediaType.ALL_VALUE)
    public ResponseEntity<String> sayHelloWithData(@RequestBody String json) {
        String message = "Hello world at " + LocalDateTime.now() + " with json " + json;
        log.info("Processing request to say hello: " + message);

        ApplicationResponse<String> applicationResponse = new ApplicationResponse<>();

        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);

        applicationResponse.setData(Arrays.asList(message));

        return ResponseEntity.ok(message);
    }

    @PostMapping(value = RestUrlPaths.LOGIN_PAGE_URL, consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDto> logon(@RequestBody UserLogonDto userLogonDto) {
        log.info("Processing logon request at {} with userLogonDto={}", RestUrlPaths.LOGIN_PAGE_URL, userLogonDto);
        ApplicationResponse<UserDto> applicationResponse = new ApplicationResponse<>();

        ApplicationResponse authenticationResponse = userAuthenticationService.authenticateUser(userLogonDto.getUserName(), userLogonDto.getPassword());

        HttpStatus httpStatus = HttpStatus.OK;
        UserDto userDto = null;

        switch(authenticationResponse.getResponseStatus()) {
            case AUTHENTICATED:

                log.info("User {} status is {}", userLogonDto.getUserName(), authenticationResponse.getResponseStatus());

                userDto = (UserDto) authenticationResponse.getData().get(0);
                applicationResponse.setData(Arrays.asList(userDto));

                break;

            case ACCOUNT_LOCKED:
            case INVALID_CREDENTIALS:
            case PASSWORD_NEEDS_RESET:

                log.info("User {} status is {}", userLogonDto.getUserName(), authenticationResponse.getResponseStatus());

                httpStatus = HttpStatus.UNAUTHORIZED;
                break;

            default:
                applicationResponse.setResponseStatus(authenticationResponse.getResponseStatus());
                applicationResponse.setMessage(authenticationResponse.getMessage());
                httpStatus = HttpStatus.UNAUTHORIZED;
        }

        return new ResponseEntity<>(userDto, httpStatus);
    }

}
