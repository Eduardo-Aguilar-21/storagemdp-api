package com.ast.storagemdp_api.repositories;

import com.ast.storagemdp_api.models.BranchModel;
import com.ast.storagemdp_api.models.CompanyModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BranchRepository extends JpaRepository<BranchModel, Long> {
    List<BranchModel> findByCompany(CompanyModel company);
    Page<BranchModel> findByCompany(CompanyModel company, Pageable pageable);
}
