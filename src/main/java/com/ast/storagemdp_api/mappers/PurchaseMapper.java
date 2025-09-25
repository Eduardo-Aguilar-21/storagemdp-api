package com.ast.storagemdp_api.mappers;

import com.ast.storagemdp_api.dtos.PurchaseDTO;
import com.ast.storagemdp_api.models.PurchaseModel;

import java.util.stream.Collectors;

public class PurchaseMapper {

    public static PurchaseDTO toDTO(PurchaseModel entity) {
        if (entity == null) {
            return null;
        }

        PurchaseDTO dto = new PurchaseDTO();
        dto.setId(entity.getId());
        dto.setSupplierId(entity.getSupplier() != null ? entity.getSupplier().getId() : null);
        dto.setSupplierName(entity.getSupplier() != null ? entity.getSupplier().getName() : null);
        dto.setPurchaseDate(entity.getPurchaseDate());
        dto.setTotalAmount(entity.getTotalAmount());
        dto.setInvoiceNumber(entity.getInvoiceNumber());
        dto.setNotes(entity.getNotes());
        dto.setPaid(entity.getPaid());

        if (entity.getItems() != null) {
            dto.setItems(entity.getItems()
                    .stream()
                    .map(PurchaseItemMapper::toDTO)
                    .collect(Collectors.toList()));
        }

        dto.setCreatedAt(entity.getCreatedAt());
        dto.setUpdatedAt(entity.getUpdatedAt());

        return dto;
    }

    public static PurchaseModel toEntity(PurchaseDTO dto) {
        if (dto == null) {
            return null;
        }

        PurchaseModel entity = new PurchaseModel();
        entity.setId(dto.getId());
        entity.setPurchaseDate(dto.getPurchaseDate());
        entity.setTotalAmount(dto.getTotalAmount());
        entity.setInvoiceNumber(dto.getInvoiceNumber());
        entity.setNotes(dto.getNotes());
        entity.setPaid(dto.getPaid());

        if (dto.getSupplierId() != null) {
            entity.setSupplier(new com.ast.storagemdp_api.models.SupplierModel());
            entity.getSupplier().setId(dto.getSupplierId());
        }

        if (dto.getItems() != null) {
            entity.setItems(dto.getItems()
                    .stream()
                    .map(PurchaseItemMapper::toEntity)
                    .collect(Collectors.toList()));
        }

        return entity;
    }
}
