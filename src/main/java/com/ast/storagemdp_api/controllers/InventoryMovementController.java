package com.ast.storagemdp_api.controllers;

import com.ast.storagemdp_api.dto.InventoryMovementDTO;
import com.ast.storagemdp_api.mappers.InventoryMovementMapper;
import com.ast.storagemdp_api.models.InventoryMovementModel;
import com.ast.storagemdp_api.services.InventoryMovementService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/inventory-movement")
@RequiredArgsConstructor
public class InventoryMovementController {
    private final InventoryMovementService inventoryMovementService;

    @GetMapping("/{id}")
    public ResponseEntity<InventoryMovementDTO> findById(@PathVariable Long id) {
        return inventoryMovementService.findById(id)
                .map(inventoryMovementModel -> ResponseEntity.ok(InventoryMovementMapper.toDTO(inventoryMovementModel)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/by-product/{productId}")
    public ResponseEntity<List<InventoryMovementDTO>> findByProductModelId(@PathVariable Long productId) {
        List<InventoryMovementDTO> inventoryMovement = inventoryMovementService.findByProductModelId(productId)
                .stream()
                .map(InventoryMovementMapper::toDTO)
                .toList();
        return inventoryMovement.isEmpty()
                ? ResponseEntity.noContent().build()
                : ResponseEntity.ok(inventoryMovement);
    }

    @GetMapping("/by-product/{productId}/page")
    public ResponseEntity<Page<InventoryMovementDTO>> findByProductModelId(@PathVariable Long productId,
                                                                           @RequestParam(defaultValue = "0") int page,
                                                                           @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);

        Page<InventoryMovementDTO> inventoryMovementDTOPage = inventoryMovementService.findByProductModelId(productId, pageable)
                .map(InventoryMovementMapper::toDTO);

        return inventoryMovementDTOPage.isEmpty()
                ? ResponseEntity.noContent().build()
                : ResponseEntity.ok(inventoryMovementDTOPage);
    }

    @GetMapping
    public ResponseEntity<List<InventoryMovementDTO>> findAll() {
        List<InventoryMovementDTO> inventoryMovement = inventoryMovementService.findAll()
                .stream()
                .map(InventoryMovementMapper::toDTO)
                .toList();

        return inventoryMovement.isEmpty()
                ? ResponseEntity.noContent().build()
                : ResponseEntity.ok(inventoryMovement);
    }

    @GetMapping("/page")
    public ResponseEntity<Page<InventoryMovementDTO>> findAll(@RequestParam(defaultValue = "0") int page,
                                                              @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<InventoryMovementDTO> inventoryMovementDTOPage = inventoryMovementService.findAll(pageable)
                .map(InventoryMovementMapper::toDTO);

        return inventoryMovementDTOPage.isEmpty()
                ? ResponseEntity.noContent().build()
                : ResponseEntity.ok(inventoryMovementDTOPage);
    }

    @PostMapping
    public ResponseEntity<InventoryMovementDTO> save(@RequestBody InventoryMovementDTO inventoryMovementDTO) {
        InventoryMovementDTO saved = inventoryMovementService.save(inventoryMovementDTO);
        return ResponseEntity.ok(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<InventoryMovementDTO> update(@PathVariable Long id, @RequestBody InventoryMovementDTO inventoryMovementDTO) {
        InventoryMovementDTO update = inventoryMovementService.update(id, inventoryMovementDTO);

        if (update == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(update);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        Optional<InventoryMovementModel> existing = inventoryMovementService.findById(id);
        if (existing.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        inventoryMovementService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
