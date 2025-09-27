package com.ast.storagemdp_api.mappers;

import com.ast.storagemdp_api.dtos.InventoryDTO;
import com.ast.storagemdp_api.models.*;
import org.springframework.stereotype.Component;

@Component
public class InventoryMapper {

    public InventoryDTO toDTO(InventoryModel entity) {
        if (entity == null) {
            return null;
        }

        InventoryDTO dto = new InventoryDTO();
        dto.setId(entity.getId());
        dto.setPrice(entity.getPrice());
        dto.setQuantity(entity.getQuantity());

        if (entity.getProduct() != null) {
            dto.setProductId(entity.getProduct().getId());
            dto.setProductName(entity.getProduct().getName());
            dto.setProductBarcode(entity.getProduct().getBarcode());
        }

        if (entity.getCompany() != null) {
            dto.setCompanyId(entity.getCompany().getId());
            dto.setCompanyName(entity.getCompany().getName());
        }

        if (entity.getBranch() != null) {
            dto.setBranchId(entity.getBranch().getId());
            dto.setBranchName(entity.getBranch().getName());
        }

        return dto;
    }

    public InventoryModel toEntity(InventoryDTO dto) {
        if (dto == null) {
            return null;
        }

        InventoryModel entity = new InventoryModel();
        entity.setId(dto.getId());
        entity.setPrice(dto.getPrice());
        entity.setQuantity(dto.getQuantity());

        if (dto.getProductId() != null) {
            ProductModel product = new ProductModel();
            product.setId(dto.getProductId());
            entity.setProduct(product);
        }

        if (dto.getCompanyId() != null) {
            CompanyModel company = new CompanyModel();
            company.setId(dto.getCompanyId());
            entity.setCompany(company);
        }

        if (dto.getBranchId() != null) {
            BranchModel branch = new BranchModel();
            branch.setId(dto.getBranchId());
            entity.setBranch(branch);
        }

        return entity;
    }
}
