package com.irms.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public final class ApiExceptionFactory {

    private ApiExceptionFactory() {

    }

    public static ResponseStatusException internalServerError(Throwable cause) {
        return new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "unexpected error occurred", cause);
    }

    public static ResponseStatusException unauthorized(String reason, Throwable cause) {
        return new ResponseStatusException(HttpStatus.UNAUTHORIZED, reason, cause);
    }

    public static ResponseStatusException unauthorized(String reason) {
        return new ResponseStatusException(HttpStatus.UNAUTHORIZED, reason);
    }

    public static ResponseStatusException notFound(String reason) {
        return new ResponseStatusException(HttpStatus.NOT_FOUND, reason);
    }
}
