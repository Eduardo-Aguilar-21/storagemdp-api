package com.ast.storagemdp_api.mappers;

import com.ast.storagemdp_api.dtos.BranchDTO;
import com.ast.storagemdp_api.models.BranchModel;

public class BranchMapper {

    public static BranchDTO toDTO(BranchModel entity) {
        if (entity == null) {
            return null;
        }

        BranchDTO dto = new BranchDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setAddress(entity.getAddress());
        dto.setCompanyId(entity.getCompany() != null ? entity.getCompany().getId() : null);
        dto.setCreatedAt(entity.getCreatedAt());
        dto.setUpdatedAt(entity.getUpdatedAt());

        return dto;
    }

    public static BranchModel toEntity(BranchDTO dto) {
        if (dto == null) {
            return null;
        }

        BranchModel entity = new BranchModel();
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        entity.setAddress(dto.getAddress());

        if (dto.getCompanyId() != null) {
            entity.setCompany(new com.ast.storagemdp_api.models.CompanyModel());
            entity.getCompany().setId(dto.getCompanyId());
        }

        return entity;
    }
}
