package com.irms.api.service.impl;

import com.irms.api.dto.entities.ResourceTypeDto;
import com.irms.api.entity.ResourceType;
import com.irms.api.exception.ApiExceptionFactory;
import com.irms.api.repository.ResourceTypeRepository;
import com.irms.api.service.ResourceTypeService;
import com.irms.api.util.ModelConverter;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ResourceTypeServiceImpl implements ResourceTypeService {
    private final ResourceTypeRepository resourceTypeRepository;
    
    public ResourceTypeServiceImpl(ResourceTypeRepository resourceTypeRepository) {
        this.resourceTypeRepository = resourceTypeRepository;
    }
    
    @Override public ResourceTypeDto createResourceTypeById(ResourceTypeDto dto) {
        ResourceType resourceType =
                resourceTypeRepository.save(ModelConverter.toResource(dto));
        return ModelConverter.toResourceTypeDto(resourceType);
    }
    
    @Override public ResourceTypeDto getResourceTypeById(UUID id) {
        return ModelConverter.toResourceTypeDto(resourceTypeRepository.findById(id)
                                                                      .orElseThrow());
    }
    
    @Override
    public ResourceTypeDto updateResourceTypeById(UUID id, ResourceTypeDto dto) {
        if (resourceTypeRepository.existsById(id)) {
            ResourceType resourceType = ModelConverter.toResource(dto);
            resourceType.setId(id);
            return ModelConverter.toResourceTypeDto(resourceTypeRepository.save(resourceType));
        }
        throw ApiExceptionFactory.notFound("ResourceType not found");
    }
    
    @Override public void deleteResourceTypeById(UUID id) {
        resourceTypeRepository.deleteById(id);
    }
}
