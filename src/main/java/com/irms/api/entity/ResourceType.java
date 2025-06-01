package com.irms.api.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "resource_types")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResourceType {
    @Id
    @GeneratedValue
    private UUID id;
    @Column(nullable = false)
    private String name;
    private String description;
    @ManyToOne
    @JoinColumn(name = "resource_category_id", nullable = false)
    private ResourceCategory resourceCategory;
}
