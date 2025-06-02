package com.irms.api.entity;

import jakarta.persistence.*;
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
    private LocalDate allocationDate;
    @Column(nullable = false)
    private LocalDate deallocationDate;
    @ManyToOne
    @JoinColumn(name = "resource_id")
    private Resource resource;
    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;
}
