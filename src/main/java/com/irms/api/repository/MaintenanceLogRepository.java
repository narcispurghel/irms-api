package com.irms.api.repository;

import com.irms.api.entity.MaintenanceLog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface MaintenanceLogRepository extends JpaRepository<MaintenanceLog, UUID> {
}
