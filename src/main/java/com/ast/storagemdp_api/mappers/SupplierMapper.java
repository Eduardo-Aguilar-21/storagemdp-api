package com.ast.storagemdp_api.mappers;

import com.ast.storagemdp_api.dtos.SupplierDTO;
import com.ast.storagemdp_api.models.CompanyModel;
import com.ast.storagemdp_api.models.SupplierModel;

public class SupplierMapper {

    public static SupplierDTO toDTO(SupplierModel entity) {
        if (entity == null) {
            return null;
        }

        SupplierDTO dto = new SupplierDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setAddress(entity.getAddress());
        dto.setPhone(entity.getPhone());
        dto.setEmail(entity.getEmail());
        dto.setContactPerson(entity.getContactPerson());
        dto.setTaxId(entity.getTaxId());
        dto.setNotes(entity.getNotes());
        dto.setActive(entity.getActive());

        if (entity.getCompany() != null) {
            dto.setCompanyId(entity.getCompany().getId());
            dto.setCompanyName(entity.getCompany().getName());
        }

        return dto;
    }

    public static SupplierModel toEntity(SupplierDTO dto) {
        if (dto == null) {
            return null;
        }

        SupplierModel entity = new SupplierModel();
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        entity.setAddress(dto.getAddress());
        entity.setPhone(dto.getPhone());
        entity.setEmail(dto.getEmail());
        entity.setContactPerson(dto.getContactPerson());
        entity.setTaxId(dto.getTaxId());
        entity.setNotes(dto.getNotes());
        entity.setActive(dto.getActive());

        if (dto.getCompanyId() != null) {
            CompanyModel company = new CompanyModel();
            company.setId(dto.getCompanyId());
            entity.setCompany(company);
        }

        return entity;
    }
}
