package com.irms.api.service;

import com.irms.api.dto.entities.EmployeeDto;
import com.irms.api.dto.entities.MaintenanceLogDto;

import java.util.List;
import java.util.UUID;

public interface MaintenanceLogService {
    MaintenanceLogDto createMaintenanceLog(MaintenanceLogDto maintenanceLogDto);

    MaintenanceLogDto getMaintenanceLogById(UUID id);

    List<MaintenanceLogDto> getAllMaintenanceLog();

    MaintenanceLogDto updateMaintenanceLog(UUID id, MaintenanceLogDto maintenanceLogDto);

    String deleteMaintenanceLog(UUID id);
}
