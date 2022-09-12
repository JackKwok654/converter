package com.jack.currency.converter.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.jack.currency.converter.exception.CommonException;
import com.jack.currency.converter.exception.NoContentException;
import com.jack.currency.converter.exception.ResponseFailException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestControllerAdvice extends ResponseEntityExceptionHandler {
    @ExceptionHandler(value = {ResponseFailException.class})
    protected ResponseEntity<Object> handleWebClientApiFail(RuntimeException ex, WebRequest request) {
        return handleExceptionInternal(ex, HttpStatus.SERVICE_UNAVAILABLE.getReasonPhrase(),
                new HttpHeaders(), HttpStatus.SERVICE_UNAVAILABLE, request);
    }

    @ExceptionHandler(value = {JsonProcessingException.class})
    protected ResponseEntity<Object> handleReponsePhasingError(RuntimeException ex, WebRequest request) {
        return handleExceptionInternal(ex, HttpStatus.NOT_EXTENDED.getReasonPhrase(),
                new HttpHeaders(), HttpStatus.NOT_EXTENDED, request);
    }

    @ExceptionHandler(value = {CommonException.class})
    protected ResponseEntity<Object> handleUnknown(RuntimeException ex, WebRequest request) {
        return handleExceptionInternal(ex, HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
                new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
    }

    @ExceptionHandler(value = {NoContentException.class})
    protected ResponseEntity<Object> handleNoContent(RuntimeException ex, WebRequest request) {
        return handleExceptionInternal(ex, HttpStatus.NO_CONTENT.getReasonPhrase(),
                new HttpHeaders(), HttpStatus.NO_CONTENT, request);
    }
}
