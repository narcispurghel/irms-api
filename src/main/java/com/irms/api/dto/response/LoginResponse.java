package com.irms.api.dto.response;

import java.util.List;

public record LoginResponse(
        String username,
        List<String> roles,
        String token) {
}
