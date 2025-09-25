package com.ast.storagemdp_api.services;

import com.ast.storagemdp_api.dtos.BranchDTO;
import com.ast.storagemdp_api.models.CompanyModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BranchService {
    BranchDTO findById(Long id);

    List<BranchDTO> findAll();

    Page<BranchDTO> findAll(Pageable pageable);

    BranchDTO save(BranchDTO branchDTO);

    BranchDTO update(Long id, BranchDTO branchDTO);

    void deleteById(Long id);

    List<BranchDTO> findByCompany(CompanyModel company);

    Page<BranchDTO> findByCompany(CompanyModel company, Pageable pageable);
}
