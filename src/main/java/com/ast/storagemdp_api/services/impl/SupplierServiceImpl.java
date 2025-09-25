package com.ast.storagemdp_api.services.impl;

import com.ast.storagemdp_api.dtos.SupplierDTO;
import com.ast.storagemdp_api.mappers.SupplierMapper;
import com.ast.storagemdp_api.models.CompanyModel;
import com.ast.storagemdp_api.models.SupplierModel;
import com.ast.storagemdp_api.repositories.SupplierRepository;
import com.ast.storagemdp_api.services.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SupplierServiceImpl implements SupplierService {

    @Autowired
    private SupplierRepository supplierRepository;

    @Override
    public SupplierDTO getSupplierById(Long id) {
        SupplierModel supplier = supplierRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Supplier not found with id: " + id));
        return SupplierMapper.toDTO(supplier);
    }

    @Override
    public SupplierDTO saveSupplier(SupplierDTO supplierDTO) {
        SupplierModel entity = SupplierMapper.toEntity(supplierDTO);
        SupplierModel saved = supplierRepository.save(entity);
        return SupplierMapper.toDTO(saved);
    }

    @Override
    public SupplierDTO updateSupplier(Long id, SupplierDTO supplierDTO) {
        SupplierModel existingSupplier = supplierRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Supplier not found with id: " + id));

        existingSupplier.setName(supplierDTO.getName());
        existingSupplier.setAddress(supplierDTO.getAddress());
        existingSupplier.setPhone(supplierDTO.getPhone());
        existingSupplier.setEmail(supplierDTO.getEmail());
        existingSupplier.setContactPerson(supplierDTO.getContactPerson());
        existingSupplier.setTaxId(supplierDTO.getTaxId());
        existingSupplier.setNotes(supplierDTO.getNotes());
        existingSupplier.setActive(supplierDTO.getActive());

        if (supplierDTO.getCompanyId() != null) {
            CompanyModel company = new CompanyModel();
            company.setId(supplierDTO.getCompanyId());
            existingSupplier.setCompany(company);
        }

        SupplierModel updatedSupplier = supplierRepository.save(existingSupplier);
        return SupplierMapper.toDTO(updatedSupplier);
    }

    @Override
    public void deleteSupplier(Long id) {
        supplierRepository.deleteById(id);
    }

    @Override
    public List<SupplierDTO> getActiveSuppliersByCompany(CompanyModel company) {
        return supplierRepository.findByCompanyAndActiveTrue(company)
                .stream()
                .map(SupplierMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<SupplierDTO> getSuppliersByCompany(CompanyModel company) {
        return supplierRepository.findByCompany(company)
                .stream()
                .map(SupplierMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Page<SupplierDTO> getSuppliersByCompany(CompanyModel company, Pageable pageable) {
        Page<SupplierModel> page = supplierRepository.findByCompany(company, pageable);
        List<SupplierDTO> dtos = page.stream().map(SupplierMapper::toDTO).collect(Collectors.toList());
        return new PageImpl<>(dtos, pageable, page.getTotalElements());
    }

    @Override
    public List<SupplierDTO> getSuppliersByCompanyAndActive(CompanyModel company, Boolean active) {
        return supplierRepository.findByCompanyAndActive(company, active)
                .stream()
                .map(SupplierMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Page<SupplierDTO> getSuppliersByCompanyAndActive(CompanyModel company, Boolean active, Pageable pageable) {
        Page<SupplierModel> page = supplierRepository.findByCompanyAndActive(company, active, pageable);
        List<SupplierDTO> dtos = page.stream().map(SupplierMapper::toDTO).collect(Collectors.toList());
        return new PageImpl<>(dtos, pageable, page.getTotalElements());
    }

    @Override
    public List<SupplierDTO> searchSuppliersByCompanyAndName(CompanyModel company, String name) {
        return supplierRepository.findByCompanyAndNameContainingIgnoreCase(company, name)
                .stream()
                .map(SupplierMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Page<SupplierDTO> searchSuppliersByCompanyAndName(CompanyModel company, String name, Pageable pageable) {
        Page<SupplierModel> page = supplierRepository.findByCompanyAndNameContainingIgnoreCase(company, name, pageable);
        List<SupplierDTO> dtos = page.stream().map(SupplierMapper::toDTO).collect(Collectors.toList());
        return new PageImpl<>(dtos, pageable, page.getTotalElements());
    }
}
