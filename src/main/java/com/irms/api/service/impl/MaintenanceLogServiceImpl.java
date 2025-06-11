package com.irms.api.service.impl;

import com.irms.api.dto.entities.MaintenanceLogDto;
import com.irms.api.entity.MaintenanceLog;
import com.irms.api.entity.Resource;
import com.irms.api.repository.MaintenanceLogRepository;
import com.irms.api.repository.ResourceRepository;
import com.irms.api.service.MaintenanceLogService;
import com.irms.api.util.ModelConverter;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

import static com.irms.api.exception.ApiExceptionFactory.notFound;

@Service
public class MaintenanceLogServiceImpl implements MaintenanceLogService {
    private final MaintenanceLogRepository maintenanceRepository;
    private final ResourceRepository resourceRepository;

    public MaintenanceLogServiceImpl(
            MaintenanceLogRepository maintenanceLogRepository,
            ResourceRepository resourceRepository) {
        this.maintenanceRepository = maintenanceLogRepository;
        this.resourceRepository = resourceRepository;
    }

    @Override
    public MaintenanceLogDto createMaintenanceLog(MaintenanceLogDto maintenanceLogDto) {
        Objects.requireNonNull(maintenanceLogDto, "Maintenance log can not be null!");
        Resource resource = resourceRepository.findById(maintenanceLogDto.resourceId())
                .orElseThrow(() -> notFound("Resource doesn't exist or not was created yet."));
        MaintenanceLog maintenanceLog = ModelConverter.toMaintenanceLog(maintenanceLogDto);
        return ModelConverter.toMaintenanceLogDto(maintenanceLog);
    }

    @Override
    public MaintenanceLogDto getMaintenanceLogById(UUID id) {
        MaintenanceLog maintenanceLog = maintenanceRepository.findById(id)
                .orElseThrow(() -> notFound("The maintenance log was not made yet."));
        return ModelConverter.toMaintenanceLogDto(maintenanceLog);
    }

    @Override
    public List<MaintenanceLogDto> getAllMaintenanceLog() {
        List<MaintenanceLog> maintenanceLogs = maintenanceRepository.findAll();
        if(maintenanceLogs.isEmpty()){
            throw notFound("Maintenance log list is empty");
        }
        return maintenanceLogs.stream()
                .map(ModelConverter::toMaintenanceLogDto)
                .toList();
    }

    @Override
    public MaintenanceLogDto updateMaintenanceLog(UUID id, MaintenanceLogDto maintenanceLogDto) {
        Objects.requireNonNull(maintenanceLogDto, "The maintenance can not be null!");
        if (!maintenanceRepository.existsById(id)) {
            throw notFound("The maintenance log with id " + id + " not found");
        }
        MaintenanceLog maintenanceLog = ModelConverter.toMaintenanceLog(maintenanceLogDto);
        return ModelConverter.toMaintenanceLogDto(maintenanceRepository.save(maintenanceLog));
    }

    @Override
    public String deleteMaintenanceLog(UUID id) {
        if (!maintenanceRepository.existsById(id)) {
            throw notFound("Maintenance log with id " + id + " not found");
        }
        maintenanceRepository.deleteById(id);
        return "Maintenance log with ID " + id + " deleted successfully";
    }
}
