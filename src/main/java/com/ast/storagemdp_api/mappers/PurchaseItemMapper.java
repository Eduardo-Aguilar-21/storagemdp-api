package com.ast.storagemdp_api.mappers;

import com.ast.storagemdp_api.dtos.PurchaseItemDTO;
import com.ast.storagemdp_api.models.PurchaseItemModel;

public class PurchaseItemMapper {

    public static PurchaseItemDTO toDTO(PurchaseItemModel entity) {
        if (entity == null) {
            return null;
        }

        PurchaseItemDTO dto = new PurchaseItemDTO();
        dto.setId(entity.getId());
        dto.setPurchaseId(entity.getPurchase() != null ? entity.getPurchase().getId() : null);
        dto.setProductId(entity.getProduct() != null ? entity.getProduct().getId() : null);
        dto.setProductName(entity.getProduct() != null ? entity.getProduct().getName() : null);
        dto.setQuantity(entity.getQuantity());
        dto.setUnitPrice(entity.getUnitPrice());
        return dto;
    }

    public static PurchaseItemModel toEntity(PurchaseItemDTO dto) {
        if (dto == null) {
            return null;
        }

        PurchaseItemModel entity = new PurchaseItemModel();
        entity.setId(dto.getId());
        entity.setQuantity(dto.getQuantity());
        entity.setUnitPrice(dto.getUnitPrice());

        if (dto.getPurchaseId() != null) {
            entity.setPurchase(new com.ast.storagemdp_api.models.PurchaseModel());
            entity.getPurchase().setId(dto.getPurchaseId());
        }

        if (dto.getProductId() != null) {
            entity.setProduct(new com.ast.storagemdp_api.models.ProductModel());
            entity.getProduct().setId(dto.getProductId());
        }

        return entity;
    }
}
