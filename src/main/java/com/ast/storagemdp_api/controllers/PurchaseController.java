package com.ast.storagemdp_api.controllers;

import com.ast.storagemdp_api.dtos.PurchaseDTO;
import com.ast.storagemdp_api.services.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/purchases")
public class PurchaseController {

    private final PurchaseService purchaseService;

    @Autowired
    public PurchaseController(PurchaseService purchaseService) {
        this.purchaseService = purchaseService;
    }

    /** ---------------- CRUD ---------------- */

    @GetMapping("/{id}")
    public ResponseEntity<PurchaseDTO> getById(@PathVariable Long id) {
        PurchaseDTO dto = purchaseService.getPurchaseById(id);
        return dto != null ? ResponseEntity.ok(dto) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<PurchaseDTO> create(@RequestBody PurchaseDTO purchaseDTO) {
        return ResponseEntity.ok(purchaseService.createPurchase(purchaseDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PurchaseDTO> update(@PathVariable Long id, @RequestBody PurchaseDTO purchaseDTO) {
        PurchaseDTO updated = purchaseService.updatePurchase(id, purchaseDTO);
        return updated != null ? ResponseEntity.ok(updated) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        purchaseService.deletePurchase(id);
        return ResponseEntity.noContent().build();
    }

    /** ---------------- Búsqueda ---------------- */

    @GetMapping
    public ResponseEntity<Page<PurchaseDTO>> search(
            @RequestParam(required = false) Long companyId,
            @RequestParam(required = false) Long branchId,
            @RequestParam(required = false) Long supplierId,
            @RequestParam(required = false) Boolean paid,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate,
            Pageable pageable
    ) {
        Page<PurchaseDTO> results = purchaseService.searchPurchases(companyId, branchId, supplierId, paid, startDate, endDate, pageable);
        return ResponseEntity.ok(results);
    }

    /** ---------------- Métricas globales ---------------- */

    @GetMapping("/metrics/global/count")
    public ResponseEntity<Long> countGlobal(
            @RequestParam int year,
            @RequestParam int month
    ) {
        return ResponseEntity.ok(purchaseService.countPurchasesInMonth(year, month));
    }

    @GetMapping("/metrics/global/total")
    public ResponseEntity<Double> totalGlobal(
            @RequestParam int year,
            @RequestParam int month
    ) {
        return ResponseEntity.ok(purchaseService.getTotalSpentInMonth(year, month));
    }

    @GetMapping("/metrics/global/average")
    public ResponseEntity<Double> averageGlobal(
            @RequestParam int year,
            @RequestParam int month
    ) {
        return ResponseEntity.ok(purchaseService.getAveragePerPurchaseInMonth(year, month));
    }

    /** ---------------- Métricas por Empresa ---------------- */

    @GetMapping("/metrics/company/{companyId}/count")
    public ResponseEntity<Long> countByCompany(
            @PathVariable Long companyId,
            @RequestParam int year,
            @RequestParam int month
    ) {
        return ResponseEntity.ok(purchaseService.countPurchasesInMonthByCompany(companyId, year, month));
    }

    @GetMapping("/metrics/company/{companyId}/total")
    public ResponseEntity<Double> totalByCompany(
            @PathVariable Long companyId,
            @RequestParam int year,
            @RequestParam int month
    ) {
        return ResponseEntity.ok(purchaseService.getTotalSpentInMonthByCompany(companyId, year, month));
    }

    @GetMapping("/metrics/company/{companyId}/average")
    public ResponseEntity<Double> averageByCompany(
            @PathVariable Long companyId,
            @RequestParam int year,
            @RequestParam int month
    ) {
        return ResponseEntity.ok(purchaseService.getAveragePerPurchaseInMonthByCompany(companyId, year, month));
    }

    /** ---------------- Métricas por Empresa + Sede ---------------- */

    @GetMapping("/metrics/company/{companyId}/branch/{branchId}/count")
    public ResponseEntity<Long> countByCompanyAndBranch(
            @PathVariable Long companyId,
            @PathVariable Long branchId,
            @RequestParam int year,
            @RequestParam int month
    ) {
        return ResponseEntity.ok(purchaseService.countPurchasesInMonthByCompanyAndBranch(companyId, branchId, year, month));
    }

    @GetMapping("/metrics/company/{companyId}/branch/{branchId}/total")
    public ResponseEntity<Double> totalByCompanyAndBranch(
            @PathVariable Long companyId,
            @PathVariable Long branchId,
            @RequestParam int year,
            @RequestParam int month
    ) {
        return ResponseEntity.ok(purchaseService.getTotalSpentInMonthByCompanyAndBranch(companyId, branchId, year, month));
    }

    @GetMapping("/metrics/company/{companyId}/branch/{branchId}/average")
    public ResponseEntity<Double> averageByCompanyAndBranch(
            @PathVariable Long companyId,
            @PathVariable Long branchId,
            @RequestParam int year,
            @RequestParam int month
    ) {
        return ResponseEntity.ok(purchaseService.getAveragePerPurchaseInMonthByCompanyAndBranch(companyId, branchId, year, month));
    }
}
