package com.irms.api.constants;

import java.util.List;

public final class EndpointsConstants {
    public static final String RESOURCE_ENDPOINT = "/api/v1/resource";
    public static final String EMPLOYEE_ENDPOINT = "/api/v1/employee";
    public static final String MAINTENANCE_LOG_ENDPOINT = "/api/v1/maintenance-log";
    public static final List<String> PUBLIC_API_ENDPOINTS = List.of(
            "/test",
            "/static/**",
            "/",
            "/error",
            "/favicon.ico",
            "/api/v1/auth/register",
            "/api/v1/auth/login",
            "/api/v1/auth/logout",
            "/v3/api-docs.yaml",
            "/v3/api-docs/**",
            "/swagger-resources/**",
            "/swagger-ui/**",
            "/swagger-ui/index.html",
            "/webjars/**",
            "/swagger-ui/favicon-16x16.png",
            "/swagger-ui/favicon-32x32.png",
            "/swagger-ui/index.css",
            "/swagger-ui/swagger-ui-bundle.js",
            "/swagger-ui/swagger-ui-standalone-preset.js",
            "/swagger-ui/swagger-ui.css",
            "/swagger-ui/swagger-initializer.js",
            "/v3/api-docs/swagger-config",
            RESOURCE_ENDPOINT + "/**");

    private EndpointsConstants() {

    }
}
