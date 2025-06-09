package com.irms.api.dto;

import java.sql.Date;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import jakarta.validation.Valid;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;

public record ResourceDto(
                @JsonProperty(access = Access.READ_ONLY) UUID id,
                @Min(value = 0) int inventoryNumber,
                @Min(value = 0) int serialNumber,
                @NotBlank String modelSpecification,
                @PastOrPresent Date purchaseDate,
                @DecimalMin(value = "0.0") double price,
                @NotBlank String status,
                @NotBlank String location,
                @NotBlank String availability,
                @Valid @NotNull ResourceTypeDto resourceType) {

        public static Builder builder() {
                return new Builder();
        }

        public static class Builder {
                private UUID id;
                private int serialNumber;
                private int inventoryNumber;
                private String modelSpecification;
                private Date purchaseDate;
                private double price;
                private String status;
                private String location;
                private String availability;
                private ResourceTypeDto resourceType;

                public Builder id(UUID id) {
                        this.id = id;
                        return this;
                }

                public Builder serialNumber(int serialNumber) {
                        this.serialNumber = serialNumber;
                        return this;
                }

                public Builder inventoryNumber(int inventoryNumber) {
                        this.inventoryNumber = inventoryNumber;
                        return this;
                }

                public Builder modelSpecification(String modelSpecification) {
                        this.modelSpecification = modelSpecification;
                        return this;
                }

                public Builder purchaseDate(Date purchaseDate) {
                        this.purchaseDate = purchaseDate;
                        return this;
                }

                public Builder price(double price) {
                        this.price = price;
                        return this;
                }

                public Builder status(String status) {
                        this.status = status;
                        return this;
                }

                public Builder location(String location) {
                        this.location = location;
                        return this;
                }

                public Builder availability(String availability) {
                        this.availability = availability;
                        return this;
                }

                public Builder resourceType(ResourceTypeDto resourceType) {
                        this.resourceType = resourceType;
                        return this;
                }

                public ResourceDto build() {
                        return new ResourceDto(
                                        id,
                                        serialNumber,
                                        inventoryNumber,
                                        modelSpecification,
                                        purchaseDate,
                                        price,
                                        status,
                                        location,
                                        availability,
                                        resourceType);
                }
        }
}
