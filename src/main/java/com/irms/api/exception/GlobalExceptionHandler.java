package com.irms.api.exception;

import javax.naming.AuthenticationException;

import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.irms.api.dto.response.ApiErrorResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(TypeMismatchException.class)
    public ResponseEntity<ApiErrorResponse> handleApiExceptions(TypeMismatchException e) {
        ApiErrorResponse response = new ApiErrorResponse(
                "Bad request",
                e.getMessage(),
                HttpStatus.BAD_REQUEST.value(),
                "data");
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ApiErrorResponse> handleHttpMessageNotReadableException(HttpMessageNotReadableException e) {
        ApiErrorResponse response = new ApiErrorResponse(
                "Bad request",
                e.getMessage(),
                HttpStatus.BAD_REQUEST.value(),
                "data");
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {AuthenticationException.class })
    public ResponseEntity<ApiErrorResponse> handleApiExceptions(AuthenticationException e) {
        ApiErrorResponse response = new ApiErrorResponse(
                "Bad request",
                e.getMessage(),
                HttpStatus.BAD_REQUEST.value(),
                "data");
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
