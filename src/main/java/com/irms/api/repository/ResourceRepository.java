package com.irms.api.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.irms.api.entity.Resource;

@Repository
public interface ResourceRepository extends JpaRepository<Resource, UUID> {

}
