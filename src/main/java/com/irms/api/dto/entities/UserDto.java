package com.irms.api.dto.entities;

import com.irms.api.dto.AuthorityDto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public record UserDto(
        UUID id,
        String firstName,
        String lastName,
        String username,
        LocalDateTime updatedAt,
        LocalDateTime createdAt,
        List<AuthorityDto> authorities) {

}
