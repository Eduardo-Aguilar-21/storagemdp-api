package com.ast.storagemdp_api.services;

import com.ast.storagemdp_api.dtos.InventoryMovementDTO;
import com.ast.storagemdp_api.models.InventoryMovementModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface InventoryMovementService {
    InventoryMovementDTO findById(Long id);

    List<InventoryMovementDTO> findByProductId(Long productId);

    Page<InventoryMovementDTO> findByProductId(Long productId, Pageable pageable);

    List<InventoryMovementDTO> findAll();

    Page<InventoryMovementDTO> findAll(Pageable pageable);

    InventoryMovementDTO save(InventoryMovementDTO inventoryMovementDTO);

    InventoryMovementDTO update(Long id, InventoryMovementDTO inventoryMovementDTO);

    void deleteById(Long id);
}
