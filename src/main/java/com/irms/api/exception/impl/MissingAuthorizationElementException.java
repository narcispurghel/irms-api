package com.irms.api.exception.impl;

import com.irms.api.exception.ApiException;
import org.springframework.http.HttpStatusCode;

public class MissingAuthorizationElementException extends ApiException {

    public MissingAuthorizationElementException(String message, String description, HttpStatusCode httpStatusCode, String path) {
        super(message, description, httpStatusCode, path);
    }
}
