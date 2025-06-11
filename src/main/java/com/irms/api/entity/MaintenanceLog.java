package com.irms.api.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "maintenance_logs")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MaintenanceLog {
    @Id
    @GeneratedValue
    private UUID id;

    @Column(name = "service_start_date", nullable = false)
    private Date serviceStartDate;

    @Column(name = "service_end_date", nullable = false)
    private Date serviceEndDate;

    @Column(name = "problem_description", nullable = false)
    private String problemDescription;

    @Column(name = "repair_description", nullable = false)
    private String repairDescription;

    @Column(name = "cost", nullable = false)
    private Double cost;

    @ManyToOne
    @JoinColumn(name = "resource_id", nullable = false)
    private Resource resource;

}
