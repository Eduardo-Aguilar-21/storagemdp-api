package com.ast.storagemdp_api.repositories;

import com.ast.storagemdp_api.models.InventoryMovementModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InventoryMovementRepository extends JpaRepository<InventoryMovementModel, Long> {
}
