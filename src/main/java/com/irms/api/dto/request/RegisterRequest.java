package com.irms.api.dto.request;

import com.irms.api.enums.RoleType;

public record RegisterRequest(
                String username,
                String firstName,
                String lastName,
                String password,
                RoleType role) {
}
