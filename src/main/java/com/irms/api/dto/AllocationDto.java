package com.irms.api.dto;

import com.irms.api.entity.Employee;
import com.irms.api.entity.Resource;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@Data
public class AllocationDto {
    private UUID id;
    @NotNull
    @PastOrPresent(message = "Allocation date cannot be in the future")
    private LocalDate allocationDate;
    @NotNull
    @FutureOrPresent(message = "Allocation date cannot be in the past")
    private LocalDate deallocationDate;
    private Resource resource;
    private Employee employee;
}
