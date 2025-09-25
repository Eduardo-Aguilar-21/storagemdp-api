package com.ast.storagemdp_api.services.impl;

import com.ast.storagemdp_api.dtos.BranchDTO;
import com.ast.storagemdp_api.mappers.BranchMapper;
import com.ast.storagemdp_api.models.BranchModel;
import com.ast.storagemdp_api.models.CompanyModel;
import com.ast.storagemdp_api.repositories.BranchRepository;
import com.ast.storagemdp_api.services.BranchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BranchServiceImpl implements BranchService {

    @Autowired
    private BranchRepository branchRepository;

    @Override
    public BranchDTO findById(Long id) {
        BranchModel branch = branchRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Branch not found with id: " + id));
        return BranchMapper.toDTO(branch);
    }

    @Override
    public List<BranchDTO> findAll() {
        return branchRepository.findAll().stream()
                .map(BranchMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Page<BranchDTO> findAll(Pageable pageable) {
        Page<BranchModel> page = branchRepository.findAll(pageable);
        List<BranchDTO> dtos = page.stream().map(BranchMapper::toDTO).collect(Collectors.toList());
        return new PageImpl<>(dtos, pageable, page.getTotalElements());
    }

    @Override
    public List<BranchDTO> findByCompany(CompanyModel company) {
        return branchRepository.findByCompany(company).stream()
                .map(BranchMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Page<BranchDTO> findByCompany(CompanyModel company, Pageable pageable) {
        Page<BranchModel> page = branchRepository.findByCompany(company, pageable);
        List<BranchDTO> dtos = page.stream().map(BranchMapper::toDTO).collect(Collectors.toList());
        return new PageImpl<>(dtos, pageable, page.getTotalElements());
    }

    @Override
    public BranchDTO save(BranchDTO branchDTO) {
        BranchModel entity = BranchMapper.toEntity(branchDTO);
        BranchModel saved = branchRepository.save(entity);
        return BranchMapper.toDTO(saved);
    }

    @Override
    public BranchDTO update(Long id, BranchDTO branchDTO) {
        BranchModel existing = branchRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Branch not found with id: " + id));

        existing.setName(branchDTO.getName());
        existing.setAddress(branchDTO.getAddress());

        if (branchDTO.getCompanyId() != null) {
            CompanyModel company = new CompanyModel();
            company.setId(branchDTO.getCompanyId());
            existing.setCompany(company);
        }

        BranchModel updated = branchRepository.save(existing);
        return BranchMapper.toDTO(updated);
    }

    @Override
    public void deleteById(Long id) {
        branchRepository.deleteById(id);
    }
}
