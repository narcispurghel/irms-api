package com.irms.api.controller;

import com.irms.api.dto.entities.ResourceTypeDto;
import com.irms.api.service.ResourceTypeService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

import static com.irms.api.constants.EndpointsConstants.RESOURCE_TYPE_ENDPOINT;

@RestController
@RequestMapping(RESOURCE_TYPE_ENDPOINT)
public class ResourceTypeController {
    private final ResourceTypeService resourceTypeService;
    
    public ResourceTypeController(ResourceTypeService resourceTypeService) {
        this.resourceTypeService = resourceTypeService;
    }
    
    @PostMapping
    public ResponseEntity<ResourceTypeDto> createResourceType(@RequestBody @Valid
                                                              ResourceTypeDto dto) {
        return ResponseEntity.ok(resourceTypeService.createResourceTypeById(dto));
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<ResourceTypeDto> getResourceTypeById(
            @PathVariable(value = "id") UUID id) {
        return ResponseEntity.ok(resourceTypeService.getResourceTypeById(id));
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<ResourceTypeDto> updateResourceTypeById(
            @PathVariable(value = "id") UUID id,
            @RequestBody @Valid ResourceTypeDto resourceTypeDto) {
        return ResponseEntity.ok(resourceTypeService.updateResourceTypeById(id,
                                                                            resourceTypeDto));
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteResourceTypeById(
            @PathVariable(value = "id") UUID id) {
        resourceTypeService.deleteResourceTypeById(id);
        return ResponseEntity.noContent()
                             .build();
    }
}
