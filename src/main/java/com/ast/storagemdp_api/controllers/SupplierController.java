package com.ast.storagemdp_api.controllers;

import com.ast.storagemdp_api.dtos.SupplierDTO;
import com.ast.storagemdp_api.models.CompanyModel;
import com.ast.storagemdp_api.services.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/suppliers")
public class SupplierController {

    @Autowired
    private SupplierService supplierService;

    @GetMapping("/{id}")
    public ResponseEntity<SupplierDTO> getSupplierById(@PathVariable Long id) {
        SupplierDTO supplierDTO = supplierService.getSupplierById(id);
        return ResponseEntity.ok(supplierDTO);
    }

    @PostMapping
    public ResponseEntity<SupplierDTO> createSupplier(@RequestBody SupplierDTO supplierDTO) {
        SupplierDTO saved = supplierService.saveSupplier(supplierDTO);
        return ResponseEntity.ok(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SupplierDTO> updateSupplier(@PathVariable Long id, @RequestBody SupplierDTO supplierDTO) {
        SupplierDTO updated = supplierService.updateSupplier(id, supplierDTO);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSupplier(@PathVariable Long id) {
        supplierService.deleteSupplier(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/company/{companyId}")
    public ResponseEntity<List<SupplierDTO>> getSuppliersByCompany(@PathVariable Long companyId) {
        CompanyModel company = new CompanyModel();
        company.setId(companyId);
        List<SupplierDTO> suppliers = supplierService.getSuppliersByCompany(company);
        return ResponseEntity.ok(suppliers);
    }

    @GetMapping("/company/{companyId}/active")
    public ResponseEntity<List<SupplierDTO>> getActiveSuppliersByCompany(@PathVariable Long companyId) {
        CompanyModel company = new CompanyModel();
        company.setId(companyId);
        List<SupplierDTO> suppliers = supplierService.getActiveSuppliersByCompany(company);
        return ResponseEntity.ok(suppliers);
    }

    @GetMapping("/company/{companyId}/search")
    public ResponseEntity<List<SupplierDTO>> searchSuppliers(
            @PathVariable Long companyId,
            @RequestParam String name
    ) {
        CompanyModel company = new CompanyModel();
        company.setId(companyId);
        List<SupplierDTO> suppliers = supplierService.searchSuppliersByCompanyAndName(company, name);
        return ResponseEntity.ok(suppliers);
    }

    @GetMapping("/company/{companyId}/page")
    public ResponseEntity<Page<SupplierDTO>> getSuppliersByCompanyPage(
            @PathVariable Long companyId,
            Pageable pageable
    ) {
        CompanyModel company = new CompanyModel();
        company.setId(companyId);
        Page<SupplierDTO> page = supplierService.getSuppliersByCompany(company, pageable);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/company/{companyId}/active/page")
    public ResponseEntity<Page<SupplierDTO>> getActiveSuppliersByCompanyPage(
            @PathVariable Long companyId,
            Pageable pageable
    ) {
        CompanyModel company = new CompanyModel();
        company.setId(companyId);
        Page<SupplierDTO> page = supplierService.getSuppliersByCompanyAndActive(company, true, pageable);
        return ResponseEntity.ok(page);
    }
}
