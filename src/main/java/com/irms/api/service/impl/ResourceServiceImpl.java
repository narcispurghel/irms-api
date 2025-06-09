package com.irms.api.service.impl;

import static com.irms.api.exception.ApiExceptionFactory.notFound;

import java.util.Objects;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.irms.api.dto.ResourceDto;
import com.irms.api.entity.Resource;
import com.irms.api.entity.ResourceType;
import com.irms.api.exception.ApiExceptionFactory;
import com.irms.api.repository.ResourceRepository;
import com.irms.api.repository.ResourceTypeRepository;
import com.irms.api.service.ResourceService;
import com.irms.api.util.ModelConverter;

@Service
public class ResourceServiceImpl implements ResourceService {
    private final ResourceRepository resourceRepository;
    private final ResourceTypeRepository resourceTypeRepository;

    public ResourceServiceImpl(
            ResourceRepository resourceRepository,
            ResourceTypeRepository resourceTypeRepository) {
        this.resourceRepository = resourceRepository;
        this.resourceTypeRepository = resourceTypeRepository;
    }

    @Override
    @Transactional
    public ResourceDto createResource(ResourceDto resourceDto) {
        Objects.requireNonNull(resourceDto, "resource dto cannot be null");
        Resource resource = ModelConverter.toResource(resourceDto);
        ResourceType resourceType = resourceTypeRepository.findByName(resource.getResourceType().getName())
                .orElseThrow(() -> ApiExceptionFactory.notFound("resource type is not created yet"));
        resource.setResourceType(resourceType);
        return ModelConverter.toResourceDto(resourceRepository.save(resource));
    }

    @Override
    public ResourceDto getResourceById(UUID id) {
        Resource resource = resourceRepository.findById(id)
                .orElseThrow(() -> notFound("Resource with id " + id + " not found"));
        return ModelConverter.toResourceDto(resource);
    }

    @Override
    @Transactional
    public ResourceDto updateResourceById(UUID id, ResourceDto updatedResource) {
        Objects.requireNonNull(updatedResource, "updated resource cannot be null");
        if (!resourceRepository.existsById(id)) {
            throw ApiExceptionFactory.notFound("resource with id " + id + "not found");
        }
        Resource resource = ModelConverter.toResource(updatedResource);
        return ModelConverter.toResourceDto(resourceRepository.save(resource));
    }

    @Override
    @Transactional
    public void deleteResourceById(UUID id) {
        if (!resourceRepository.existsById(id)) {
            throw ApiExceptionFactory.notFound("resource with id " + id + "not found");
        }
        resourceRepository.deleteById(id);
    }
}
