package com.ast.storagemdp_api.repositories;

import com.ast.storagemdp_api.models.CompanyModel;
import com.ast.storagemdp_api.models.SupplierModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SupplierRepository extends JpaRepository<SupplierModel, Long> {
    List<SupplierModel> findByCompany(CompanyModel company);
    Page<SupplierModel> findByCompany(CompanyModel company, Pageable pageable);

    List<SupplierModel> findByCompanyAndActiveTrue(CompanyModel company);
    Page<SupplierModel> findByCompanyAndActiveTrue(CompanyModel company, Pageable pageable);

    List<SupplierModel> findByCompanyAndActive(CompanyModel company, Boolean active);
    Page<SupplierModel> findByCompanyAndActive(CompanyModel company, Boolean active, Pageable pageable);

    List<SupplierModel> findByCompanyAndNameContainingIgnoreCase(CompanyModel company, String name);
    Page<SupplierModel> findByCompanyAndNameContainingIgnoreCase(CompanyModel company, String name, Pageable pageable);
}
