package com.irms.api.controller;

import com.irms.api.dto.MessageDto;
import com.irms.api.dto.entities.AllocationDto;
import com.irms.api.entity.Allocation;
import com.irms.api.service.AllocationService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

import static com.irms.api.constants.EndpointsConstants.ALLOCATION_ENDPOINT;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = ALLOCATION_ENDPOINT, produces = APPLICATION_JSON_VALUE)
public class AllocationController {
    private final AllocationService allocationService;

    public AllocationController(AllocationService allocationService) {
        this.allocationService = allocationService;
    }

    @PostMapping
    public ResponseEntity<AllocationDto> createAllocation(AllocationDto allocationDto) {
        return ResponseEntity.ok()
                .contentType(APPLICATION_JSON)
                .body(allocationService.createAllocation(allocationDto));
    }

    @GetMapping("/{id}")
    ResponseEntity<AllocationDto> getAllocationById(@PathVariable(name = "id") UUID id) {
        return ResponseEntity.ok()
                .contentType(APPLICATION_JSON)
                .body(allocationService.getAllocationById(id));
    }

    @PutMapping("/{id}")
    ResponseEntity<AllocationDto> updateAllocationById(
            @PathVariable(name = "id") UUID id,
            @RequestBody @Valid AllocationDto allocationDto) {
        return ResponseEntity.ok()
                .contentType(APPLICATION_JSON)
                .body(allocationService.updateAllocation(id, allocationDto));
    }

    @DeleteMapping("/{id}")
    ResponseEntity<MessageDto> deleteAllocationById(@PathVariable(name = "id") UUID id) {
        String message = allocationService.deleteAllocationById(id);
        return ResponseEntity.ok()
                .contentType(APPLICATION_JSON)
                .body(new MessageDto(message));
    }

}
