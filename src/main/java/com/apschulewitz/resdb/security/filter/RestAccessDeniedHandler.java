package com.apschulewitz.resdb.security.filter;

import com.apschulewitz.resdb.security.model.ErrorMessage;
import com.apschulewitz.resdb.security.model.ResponseWrapper;
import com.apschulewitz.resdb.security.model.RestErrorList;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import java.io.IOException;

import static java.util.Collections.singletonMap;
import static javax.servlet.http.HttpServletResponse.SC_FORBIDDEN;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Slf4j
public class RestAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        log.info("Access denied for request " + request);
        RestErrorList errorList = new RestErrorList(SC_FORBIDDEN, new ErrorMessage(accessDeniedException.getMessage()));
        ResponseWrapper responseWrapper = new ResponseWrapper(null, singletonMap("status", SC_FORBIDDEN), errorList);
        ObjectMapper objectMapper = new ObjectMapper();

        final HttpServletResponseWrapper wrapper = new HttpServletResponseWrapper(response);
        wrapper.setStatus(SC_FORBIDDEN);
        wrapper.setContentType(APPLICATION_JSON_VALUE);
        wrapper.getWriter().println(objectMapper.writeValueAsString(responseWrapper));
        wrapper.getWriter().flush();
    }
}
