package com.irms.api.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.irms.api.entity.ResourceType;

@Repository
public interface ResourceTypeRepository extends JpaRepository<ResourceType, UUID>{
    Optional<ResourceType> findByName(String name);
}
