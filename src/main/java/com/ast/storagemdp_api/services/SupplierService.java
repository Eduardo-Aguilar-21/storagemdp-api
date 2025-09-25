package com.ast.storagemdp_api.services;

import com.ast.storagemdp_api.dtos.SupplierDTO;
import com.ast.storagemdp_api.models.CompanyModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface SupplierService {
    SupplierDTO getSupplierById(Long id);

    SupplierDTO saveSupplier(SupplierDTO supplierDTO);

    SupplierDTO updateSupplier(Long id, SupplierDTO supplierDTO);

    void deleteSupplier(Long id);

    List<SupplierDTO> getActiveSuppliersByCompany(CompanyModel company);

    List<SupplierDTO> getSuppliersByCompany(CompanyModel company);

    Page<SupplierDTO> getSuppliersByCompany(CompanyModel company, Pageable pageable);

    List<SupplierDTO> getSuppliersByCompanyAndActive(CompanyModel company, Boolean active);

    Page<SupplierDTO> getSuppliersByCompanyAndActive(CompanyModel company, Boolean active, Pageable pageable);

    List<SupplierDTO> searchSuppliersByCompanyAndName(CompanyModel company, String name);

    Page<SupplierDTO> searchSuppliersByCompanyAndName(CompanyModel company, String name, Pageable pageable);
}
