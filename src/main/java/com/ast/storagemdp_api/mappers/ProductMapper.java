package com.ast.storagemdp_api.mappers;

import com.ast.storagemdp_api.dto.ProductDTO;
import com.ast.storagemdp_api.models.CategoryModel;
import com.ast.storagemdp_api.models.ProductModel;

import java.util.Objects;

public class ProductMapper {
    public static ProductDTO toDTO(ProductModel entity){
        if(entity == null){
            return null;
        }

        ProductDTO dto = new ProductDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setPrice(entity.getPrice());
        dto.setQuantity(entity.getQuantity());
        dto.setCategoryId(entity.getCategory() != null ? entity.getCategory().getId() : null);
        dto.setCategoryName(entity.getCategory() != null ? entity.getCategory().getName() : null);
        return dto;
    }

    public static ProductModel toEntity(ProductDTO dto){
        if(dto == null){
            return null;
        }

        ProductModel entity = new ProductModel();
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        entity.setPrice(dto.getPrice());
        entity.setQuantity(dto.getQuantity());

        if (Objects.nonNull(dto.getCategoryId()) || Objects.nonNull(dto.getCategoryName())) {
            CategoryModel category = new CategoryModel();
            if (Objects.nonNull(dto.getCategoryId())) {
                category.setId(dto.getCategoryId());
            }
            if (Objects.nonNull(dto.getCategoryName())) {
                category.setName(dto.getCategoryName());
            }
            entity.setCategory(category);
        }

        return entity;
    }
}
