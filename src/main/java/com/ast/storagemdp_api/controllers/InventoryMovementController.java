package com.ast.storagemdp_api.controllers;

import com.ast.storagemdp_api.dtos.InventoryMovementDTO;
import com.ast.storagemdp_api.services.InventoryMovementService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventory-movement")
@RequiredArgsConstructor
public class InventoryMovementController {

    private final InventoryMovementService inventoryMovementService;

    @GetMapping("/{id}")
    public ResponseEntity<InventoryMovementDTO> findById(@PathVariable Long id) {
        InventoryMovementDTO dto = inventoryMovementService.findById(id);
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/by-product/{productId}")
    public ResponseEntity<List<InventoryMovementDTO>> findByProductId(@PathVariable Long productId) {
        List<InventoryMovementDTO> list = inventoryMovementService.findByProductId(productId);
        return list.isEmpty()
                ? ResponseEntity.noContent().build()
                : ResponseEntity.ok(list);
    }

    @GetMapping("/by-product/{productId}/page")
    public ResponseEntity<Page<InventoryMovementDTO>> findByProductIdPaged(
            @PathVariable Long productId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        Pageable pageable = PageRequest.of(page, size);
        Page<InventoryMovementDTO> paged = inventoryMovementService.findByProductId(productId, pageable);
        return paged.isEmpty()
                ? ResponseEntity.noContent().build()
                : ResponseEntity.ok(paged);
    }

    @GetMapping
    public ResponseEntity<List<InventoryMovementDTO>> findAll() {
        List<InventoryMovementDTO> list = inventoryMovementService.findAll();
        return list.isEmpty()
                ? ResponseEntity.noContent().build()
                : ResponseEntity.ok(list);
    }

    @GetMapping("/page")
    public ResponseEntity<Page<InventoryMovementDTO>> findAllPaged(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        Pageable pageable = PageRequest.of(page, size);
        Page<InventoryMovementDTO> paged = inventoryMovementService.findAll(pageable);
        return paged.isEmpty()
                ? ResponseEntity.noContent().build()
                : ResponseEntity.ok(paged);
    }

    @PostMapping
    public ResponseEntity<InventoryMovementDTO> save(@RequestBody InventoryMovementDTO dto) {
        InventoryMovementDTO saved = inventoryMovementService.save(dto);
        return ResponseEntity.ok(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<InventoryMovementDTO> update(@PathVariable Long id,
                                                       @RequestBody InventoryMovementDTO dto) {
        InventoryMovementDTO updated = inventoryMovementService.update(id, dto);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        inventoryMovementService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
