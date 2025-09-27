package com.ast.storagemdp_api.controllers;

import com.ast.storagemdp_api.dtos.InventoryDTO;
import com.ast.storagemdp_api.services.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/inventories")
@RequiredArgsConstructor
public class InventoryController {

    private final InventoryService inventoryService;

    /** ---------------- CRUD ---------------- */

    @GetMapping("/{id}")
    public ResponseEntity<InventoryDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(inventoryService.getById(id));
    }

    @PostMapping
    public ResponseEntity<InventoryDTO> create(@RequestBody InventoryDTO dto) {
        return ResponseEntity.ok(inventoryService.create(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<InventoryDTO> update(@PathVariable Long id, @RequestBody InventoryDTO dto) {
        return ResponseEntity.ok(inventoryService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        inventoryService.delete(id);
        return ResponseEntity.noContent().build();
    }

    /** ---------------- Métricas generales ---------------- */

    @GetMapping("/metrics/count-all")
    public ResponseEntity<Long> countAllProducts() {
        return ResponseEntity.ok(inventoryService.countAllProducts());
    }

    @GetMapping("/metrics/count/company/{companyId}")
    public ResponseEntity<Long> countProductsByCompany(@PathVariable Long companyId) {
        return ResponseEntity.ok(inventoryService.countProductsByCompany(companyId));
    }

    @GetMapping("/metrics/count/company/{companyId}/branch/{branchId}")
    public ResponseEntity<Long> countProductsByCompanyAndBranch(@PathVariable Long companyId, @PathVariable Long branchId) {
        return ResponseEntity.ok(inventoryService.countProductsByCompanyAndBranch(companyId, branchId));
    }

    @GetMapping("/metrics/low-stock/company/{companyId}")
    public ResponseEntity<Long> countLowStockByCompany(@PathVariable Long companyId) {
        return ResponseEntity.ok(inventoryService.countLowStockByCompany(companyId));
    }

    @GetMapping("/metrics/low-stock/company/{companyId}/branch/{branchId}")
    public ResponseEntity<Long> countLowStockByCompanyAndBranch(@PathVariable Long companyId, @PathVariable Long branchId) {
        return ResponseEntity.ok(inventoryService.countLowStockByCompanyAndBranch(companyId, branchId));
    }

    @GetMapping("/metrics/categories/count")
    public ResponseEntity<Long> countCategories() {
        return ResponseEntity.ok(inventoryService.countCategories());
    }

    @GetMapping("/metrics/categories/count/company/{companyId}")
    public ResponseEntity<Long> countCategoriesByCompany(@PathVariable Long companyId) {
        return ResponseEntity.ok(inventoryService.countCategoriesByCompany(companyId));
    }

    @GetMapping("/metrics/categories/count/company/{companyId}/branch/{branchId}")
    public ResponseEntity<Long> countCategoriesByCompanyAndBranch(@PathVariable Long companyId, @PathVariable Long branchId) {
        return ResponseEntity.ok(inventoryService.countCategoriesByCompanyAndBranch(companyId, branchId));
    }

    @GetMapping("/metrics/value/company/{companyId}")
    public ResponseEntity<Double> getTotalValueByCompany(@PathVariable Long companyId) {
        return ResponseEntity.ok(inventoryService.getTotalValueByCompany(companyId));
    }

    @GetMapping("/metrics/value/company/{companyId}/branch/{branchId}")
    public ResponseEntity<Double> getTotalValueByCompanyAndBranch(@PathVariable Long companyId, @PathVariable Long branchId) {
        return ResponseEntity.ok(inventoryService.getTotalValueByCompanyAndBranch(companyId, branchId));
    }

    /** ---------------- Búsquedas ---------------- */

    @GetMapping("/search")
    public ResponseEntity<Page<InventoryDTO>> searchInventories(
            @RequestParam(required = false) Long companyId,
            @RequestParam(required = false) Long branchId,
            @RequestParam(required = false) Long categoryId,
            @RequestParam(required = false) Boolean lowStock,
            Pageable pageable
    ) {
        return ResponseEntity.ok(
                inventoryService.searchInventories(companyId, branchId, categoryId, lowStock, pageable)
        );
    }

    @GetMapping
    public ResponseEntity<Page<InventoryDTO>> getAllInventories(Pageable pageable) {
        return ResponseEntity.ok(inventoryService.getAllInventories(pageable));
    }
}
