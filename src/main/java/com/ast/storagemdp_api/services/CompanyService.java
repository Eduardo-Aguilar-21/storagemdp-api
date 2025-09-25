package com.ast.storagemdp_api.services;

import com.ast.storagemdp_api.dtos.CompanyDTO;
import com.ast.storagemdp_api.models.CompanyModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface CompanyService {
    CompanyDTO findById(Long id);

    List<CompanyDTO> findAll();

    Page<CompanyDTO> findAll(Pageable pageable);

    CompanyDTO save(CompanyDTO companyDTO);

    CompanyDTO update(Long id, CompanyDTO companyDTO);

    void deleteById(Long id);
}
