package com.ast.storagemdp_api.controllers;

import com.ast.storagemdp_api.dtos.PromotionDTO;
import com.ast.storagemdp_api.enums.PromotionStatus;
import com.ast.storagemdp_api.enums.PromotionType;
import com.ast.storagemdp_api.services.PromotionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.ZonedDateTime;

@RestController
@RequestMapping("/api/promotions")
public class PromotionController {

    private final PromotionService promotionService;

    @Autowired
    public PromotionController(PromotionService promotionService) {
        this.promotionService = promotionService;
    }

    // Obtener promoción por ID
    @GetMapping("/{id}")
    public ResponseEntity<PromotionDTO> getPromotionById(@PathVariable Long id) {
        PromotionDTO promotion = promotionService.getPromotionById(id);
        return ResponseEntity.ok(promotion);
    }

    // Crear nueva promoción
    @PostMapping
    public ResponseEntity<PromotionDTO> createPromotion(@RequestBody PromotionDTO promotionDTO) {
        PromotionDTO created = promotionService.createPromotion(promotionDTO);
        return ResponseEntity.ok(created);
    }

    // Actualizar promoción existente
    @PutMapping("/{id}")
    public ResponseEntity<PromotionDTO> updatePromotion(@PathVariable Long id,
                                                        @RequestBody PromotionDTO promotionDTO) {
        PromotionDTO updated = promotionService.updatePromotion(id, promotionDTO);
        return ResponseEntity.ok(updated);
    }

    // Eliminar promoción
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePromotion(@PathVariable Long id) {
        promotionService.deletePromotion(id);
        return ResponseEntity.noContent().build();
    }

    // Buscar promociones con filtros y paginación
    @GetMapping
    public ResponseEntity<Page<PromotionDTO>> searchPromotions(
            @RequestParam(required = false) Long companyId,
            @RequestParam(required = false) Long branchId,
            @RequestParam(required = false) PromotionType type,
            @RequestParam(required = false) PromotionStatus status,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) ZonedDateTime start,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) ZonedDateTime end,
            Pageable pageable
    ) {
        Page<PromotionDTO> promotions = promotionService.searchPromotions(
                companyId, branchId, type, status, start, end, pageable
        );
        return ResponseEntity.ok(promotions);
    }

    // Total promociones de una empresa
    @GetMapping("/count")
    public ResponseEntity<Long> countPromotionsByCompany(@RequestParam Long companyId) {
        long count = promotionService.countAllByCompany(companyId);
        return ResponseEntity.ok(count);
    }

    // Total promociones de una empresa y sede
    @GetMapping("/count/branch")
    public ResponseEntity<Long> countPromotionsByCompanyAndBranch(@RequestParam Long companyId,
                                                                  @RequestParam Long branchId) {
        long count = promotionService.countAllByCompanyAndBranch(companyId, branchId);
        return ResponseEntity.ok(count);
    }

    // Cantidad de promociones activas por empresa
    @GetMapping("/count/active")
    public ResponseEntity<Long> countActivePromotionsByCompany(@RequestParam Long companyId) {
        long count = promotionService.countByStatusAndCompany(PromotionStatus.ACTIVE, companyId);
        return ResponseEntity.ok(count);
    }

    // Cantidad de promociones activas por empresa y sede
    @GetMapping("/count/active/branch")
    public ResponseEntity<Long> countActivePromotionsByCompanyAndBranch(@RequestParam Long companyId,
                                                                        @RequestParam Long branchId) {
        long count = promotionService.countByStatusAndCompanyAndBranch(PromotionStatus.ACTIVE, companyId, branchId);
        return ResponseEntity.ok(count);
    }

    // Cantidad de promociones inactivas por empresa
    @GetMapping("/count/inactive")
    public ResponseEntity<Long> countInactivePromotionsByCompany(@RequestParam Long companyId) {
        long count = promotionService.countByStatusAndCompany(PromotionStatus.INACTIVE, companyId);
        return ResponseEntity.ok(count);
    }

    // Cantidad de promociones inactivas por empresa y sede
    @GetMapping("/count/inactive/branch")
    public ResponseEntity<Long> countInactivePromotionsByCompanyAndBranch(@RequestParam Long companyId,
                                                                          @RequestParam Long branchId) {
        long count = promotionService.countByStatusAndCompanyAndBranch(PromotionStatus.INACTIVE, companyId, branchId);
        return ResponseEntity.ok(count);
    }

    // Cantidad de promociones expiradas por empresa
    @GetMapping("/count/expired")
    public ResponseEntity<Long> countExpiredPromotionsByCompany(@RequestParam Long companyId) {
        long count = promotionService.countByStatusAndCompany(PromotionStatus.EXPIRED, companyId);
        return ResponseEntity.ok(count);
    }

    // Cantidad de promociones expiradas por empresa y sede
    @GetMapping("/count/expired/branch")
    public ResponseEntity<Long> countExpiredPromotionsByCompanyAndBranch(@RequestParam Long companyId,
                                                                         @RequestParam Long branchId) {
        long count = promotionService.countByStatusAndCompanyAndBranch(PromotionStatus.EXPIRED, companyId, branchId);
        return ResponseEntity.ok(count);
    }
}
