package com.irms.api.repository;

import com.irms.api.entity.Allocation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AllocationRepository extends JpaRepository<Allocation, UUID> { }
