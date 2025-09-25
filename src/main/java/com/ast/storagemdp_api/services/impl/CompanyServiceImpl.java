package com.ast.storagemdp_api.services.impl;

import com.ast.storagemdp_api.dtos.CompanyDTO;
import com.ast.storagemdp_api.mappers.CompanyMapper;
import com.ast.storagemdp_api.models.CompanyModel;
import com.ast.storagemdp_api.repositories.CompanyRepository;
import com.ast.storagemdp_api.services.CompanyService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CompanyServiceImpl implements CompanyService {

    private final CompanyRepository companyRepository;

    public CompanyServiceImpl(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    @Override
    public CompanyDTO findById(Long id) {
        CompanyModel entity = companyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Company not found with id: " + id));
        return CompanyMapper.toDTO(entity);
    }

    @Override
    public List<CompanyDTO> findAll() {
        return companyRepository.findAll()
                .stream()
                .map(CompanyMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Page<CompanyDTO> findAll(Pageable pageable) {
        Page<CompanyModel> page = companyRepository.findAll(pageable);
        List<CompanyDTO> dtos = page.stream()
                .map(CompanyMapper::toDTO)
                .collect(Collectors.toList());
        return new PageImpl<>(dtos, pageable, page.getTotalElements());
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