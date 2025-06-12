package com.irms.api.service;

import com.irms.api.dto.entities.ResourceTypeDto;

import java.util.UUID;

public interface ResourceTypeService {
    ResourceTypeDto createResourceTypeById(ResourceTypeDto dto);
    
    ResourceTypeDto getResourceTypeById(UUID id);
    
    ResourceTypeDto updateResourceTypeById(UUID id, ResourceTypeDto dto);
    
    void deleteResourceTypeById(UUID id);
}
