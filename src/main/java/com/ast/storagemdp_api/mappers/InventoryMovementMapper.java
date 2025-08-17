package com.ast.storagemdp_api.mappers;

import com.ast.storagemdp_api.dto.InventoryMovementDTO;
import com.ast.storagemdp_api.models.InventoryMovementModel;
import com.ast.storagemdp_api.models.ProductModel;

import java.util.Objects;

public class InventoryMovementMapper {
    public static InventoryMovementDTO toDTO(InventoryMovementModel entity){
        if(entity == null){
          return null;
        }

        InventoryMovementDTO dto = new InventoryMovementDTO();
        dto.setId(entity.getId());
        dto.setType(entity.getType());
        dto.setQuantity(entity.getQuantity());
        dto.setProductId(entity.getProduct() != null ? entity.getProduct().getId() : null);
        dto.setProductName(entity.getProduct() != null ? entity.getProduct().getName() : null);
        dto.setCreatedBy(entity.getCreatedBy());
        dto.setCreatedAt(entity.getCreatedAt());
        dto.setUpdatedAt(entity.getUpdatedAt());
        return dto;
    }

    public static InventoryMovementModel toEntity(InventoryMovementDTO dto) {
        if (dto == null) {
            return null;
        }

        InventoryMovementModel entity = new InventoryMovementModel();
        entity.setId(dto.getId());
        entity.setType(dto.getType());
        entity.setQuantity(dto.getQuantity());
        entity.setCreatedBy(dto.getCreatedBy());

        if (dto.getProductId() != null) {
            ProductModel product = new ProductModel();
            product.setId(dto.getProductId());
            entity.setProduct(product);
        }
        return entity;
    }
}
