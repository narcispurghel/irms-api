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
    @Column(nullable = false)
    private Date serviceStartDate;
    @Column(nullable = false)
    private Date serviceEndDate;
    private String problemDescription;
    private String repairDescription;
    @Column(nullable = false)
    private Double cost;
    @ManyToOne
    @JoinColumn(name = "resource_id")
    private Resource resource;

}
