package com.ast.storagemdp_api.services;

import com.ast.storagemdp_api.dtos.PromotionDTO;
import com.ast.storagemdp_api.enums.PromotionStatus;
import com.ast.storagemdp_api.enums.PromotionType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.ZonedDateTime;

public interface PromotionService {
    PromotionDTO getPromotionById(Long id);

    PromotionDTO createPromotion(PromotionDTO promotionDTO);

    PromotionDTO updatePromotion(Long id, PromotionDTO promotionDTO);

    void deletePromotion(Long id);

    Page<PromotionDTO> searchPromotions(
            Long companyId,
            Long branchId,
            PromotionType type,
            PromotionStatus status,
            ZonedDateTime start,
            ZonedDateTime end,
            Pageable pageable
    );

    long countAllByCompany(Long companyId);
    long countAllByCompanyAndBranch(Long companyId, Long branchId);
    long countByStatusAndCompany(PromotionStatus status, Long companyId);
    long countByStatusAndCompanyAndBranch(PromotionStatus status, Long companyId, Long branchId);
}
