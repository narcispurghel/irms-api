package com.irms.api.util;

import java.util.List;

import com.irms.api.dto.AuthorityDto;
import com.irms.api.dto.UserDto;
import com.irms.api.entity.Authority;
import com.irms.api.entity.User;

public final class ModelConverter {

    private ModelConverter() {
        // Prevent instantiation
    }

    public static AuthorityDto toAuthorityDto(Authority authority) {
        if (authority == null) {
            return null;
        }
        return new AuthorityDto(authority.getRole());
    }

    public static UserDto toUserDto(User user) {
        List<AuthorityDto> authorities = user.getAuthorities()
                .stream()
                .map(ModelConverter::toAuthorityDto)
                .toList();
        return new UserDto(
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getUsername(),
                user.getCreatedAt(),
                user.getUpdatedAt(),
                authorities);
    }
}