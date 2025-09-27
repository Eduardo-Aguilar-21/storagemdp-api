package com.ast.storagemdp_api.services;

import com.ast.storagemdp_api.dtos.InventoryMovementDTO;
import com.ast.storagemdp_api.dtos.InventoryMovementSummaryDTO;
import com.ast.storagemdp_api.enums.MovementType;
import com.ast.storagemdp_api.models.InventoryMovementModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;

public interface InventoryMovementService {
    InventoryMovementDTO findById(Long id);

    List<InventoryMovementDTO> findAll();

    Page<InventoryMovementDTO> findAll(Pageable pageable);

    InventoryMovementDTO save(InventoryMovementDTO inventoryMovementDTO);

    InventoryMovementDTO update(Long id, InventoryMovementDTO inventoryMovementDTO);

    void deleteById(Long id);

    Page<InventoryMovementDTO> searchMovements(
            Long companyId,
            Long branchId,
            MovementType type,
            ZonedDateTime start,
            ZonedDateTime end,
            Pageable pageable
    );

    List<InventoryMovementDTO> findByProductId(Long productId);

    Page<InventoryMovementDTO> findByProductId(Long productId, Pageable pageable);

    Long getTotalByCompanyAndTypeAndDate(Long companyId, MovementType type, ZonedDateTime start, ZonedDateTime end);

    Long getTotalByCompanyBranchAndTypeAndDate(Long companyId, Long branchId, MovementType type, ZonedDateTime start, ZonedDateTime end);

    InventoryMovementSummaryDTO getSummaryByCompanyAndBranch(
            Long companyId,
            Long branchId,
            ZonedDateTime start,
            ZonedDateTime end
    );
}
