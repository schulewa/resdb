package com.apschulewitz.resdb.common;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@Slf4j
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

  @ExceptionHandler()
  protected ResponseEntity<Object> handleException(RuntimeException rte, WebRequest request) {
    String bodyOfResponse = "Application specific response";
    log.error(request.toString());
    String message = rte.getCause() != null ? rte.getCause().getMessage() : rte.getMessage();
    log.error("Error exception from processing request: {}", message, rte);
    return handleExceptionInternal(rte, bodyOfResponse, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
  }
}
