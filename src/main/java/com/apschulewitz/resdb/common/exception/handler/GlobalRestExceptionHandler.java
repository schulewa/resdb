package com.apschulewitz.resdb.common.exception.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.ConversionNotSupportedException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.lang.NonNullApi;
import org.springframework.validation.BindException;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.context.request.async.AsyncRequestTimeoutException;
import org.springframework.web.multipart.support.MissingServletRequestPartException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.constraints.NotNull;
import java.time.ZonedDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

@ControllerAdvice
@Slf4j
public class GlobalRestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Object> handleRuntimeException(RuntimeException rte, WebRequest request) {
        String errMsg = extractErrorMessage(rte);

        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", ZonedDateTime.now());
        body.put("error", errMsg);

        log.error("Error caught processing request [{}]. Error [{}]", request, errMsg, rte);

        return new ResponseEntity<>(body, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex,
                                                                         HttpHeaders headers,
                                                                         HttpStatus status,
                                                                         WebRequest request) {
        String errMsg = extractErrorMessage(ex);

        log.error("Method not supported for processing request [{}], status [{}]. Error [{}]", request, status, errMsg, ex);

        return super.handleHttpRequestMethodNotSupported(ex, headers, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleConversionNotSupported(ConversionNotSupportedException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatus status,
                                                                  WebRequest request) {
        String errMsg = extractErrorMessage(ex);

        log.error("Conversion not supported for processing request [{}], status [{}]. Error [{}]", request, status, errMsg, ex);

        return super.handleConversionNotSupported(ex, headers, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        String errMsg = extractErrorMessage(ex);

        log.error("Message  not readable when processing request [{}], status [{}]. Error [{}]", request, status, errMsg, ex);

        return super.handleHttpMessageNotReadable(ex, headers, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleBindException(BindException ex,
                                                         HttpHeaders headers,
                                                         HttpStatus status,
                                                         WebRequest request) {
        String errMsg = extractErrorMessage(ex);

        log.error("Bind exception when processing request [{}], status [{}]. Error [{}]", request, status, errMsg, ex);

        return super.handleBindException(ex, headers, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMediaTypeNotSupported(HttpMediaTypeNotSupportedException ex,
                                                                     HttpHeaders headers,
                                                                     HttpStatus status,
                                                                     WebRequest request) {
        String errMsg = extractErrorMessage(ex);

        log.error("Media type not supported when processing request [{}], status [{}]. Error [{}]", request, status, errMsg, ex);

        return super.handleHttpMediaTypeNotSupported(ex, headers, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMediaTypeNotAcceptable(HttpMediaTypeNotAcceptableException ex,
                                                                      HttpHeaders headers,
                                                                      HttpStatus status,
                                                                      WebRequest request) {
        String errMsg = extractErrorMessage(ex);

        log.error("Media type not acceptable when processing request [{}], status [{}]. Error [{}]", request, status, errMsg, ex);

        return super.handleHttpMediaTypeNotAcceptable(ex, headers, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleMissingPathVariable(MissingPathVariableException ex,
                                                               HttpHeaders headers,
                                                               HttpStatus status,
                                                               WebRequest request) {
        String errMsg = extractErrorMessage(ex);

        log.error("Missing path variable when processing request [{}], status [{}]. Error [{}]", request, status, errMsg, ex);

        return super.handleMissingPathVariable(ex, headers, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleMissingServletRequestParameter(MissingServletRequestParameterException ex,
                                                                          HttpHeaders headers,
                                                                          HttpStatus status,
                                                                          WebRequest request) {
        String errMsg = extractErrorMessage(ex);

        log.error("Missing servlet request parameter when processing request [{}], status [{}]. Error [{}]", request, status, errMsg, ex);

        return super.handleMissingServletRequestParameter(ex, headers, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleServletRequestBindingException(ServletRequestBindingException ex,
                                                                          HttpHeaders headers,
                                                                          HttpStatus status,
                                                                          WebRequest request) {
        String errMsg = extractErrorMessage(ex);

        log.error("Servlet request binding exception when processing request [{}], status [{}]. Error [{}]", request, status, errMsg, ex);

        return super.handleServletRequestBindingException(ex, headers, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleTypeMismatch(TypeMismatchException ex,
                                                        HttpHeaders headers,
                                                        HttpStatus status,
                                                        WebRequest request) {
        String errMsg = extractErrorMessage(ex);

        log.error("Type mismatch when processing request [{}], status [{}]. Error [{}]", request, status, errMsg, ex);

        return super.handleTypeMismatch(ex, headers, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotWritable(HttpMessageNotWritableException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatus status,
                                                                  WebRequest request) {
        String errMsg = extractErrorMessage(ex);

        log.error("Message not writable when processing request [{}], status [{}]. Error [{}]", request, status, errMsg, ex);

        return super.handleHttpMessageNotWritable(ex, headers, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatus status,
                                                                  WebRequest request) {
        String errMsg = extractErrorMessage(ex);

        log.error("Method argument not valid when processing request [{}], status [{}]. Error [{}]", request, status, errMsg, ex);

        return super.handleMethodArgumentNotValid(ex, headers, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleMissingServletRequestPart(MissingServletRequestPartException ex,
                                                                     HttpHeaders headers,
                                                                     HttpStatus status,
                                                                     WebRequest request) {
        String errMsg = extractErrorMessage(ex);

        log.error("Missing servlet request part when processing request [{}], status [{}]. Error [{}]", request, status, errMsg, ex);

        return super.handleMissingServletRequestPart(ex, headers, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex,
                                                                   HttpHeaders headers,
                                                                   HttpStatus status,
                                                                   WebRequest request) {
        String errMsg = extractErrorMessage(ex);

        log.error("No handler found exception when processing request [{}], status [{}]. Error [{}]", request, status, errMsg, ex);

        return super.handleNoHandlerFoundException(ex, headers, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleAsyncRequestTimeoutException(AsyncRequestTimeoutException ex,
                                                                        HttpHeaders headers,
                                                                        HttpStatus status,
                                                                        WebRequest request) {
        String errMsg = extractErrorMessage(ex);

        log.error("Async request timeout exception when processing request [{}], status [{}]. Error [{}]", request, status, errMsg, ex);

        return super.handleAsyncRequestTimeoutException(ex, headers, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex,
                                                             Object body,
                                                             HttpHeaders headers,
                                                             HttpStatus status,
                                                             WebRequest request) {
        String errMsg = extractErrorMessage(ex);

        log.error("Exception internal when processing request [{}], status [{}]. Error [{}]", request, status, errMsg, ex);

        return super.handleExceptionInternal(ex, body, headers, status, request);
    }

    private String extractErrorMessage(Exception ex) {
        return ex == null ? "<null exception>" : ex.getCause() == null ? ex.getMessage() : ex.getCause().getMessage();
    }
}
