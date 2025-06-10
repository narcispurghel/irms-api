package com.irms.api.dto.entities;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record ResourceTypeDto(
                @JsonProperty(access = Access.READ_ONLY) UUID id,
                @NotBlank String name,
                @NotBlank @Size(min = 25) String description,
                @NotNull @Valid ResourceCategoryDto resourceCategory) {

}
