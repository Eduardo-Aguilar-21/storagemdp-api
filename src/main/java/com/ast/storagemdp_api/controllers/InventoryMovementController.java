package com.ast.storagemdp_api.controllers;

import com.ast.storagemdp_api.dtos.InventoryMovementDTO;
import com.ast.storagemdp_api.dtos.InventoryMovementSummaryDTO;
import com.ast.storagemdp_api.enums.MovementType;
import com.ast.storagemdp_api.services.InventoryMovementService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.ZonedDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/inventory-movements")
@RequiredArgsConstructor
public class InventoryMovementController {

    private final InventoryMovementService inventoryMovementService;

    /** CRUD Básico */

    @GetMapping("/{id}")
    public ResponseEntity<InventoryMovementDTO> getById(@PathVariable Long id) {
        InventoryMovementDTO dto = inventoryMovementService.findById(id);
        return ResponseEntity.ok(dto);
    }

    @GetMapping
    public ResponseEntity<List<InventoryMovementDTO>> getAll() {
        List<InventoryMovementDTO> list = inventoryMovementService.findAll();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/paged")
    public ResponseEntity<Page<InventoryMovementDTO>> getAllPaged(Pageable pageable) {
        Page<InventoryMovementDTO> page = inventoryMovementService.findAll(pageable);
        return ResponseEntity.ok(page);
    }

    @PostMapping
    public ResponseEntity<InventoryMovementDTO> create(@RequestBody InventoryMovementDTO dto) {
        InventoryMovementDTO created = inventoryMovementService.save(dto);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<InventoryMovementDTO> update(
            @PathVariable Long id,
            @RequestBody InventoryMovementDTO dto
    ) {
        InventoryMovementDTO updated = inventoryMovementService.update(id, dto);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        inventoryMovementService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    /** Búsqueda flexible con filtros */
    @GetMapping("/search")
    public ResponseEntity<Page<InventoryMovementDTO>> search(
            @RequestParam(required = false) Long companyId,
            @RequestParam(required = false) Long branchId,
            @RequestParam(required = false) MovementType type,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) ZonedDateTime start,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) ZonedDateTime end,
            Pageable pageable
    ) {
        Page<InventoryMovementDTO> page = inventoryMovementService.searchMovements(companyId, branchId, type, start, end, pageable);
        return ResponseEntity.ok(page);
    }

    /** Buscar movimientos por producto */
    @GetMapping("/product/{productId}")
    public ResponseEntity<List<InventoryMovementDTO>> findByProduct(@PathVariable Long productId) {
        List<InventoryMovementDTO> list = inventoryMovementService.findByProductId(productId);
        return ResponseEntity.ok(list);
    }

    @GetMapping("/product/{productId}/paged")
    public ResponseEntity<Page<InventoryMovementDTO>> findByProductPaged(
            @PathVariable Long productId,
            Pageable pageable
    ) {
        Page<InventoryMovementDTO> page = inventoryMovementService.findByProductId(productId, pageable);
        return ResponseEntity.ok(page);
    }

    /** Métricas: Totales por empresa, sede y tipo */
    @GetMapping("/total")
    public ResponseEntity<Long> getTotal(
            @RequestParam Long companyId,
            @RequestParam(required = false) Long branchId,
            @RequestParam MovementType type,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) ZonedDateTime start,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) ZonedDateTime end
    ) {
        Long total;
        if (branchId != null) {
            total = inventoryMovementService.getTotalByCompanyBranchAndTypeAndDate(companyId, branchId, type, start, end);
        } else {
            total = inventoryMovementService.getTotalByCompanyAndTypeAndDate(companyId, type, start, end);
        }
        return ResponseEntity.ok(total);
    }

    @GetMapping("/summary")
    public ResponseEntity<InventoryMovementSummaryDTO> getSummary(
            @RequestParam Long companyId,
            @RequestParam(required = false) Long branchId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) ZonedDateTime start,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) ZonedDateTime end
    ) {
        InventoryMovementSummaryDTO summary = inventoryMovementService.getSummaryByCompanyAndBranch(companyId, branchId, start, end);
        return ResponseEntity.ok(summary);
    }
}
