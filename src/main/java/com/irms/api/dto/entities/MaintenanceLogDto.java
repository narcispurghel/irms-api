package com.irms.api.dto.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.util.Date;
import java.util.UUID;

public record MaintenanceLogDto(
        @JsonProperty(access = JsonProperty.Access.READ_ONLY)
        UUID id,

        @NotNull
        Date serviceStartDate,

        @NotNull
        Date serviceEndDate,

        @NotBlank
        String problemDescription,

        @NotBlank
        String repairDescription,

        @NotNull @Positive
        Double cost,

        @NotNull
        UUID resourceId) {

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private UUID id;
        private Date serviceStartDate;
        private Date serviceEndDate;
        private String problemDescription;
        private String repairDescription;
        private Double cost;
        private UUID resourceId;

        public Builder id(UUID id) {
            this.id = id;
            return this;
        }

        public Builder serviceStartDate(Date serviceStartDate) {
            this.serviceStartDate = serviceStartDate;
            return this;
        }

        public Builder serviceEndDate(Date serviceEndDate) {
            this.serviceEndDate = serviceEndDate;
            return this;
        }

        public Builder problemDescription(String problemDescription) {
            this.problemDescription = problemDescription;
            return this;
        }

        public Builder repairDescription(String repairDescription) {
            this.repairDescription = repairDescription;
            return this;
        }

        public Builder cost(Double cost) {
            this.cost = cost;
            return this;
        }

        public Builder resourceId(UUID resourceId) {
            this.resourceId = resourceId;
            return this;
        }

        public MaintenanceLogDto build() {
            return new MaintenanceLogDto(
                    id,
                    serviceStartDate,
                    serviceEndDate,
                    problemDescription,
                    repairDescription,
                    cost,
                    resourceId
            );
        }
    }
}

