package com.ast.storagemdp_api.mappers;

import com.ast.storagemdp_api.dto.CategoryDTO;
import com.ast.storagemdp_api.models.CategoryModel;

public class CategoryMapper {
    public static CategoryDTO toDTO(CategoryModel entity){
        if (entity == null) {
            return null;
        }

        CategoryDTO dto = new CategoryDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setDescription(entity.getDescription());
        return dto;
    }

    public static CategoryModel toEntity(CategoryDTO dto){
        if (dto == null){
            return null;
        }

        CategoryModel entity = new CategoryModel();
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        entity.setDescription(dto.getDescription());
        return entity;
    }
}
