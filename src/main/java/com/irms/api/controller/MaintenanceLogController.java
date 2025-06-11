package com.irms.api.controller;

import com.irms.api.dto.MessageDto;
import com.irms.api.dto.entities.MaintenanceLogDto;
import com.irms.api.service.MaintenanceLogService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

import static com.irms.api.constants.EndpointsConstants.MAINTENANCE_LOG_ENDPOINT;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = MAINTENANCE_LOG_ENDPOINT, produces = APPLICATION_JSON_VALUE)
public class MaintenanceLogController {

    private final MaintenanceLogService maintenanceService;

    public MaintenanceLogController(MaintenanceLogService maintenanceService) {
        this.maintenanceService = maintenanceService;
    }

    @PostMapping
    public ResponseEntity<MaintenanceLogDto> createMaintenanceLog(@RequestBody @Valid MaintenanceLogDto maintenanceLogDto) {
        return ResponseEntity.ok()
                .contentType(APPLICATION_JSON)
                .body(maintenanceService.createMaintenanceLog(maintenanceLogDto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<MaintenanceLogDto> getMaintenanceLogById(@PathVariable(name = "id") UUID id) {
        return ResponseEntity.ok()
                .contentType(APPLICATION_JSON)
                .body(maintenanceService.getMaintenanceLogById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<MaintenanceLogDto> updateMaintenanceLog(
            @PathVariable(name = "id") UUID id,
            @RequestBody @Valid MaintenanceLogDto maintenanceLogDto) {
        return ResponseEntity.ok()
                .contentType(APPLICATION_JSON)
                .body(maintenanceService.updateMaintenanceLog(id, maintenanceLogDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<MessageDto> deleteMaintenanceLogById(@PathVariable(name = "id") UUID id) {
        String message = maintenanceService.deleteMaintenanceLog(id);
        return ResponseEntity.ok()
                .contentType(APPLICATION_JSON)
                .body(new MessageDto(message));
    }

    @GetMapping("/all")
    public ResponseEntity<List<MaintenanceLogDto>> getAllMaintenanceLog() {
        return ResponseEntity.ok()
                .contentType(APPLICATION_JSON)
                .body(maintenanceService.getAllMaintenanceLog());
    }



}
