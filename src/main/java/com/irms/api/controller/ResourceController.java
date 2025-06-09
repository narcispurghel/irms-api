package com.irms.api.controller;

import static com.irms.api.constants.EndpointsConstants.RESOURCE_ENDPOINT;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.irms.api.dto.entities.ResourceDto;
import com.irms.api.service.ResourceService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping(value = RESOURCE_ENDPOINT, produces = APPLICATION_JSON_VALUE)
public class ResourceController {
    private final ResourceService resourceService;

    public ResourceController(ResourceService resourceService) {
        this.resourceService = resourceService;
    }

    @PostMapping
    public ResponseEntity<ResourceDto> createResource(@RequestBody @Valid ResourceDto resource) {
        return ResponseEntity.status(200)
                .contentType(APPLICATION_JSON)
                .body(resourceService.createResource(resource));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResourceDto> getResourceById(@PathVariable(name = "id") UUID id) {
        return ResponseEntity.status(200)
                .contentType(APPLICATION_JSON)
                .body(resourceService.getResourceById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResourceDto> updateResource(
            @PathVariable(name = "id") UUID id,
            @RequestBody @Valid ResourceDto resource) {
        return ResponseEntity.status(200)
                .contentType(APPLICATION_JSON)
                .body(resourceService.updateResourceById(id, resource));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteResourceById(@PathVariable(name = "id") UUID id) {
        return ResponseEntity.status(204)
                .contentType(APPLICATION_JSON)
                .build();
    }
}
