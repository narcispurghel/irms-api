package com.irms.api.util;

import java.util.List;
import java.util.UUID;

import com.irms.api.dto.AuthorityDto;
import com.irms.api.dto.entities.*;
import com.irms.api.entity.*;

public final class ModelConverter {

    private ModelConverter() {
        // Prevent instantiation
    }

    public static AuthorityDto toAuthorityDto(Authority authority) {
        if (authority == null) {
            return null;
        }
        return new AuthorityDto(authority.getRole());
    }

    public static UserDto toUserDto(User user) {
        List<AuthorityDto> authorities = user.getAuthorities()
                .stream()
                .map(ModelConverter::toAuthorityDto)
                .toList();
        return new UserDto(
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getUsername(),
                user.getCreatedAt(),
                user.getUpdatedAt(),
                authorities);
    }

    public static ResourceDto toResourceDto(Resource resource) {
        if (resource == null) {
            return null;
        }
        ResourceTypeDto resourceTypeDto = toResourceTypeDto(resource.getResourceType());
        return ResourceDto.builder()
                .id(resource.getId())
                .inventoryNumber(resource.getInventoryNumber())
                .modelSpecification(resource.getModelSpecification())
                .purchaseDate(resource.getPurchaseDate())
                .price(resource.getPrice())
                .status(resource.getStatus())
                .location(resource.getLocation())
                .availability(resource.getAvailability())
                .resourceType(resourceTypeDto)
                .build();
    }

    private static ResourceTypeDto toResourceTypeDto(ResourceType resourceType) {
        if (resourceType == null) {
            return null;
        }
        ResourceCategoryDto resourceCategoryDto = toResourceCategoryDto(resourceType.getResourceCategory());
        return new ResourceTypeDto(
                resourceType.getId(),
                resourceType.getName(),
                resourceType.getDescription(),
                resourceCategoryDto);
    }

    private static ResourceCategoryDto toResourceCategoryDto(ResourceCategory resourceCategory) {
        if (resourceCategory == null) {
            return null;
        }
        return new ResourceCategoryDto(
                resourceCategory.getId(),
                resourceCategory.getName());
    }

    public static EmployeeDto toEmployeeDto(Employee employee) {
        if (employee == null) {
            return null;
        }
        return EmployeeDto.builder()
                .id(employee.getId())
                .firstName(employee.getFirstName())
                .secondName(employee.getSecondName())
                .email(employee.getEmail())
                .department(employee.getDepartment())
                .role(employee.getRole())
                .build();
    }

    public static MaintenanceLogDto toMaintenanceLogDto(MaintenanceLog maintenanceLog) {
        if (maintenanceLog == null) {
            return  null;
        }
        return MaintenanceLogDto.builder()
                .id(maintenanceLog.getId())
                .serviceStartDate(maintenanceLog.getServiceStartDate())
                .serviceEndDate(maintenanceLog.getServiceEndDate())
                .problemDescription(maintenanceLog.getProblemDescription())
                .repairDescription(maintenanceLog.getRepairDescription())
                .cost(maintenanceLog.getCost())
                .resourceId(maintenanceLog.getId())
                .build();
    }

    public static Resource toResource(ResourceDto dto) {
        if (dto == null) {
            return null;
        }
        Resource resource = new Resource();
        resource.setId(dto.id());
        resource.setSerialNumber(dto.serialNumber());
        resource.setInventoryNumber(dto.inventoryNumber());
        resource.setModelSpecification(dto.modelSpecification());
        resource.setPurchaseDate(dto.purchaseDate());
        resource.setPrice(dto.price());
        resource.setStatus(dto.status());
        resource.setLocation(dto.location());
        resource.setAvailability(dto.availability());
        resource.setResourceType(toResourceType(dto.resourceType()));
        return resource;
    }

    public static Resource toResourceId(UUID id) {
        if (id == null) return null;
        Resource resource = new Resource();
        resource.setId(id);
        return resource;
    }

    private static ResourceType toResourceType(ResourceTypeDto dto) {
        if (dto == null) {
            return null;
        }
        ResourceType type = new ResourceType();
        type.setId(dto.id());
        type.setName(dto.name());
        type.setDescription(dto.description());
        type.setResourceCategory(toResourceCategory(dto.resourceCategory()));
        return type;
    }

    private static ResourceCategory toResourceCategory(ResourceCategoryDto dto) {
        if (dto == null) {
            return null;
        }
        ResourceCategory category = new ResourceCategory();
        category.setId(dto.id());
        category.setName(dto.name());
        return category;
    }

    public static Employee toEmployee(EmployeeDto dto) {
        if (dto == null) {
            return null;
        }
        Employee employee = new Employee();
        employee.setId(dto.id());
        employee.setFirstName(dto.firstName());
        employee.setSecondName(dto.secondName());
        employee.setEmail(dto.email());
        employee.setDepartment(dto.department());
        employee.setRole(dto.role());
        return employee;
    }

    public static MaintenanceLog toMaintenanceLog(MaintenanceLogDto dto) {
        if (dto == null) {
            return null;
        }
        MaintenanceLog maintenance = new MaintenanceLog();
        maintenance.setId(dto.id());
        maintenance.setServiceStartDate(dto.serviceStartDate());
        maintenance.setServiceEndDate(dto.serviceEndDate());
        maintenance.setProblemDescription(dto.problemDescription());
        maintenance.setRepairDescription(dto.repairDescription());
        maintenance.setCost(dto.cost());
        maintenance.setResource(toResourceId(dto.resourceId()));
        return  maintenance;
    }

}