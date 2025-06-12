package com.irms.api.dto.entities;

import com.fasterxml.jackson.annotation.JsonProperty;

import com.irms.api.entity.Employee;
import com.irms.api.entity.Resource;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.UUID;

public record AllocationDto(
        @JsonProperty(access = JsonProperty.Access.READ_ONLY) UUID id,
        @NotNull(message = "Allocation date is required") LocalDate allocationDate,
        @NotNull(message = "Deallocation date is required") LocalDate deallocationDate,
        @NotNull Resource resourceId,
        @NotNull Employee employeeId) {

    public static Builder builder() {return new Builder();}

    public static class Builder {
        private UUID id;
        private LocalDate allocationDate;
        private LocalDate deallocationDate;
        private Resource resourceId;
        private Employee employeeId;

        public Builder id(UUID id) {
            this.id = id;
            return this;
        }

        public Builder allocationDate(LocalDate allocationDate) {
            this.allocationDate = allocationDate;
            return this;
        }

        public Builder deallocationDate(LocalDate deallocationDate) {
            this.deallocationDate = deallocationDate;
            return this;
        }

        public Builder resourceId(Resource resourceId) {
            this.resourceId = resourceId;
            return this;
        }

        public Builder employeeId(Employee employeeId) {
            this.employeeId = employeeId;
            return this;
        }

        public AllocationDto build() {
            return new AllocationDto(
                    id,
                    allocationDate,
                    deallocationDate,
                    resourceId,
                    employeeId);
        }

    }

}
