package com.ast.storagemdp_api.controllers;


import com.ast.storagemdp_api.dtos.CompanyDTO;
import com.ast.storagemdp_api.services.CompanyService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/companies")
public class CompanyController {
    private final CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<CompanyDTO> getCompanyById(@PathVariable Long id) {
        try {
            CompanyDTO company = companyService.findById(id);
            return ResponseEntity.ok(company);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<CompanyDTO>> getAllCompanies() {
        List<CompanyDTO> companies = companyService.findAll();
        return companies.isEmpty()
                ? ResponseEntity.noContent().build()
                : ResponseEntity.ok(companies);
    }

    @GetMapping("/page")
    public ResponseEntity<Page<CompanyDTO>> getAllCompaniesPaged(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        Page<CompanyDTO> companyPage = companyService.findAll(pageable);
        return companyPage.isEmpty()
                ? ResponseEntity.noContent().build()
                : ResponseEntity.ok(companyPage);
    }

    @PostMapping
    public ResponseEntity<CompanyDTO> createCompany(@RequestBody CompanyDTO companyDTO) {
        CompanyDTO saved = companyService.save(companyDTO);
        return ResponseEntity.ok(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CompanyDTO> updateCompany(@PathVariable Long id, @RequestBody CompanyDTO companyDTO) {
        CompanyDTO updated = companyService.update(id, companyDTO);

        if (updated == null){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCompany(@PathVariable Long id) {
        companyService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
