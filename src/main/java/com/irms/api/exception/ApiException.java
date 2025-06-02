package com.irms.api.exception;

import org.springframework.http.HttpStatusCode;

public abstract class ApiException extends RuntimeException {
    private final String description;
    private final HttpStatusCode httpStatusCode;
    private final String path;

    protected ApiException(String message, String description, HttpStatusCode httpStatusCode, String path) {
        super(message);
        this.description = description;
        this.httpStatusCode = httpStatusCode;
        this.path = path;
    }

    public String getDescription() {
        return description;
    }

    public HttpStatusCode getHttpStatusCode() {
        return httpStatusCode;
    }

    public String getPath() {
        return path;
    }
}