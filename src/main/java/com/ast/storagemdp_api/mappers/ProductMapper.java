package com.ast.storagemdp_api.mappers;

import com.ast.storagemdp_api.dtos.ProductDTO;
import com.ast.storagemdp_api.models.CategoryModel;
import com.ast.storagemdp_api.models.ProductModel;

public class ProductMapper {
    public static ProductDTO toDTO(ProductModel entity) {
        if (entity == null) {
            return null;
        }

        ProductDTO dto = new ProductDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setBarcode(entity.getBarcode());
        dto.setCategoryId(entity.getCategory() != null ? entity.getCategory().getId() : null);
        dto.setCategoryName(entity.getCategory() != null ? entity.getCategory().getName() : null);
        return dto;
    }

    public static ProductModel toEntity(ProductDTO dto) {
        if (dto == null) {
            return null;
        }

        ProductModel entity = new ProductModel();
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        entity.setBarcode(dto.getBarcode());

        if (dto.getCategoryId() != null) {
            CategoryModel category = new CategoryModel();
            category.setId(dto.getCategoryId());
            entity.setCategory(category);
        }

        return entity;
    }
}
