package com.irms.api.dto;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import jakarta.validation.constraints.NotBlank;

public record ResourceCategoryDto(
                @JsonProperty(access = Access.READ_ONLY) UUID id,
                @NotBlank String name) {
}
