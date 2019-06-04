package com.apschulewitz.resdb.security.filter;

import com.apschulewitz.resdb.security.model.ErrorMessage;
import com.apschulewitz.resdb.security.model.ResponseWrapper;
import com.apschulewitz.resdb.security.model.RestErrorList;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import java.io.IOException;

import static javax.servlet.http.HttpServletResponse.SC_UNAUTHORIZED;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

//@Slf4j
public class SecurityAuthenticationEntryPoint implements AuthenticationEntryPoint {

    private Logger log = LoggerFactory.getLogger(SecurityAuthenticationEntryPoint.class);

    @Value("${environment")
    private String environmentName;

    public SecurityAuthenticationEntryPoint() {
        log.info("Starting security authentication for environment " + environmentName);
    }

    @Override
    public void commence(HttpServletRequest request,
                         HttpServletResponse response,
                         AuthenticationException authException) throws IOException, ServletException {
        log.info("Processing request from host " + request.getHeader("host") + " from referrer " + request.getHeader("referer"));
        log.info("Authorization header = " + request.getHeader(AUTHORIZATION));
        RestErrorList errorList = new RestErrorList(SC_UNAUTHORIZED, new ErrorMessage(authException.getMessage()));
        ResponseWrapper responseWrapper = new ResponseWrapper();
        responseWrapper.setErrors(errorList);
//        responseWrapper.setMetadata(singletonMap("status", SC_UNAUTHORIZED));
//        ResponseWrapper responseWrapper = new ResponseWrapper(null, singletonMap("status", SC_UNAUTHORIZED), errorList);
        ObjectMapper objectMapper = new ObjectMapper();

        HttpServletResponseWrapper wrapper = new HttpServletResponseWrapper(response);
        wrapper.setStatus(SC_UNAUTHORIZED);
        wrapper.setContentType(APPLICATION_JSON_VALUE);
        wrapper.getWriter().println(objectMapper.writeValueAsString(responseWrapper));
    }
}
