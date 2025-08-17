package com.ast.storagemdp_api.services.impl;

import com.ast.storagemdp_api.dto.CompanyDTO;
import com.ast.storagemdp_api.mappers.CompanyMapper;
import com.ast.storagemdp_api.models.CompanyModel;
import com.ast.storagemdp_api.repositories.CompanyRepository;
import com.ast.storagemdp_api.services.CompanyService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyServiceImpl implements CompanyService {

    private final CompanyRepository companyRepository;

    public CompanyServiceImpl(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    @Override
    public Optional<CompanyModel> findById(Long id) {
        return companyRepository.findById(id);
    }

    @Override
    public List<CompanyModel> findAll() {
        return companyRepository.findAll();
    }

    @Override
    public CompanyDTO save(CompanyDTO companyDTO) {
        CompanyModel entity = CompanyMapper.toEntity(companyDTO);
        CompanyModel saved = companyRepository.save(entity);
        return CompanyMapper.toDTO(saved);
    }

    @Override
    public CompanyDTO update(Long id, CompanyDTO companyDTO) {
        return companyRepository.findById(id)
                .map(existing -> {
                    existing.setName(companyDTO.getName());
                    CompanyModel updated = companyRepository.save(existing);
                    return CompanyMapper.toDTO(updated);
                })
                .orElseThrow(() -> new RuntimeException("Company not found with id: " + id));
    }

    @Override
    public void deleteById(Long id) {
        if (!companyRepository.existsById(id)) {
            throw new RuntimeException("Company not found with id: " + id);
        }
        companyRepository.deleteById(id);
    }
}