package com.ast.storagemdp_api.repositories;

import com.ast.storagemdp_api.enums.MovementType;
import com.ast.storagemdp_api.models.InventoryModel;
import com.ast.storagemdp_api.models.InventoryMovementModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.ZonedDateTime;
import java.util.List;

@Repository
public interface InventoryMovementRepository extends JpaRepository<InventoryMovementModel, Long>, JpaSpecificationExecutor<InventoryMovementModel> {
    List<InventoryMovementModel> findByProductModelId(Long productId);

    Page<InventoryMovementModel> findByProductModelId(Long productId, Pageable pageable);

    @Query("SELECT SUM(m.quantity) FROM InventoryMovementModel m " +
            "WHERE m.company.id = :companyId " +
            "AND m.type = :type " +
            "AND m.createdAt BETWEEN :start AND :end")
    Long getTotalByCompanyAndTypeAndDate(Long companyId, MovementType type, ZonedDateTime start, ZonedDateTime end);

    @Query("SELECT SUM(m.quantity) FROM InventoryMovementModel m " +
            "WHERE m.company.id = :companyId " +
            "AND m.branch.id = :branchId " +
            "AND m.type = :type " +
            "AND m.createdAt BETWEEN :start AND :end")
    Long getTotalByCompanyBranchAndTypeAndDate(Long companyId, Long branchId, MovementType type, ZonedDateTime start, ZonedDateTime end);

}
