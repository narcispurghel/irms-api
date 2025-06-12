package com.irms.api.service;

import com.irms.api.dto.entities.AllocationDto;

import java.util.List;
import java.util.UUID;

public interface AllocationService {
    AllocationDto createAllocation(AllocationDto allocationDto);

    AllocationDto getAllocationById(UUID id);

    AllocationDto updateAllocation(UUID id, AllocationDto allocationDto);

    String deleteAllocationById(UUID id);

    List<AllocationDto> getAllAllocation();
}
