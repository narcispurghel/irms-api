package com.irms.api.service;

import java.util.UUID;

import com.irms.api.dto.ResourceDto;

public interface ResourceService {
    ResourceDto createResource(ResourceDto resourceDto);

    ResourceDto getResourceById(UUID id);

    ResourceDto updateResourceById(UUID id, ResourceDto updatedResource);

    void deleteResourceById(UUID id);
}
