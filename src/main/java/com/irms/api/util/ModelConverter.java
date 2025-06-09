package com.irms.api.util;

import java.util.List;

import com.irms.api.dto.AuthorityDto;
import com.irms.api.dto.ResourceCategoryDto;
import com.irms.api.dto.ResourceDto;
import com.irms.api.dto.ResourceTypeDto;
import com.irms.api.dto.UserDto;
import com.irms.api.entity.Authority;
import com.irms.api.entity.Resource;
import com.irms.api.entity.ResourceCategory;
import com.irms.api.entity.ResourceType;
import com.irms.api.entity.User;

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

}