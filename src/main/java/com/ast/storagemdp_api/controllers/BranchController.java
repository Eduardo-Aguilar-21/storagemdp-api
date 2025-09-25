package com.ast.storagemdp_api.controllers;

import com.ast.storagemdp_api.dtos.BranchDTO;
import com.ast.storagemdp_api.models.CompanyModel;
import com.ast.storagemdp_api.services.BranchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/branches")
public class BranchController {

    @Autowired
    private BranchService branchService;

    @GetMapping("/{id}")
    public BranchDTO getBranchById(@PathVariable Long id) {
        return branchService.findById(id);
    }

    @GetMapping
    public List<BranchDTO> getAllBranches() {
        return branchService.findAll();
    }

    @GetMapping("/paged")
    public Page<BranchDTO> getAllBranchesPaged(Pageable pageable) {
        return branchService.findAll(pageable);
    }

    @GetMapping("/company/{companyId}")
    public List<BranchDTO> getBranchesByCompany(@PathVariable Long companyId) {
        CompanyModel company = new CompanyModel();
        company.setId(companyId);
        return branchService.findByCompany(company);
    }

    @GetMapping("/company/{companyId}/paged")
    public Page<BranchDTO> getBranchesByCompanyPaged(@PathVariable Long companyId, Pageable pageable) {
        CompanyModel company = new CompanyModel();
        company.setId(companyId);
        return branchService.findByCompany(company, pageable);
    }

    @PostMapping
    public BranchDTO createBranch(@RequestBody BranchDTO branchDTO) {
        return branchService.save(branchDTO);
    }

    @PutMapping("/{id}")
    public BranchDTO updateBranch(@PathVariable Long id, @RequestBody BranchDTO branchDTO) {
        return branchService.update(id, branchDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBranch(@PathVariable Long id) {
        branchService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
