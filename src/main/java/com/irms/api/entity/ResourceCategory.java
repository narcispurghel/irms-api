package com.irms.api.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "resource_categories")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResourceCategory {
    @Id
    @GeneratedValue
    private UUID id;
    @Column(nullable = false)
    private String name;
}
