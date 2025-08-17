package com.ast.storagemdp_api.mappers;

import com.ast.storagemdp_api.dto.CompanyDTO;
import com.ast.storagemdp_api.models.CompanyModel;

public class CompanyMapper {
    public static CompanyDTO toDTO(CompanyModel entity){
        if (entity == null) {
            return null;
        }

        CompanyDTO dto = new CompanyDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        return dto;
    }

    public static CompanyModel toEntity(CompanyDTO dto){
        if (dto == null) {
            return null;
        }

        CompanyModel entity = new CompanyModel();
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        return entity;
    }
}
