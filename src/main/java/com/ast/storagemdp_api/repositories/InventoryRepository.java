package com.ast.storagemdp_api.repositories;

import com.ast.storagemdp_api.models.InventoryModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface InventoryRepository extends JpaRepository<InventoryModel, Long>, JpaSpecificationExecutor<InventoryModel> {

    List<InventoryModel> findByCompanyId(Long companyId);

    List<InventoryModel> findByBranchId(Long branchId);

    List<InventoryModel> findByCompanyIdAndBranchId(Long companyId, Long branchId);

    Optional<InventoryModel> findByCompanyIdAndBranchIdAndProductId(Long companyId, Long branchId, Long productId);

    List<InventoryModel> findByProductId(Long productId);
}
