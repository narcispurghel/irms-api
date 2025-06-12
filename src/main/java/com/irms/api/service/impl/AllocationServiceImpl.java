package com.irms.api.service.impl;

import ch.qos.logback.core.model.Model;
import com.irms.api.dto.entities.AllocationDto;
import com.irms.api.entity.Allocation;
import com.irms.api.repository.AllocationRepository;
import com.irms.api.service.AllocationService;
import com.irms.api.util.ModelConverter;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

import static com.irms.api.exception.ApiExceptionFactory.notFound;

@Service
public class AllocationServiceImpl  implements AllocationService {
    private final AllocationRepository allocationRepository;

    public AllocationServiceImpl(AllocationRepository allocationRepository) {
        this.allocationRepository = allocationRepository;
    }

    @Override
    public AllocationDto createAllocation(AllocationDto allocationDto) {
        if (allocationDto == null) {
            throw new IllegalArgumentException("Allocation DTO cannot be null");
        }
        Allocation allocation = ModelConverter.toAllocation(allocationDto);
        Allocation savedAllocation = allocationRepository.save(allocation);
        return ModelConverter.toAllocationDto(savedAllocation);
    }

    @Override
    public AllocationDto getAllocationById(UUID id) {
        Allocation allocation = allocationRepository.findById(id)
                .orElseThrow(() -> notFound("Allocation with id " + id + " not found." ));
        return ModelConverter.toAllocationDto(allocation);
    }

    @Override
    public AllocationDto updateAllocation(UUID id, AllocationDto updateAllocation) {
       if (!allocationRepository.existsById(id)) {
           throw notFound("Allocation with id " + id + " not found");
       }
       Allocation allocation = ModelConverter.toAllocation(updateAllocation);
       Allocation saveUpdatedAllocation = allocationRepository.save(allocation);
       return ModelConverter.toAllocationDto(saveUpdatedAllocation);
    }

    @Override
    public String deleteAllocationById(UUID id) {
        if (!allocationRepository.existsById(id)) {
            throw notFound("Allocation with id " + id + " not found");
        }
        allocationRepository.deleteById(id);
        return  "Allocation with id " + id + "  deleted successfully";
    }

    @Override
    public List<AllocationDto> getAllAllocation() {
        List<Allocation> allocations = allocationRepository.findAll();
        if (allocations.isEmpty()) {
            throw notFound("Employees list is empty");
        }
        return allocations.stream()
                .map(ModelConverter::toAllocationDto)
                .toList();
    }
}
