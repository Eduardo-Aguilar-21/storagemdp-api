package com.ast.storagemdp_api.services;

import com.ast.storagemdp_api.dto.InventoryMovementDTO;
import com.ast.storagemdp_api.models.InventoryMovementModel;
import com.ast.storagemdp_api.models.ProductModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface InventoryMovementService {
    Optional<InventoryMovementModel> findById(Long id);

    List<InventoryMovementModel> findByProductModelId(Long productId);

    Page<InventoryMovementModel> findByProductModelId(Long productId, Pageable pageable);

    List<InventoryMovementModel> findAll();

    Page<InventoryMovementModel> findAll(Pageable pageable);

    InventoryMovementDTO save(InventoryMovementDTO inventoryMovementDTO);

    InventoryMovementDTO update(Long id, InventoryMovementDTO inventoryMovementDTO);

    void deleteById(Long id);
}
