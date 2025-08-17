package com.ast.storagemdp_api.repositories;

import com.ast.storagemdp_api.models.InventoryMovementModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InventoryMovementRepository extends JpaRepository<InventoryMovementModel, Long> {
    List<InventoryMovementModel> findByProductModelId(Long productId);

    Page<InventoryMovementModel> findByProductModelId(Long productId, Pageable pageable);
}
