package com.irms.api.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "allocations")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Allocation {
    @Id
    @GeneratedValue
    private UUID id;

    @Column(nullable = false)
    @NotNull(message = "Allocation date is required")
    @PastOrPresent(message = "Allocation date cannot be in the future")
    private LocalDate allocationDate;

    @Column(nullable = false)
    @NotNull(message = "Deallocation date is required")
    private LocalDate deallocationDate;

    @ManyToOne
    @JoinColumn(name = "resource_id")
    private Resource resource;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;
}
