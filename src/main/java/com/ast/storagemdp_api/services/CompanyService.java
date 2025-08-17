package com.ast.storagemdp_api.services;

import com.ast.storagemdp_api.dto.CompanyDTO;
import com.ast.storagemdp_api.mappers.CompanyMapper;
import com.ast.storagemdp_api.models.CompanyModel;

import java.util.List;
import java.util.Optional;

public interface CompanyService {
    Optional<CompanyModel> findById(Long id);

    List<CompanyModel> findAll();

    CompanyDTO save(CompanyDTO companyDTO);

    CompanyDTO update(Long id, CompanyDTO companyDTO);

    void deleteById(Long id);
}
