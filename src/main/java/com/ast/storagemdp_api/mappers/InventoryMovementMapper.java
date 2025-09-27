package com.ast.storagemdp_api.mappers;

import com.ast.storagemdp_api.dtos.InventoryMovementDTO;
import com.ast.storagemdp_api.models.InventoryModel;
import com.ast.storagemdp_api.models.InventoryMovementModel;
import com.ast.storagemdp_api.models.ProductModel;

public class InventoryMovementMapper {
    public static InventoryMovementDTO toDTO(InventoryMovementModel entity){
        if(entity == null){
          return null;
        }

        InventoryMovementDTO dto = new InventoryMovementDTO();
        dto.setId(entity.getId());
        dto.setType(entity.getType());
        dto.setQuantity(entity.getQuantity());
        dto.setCreatedBy(entity.getCreatedBy());
        dto.setCreatedAt(entity.getCreatedAt());
        dto.setUpdatedAt(entity.getUpdatedAt());

        if (entity.getInventory() != null) {
            dto.setInventoryId(entity.getInventory().getId());

            if (entity.getInventory().getProduct() != null) {
                dto.setProductId(entity.getInventory().getProduct().getId());
                dto.setProductName(entity.getInventory().getProduct().getName());
            }
        }

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

        if (dto.getInventoryId() != null) {
            InventoryModel inventory = new InventoryModel();
            inventory.setId(dto.getInventoryId());
            entity.setInventory(inventory);
        }

        return entity;
    }
}
