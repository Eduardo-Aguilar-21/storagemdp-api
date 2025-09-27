package com.ast.storagemdp_api.repositories;

import com.ast.storagemdp_api.models.PromotionModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface PromotionRepository extends JpaRepository<PromotionModel, Long>, JpaSpecificationExecutor<PromotionModel> {
}
