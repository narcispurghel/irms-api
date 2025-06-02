package com.irms.api.dto.response;

public record ApiErrorResponse(String message, String description, int status, String path) {

}
