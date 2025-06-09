package com.irms.api.dto.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.irms.api.enums.RoleType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record EmployeeDto(
        @JsonProperty(access = JsonProperty.Access.READ_ONLY) UUID id,
        @NotBlank String firstName,
        @NotBlank String secondName,
        @NotBlank @Email String email,
        @NotBlank String department,
        @NotNull RoleType role) {

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private UUID id;
        private String firstName;
        private String secondName;
        private String email;
        private String department;
        private RoleType role;

        public EmployeeDto.Builder id(UUID id) {
            this.id = id;
            return this;
        }

        public Builder firstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public Builder secondName(String secondName) {
            this.secondName = secondName;
            return this;
        }

        public Builder email(String email) {
            this.email = email;
            return this;
        }

        public Builder department(String department) {
            this.department = department;
            return this;
        }

        public Builder role(RoleType role) {
            this.role = role;
            return this;
        }

        public EmployeeDto build() {
            return new EmployeeDto(
                    id,
                    firstName,
                    secondName,
                    email,
                    department,
                    role);
        }
    }
}