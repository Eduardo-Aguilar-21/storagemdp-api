package com.ast.storagemdp_api.services.impl;

import com.ast.storagemdp_api.dtos.PromotionDTO;
import com.ast.storagemdp_api.enums.PromotionStatus;
import com.ast.storagemdp_api.enums.PromotionType;
import com.ast.storagemdp_api.mappers.PromotionMapper;
import com.ast.storagemdp_api.models.PromotionModel;
import com.ast.storagemdp_api.repositories.PromotionRepository;
import com.ast.storagemdp_api.services.PromotionService;
import com.ast.storagemdp_api.specifications.PromotionSpecifications;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;

@Service
public class PromotionServiceImpl implements PromotionService {

    @Override
    public PromotionDTO getPromotionById(Long id) {
        PromotionModel promotion = promotionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Promotion not found"));
        return PromotionMapper.toDTO(promotion);
    }

    private final PromotionRepository promotionRepository;

    @Autowired
    public PromotionServiceImpl(PromotionRepository promotionRepository) {
        this.promotionRepository = promotionRepository;
    }

    @Override
    public PromotionDTO createPromotion(PromotionDTO promotionDTO) {
        PromotionModel promotion = PromotionMapper.toEntity(promotionDTO);
        PromotionModel saved = promotionRepository.save(promotion);
        return PromotionMapper.toDTO(saved);
    }

    @Override
    public PromotionDTO updatePromotion(Long id, PromotionDTO promotionDTO) {
        PromotionModel existing = promotionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Promotion not found"));
        PromotionModel updated = PromotionMapper.toEntity(promotionDTO);
        updated.setId(existing.getId()); // mantener el id
        PromotionModel saved = promotionRepository.save(updated);
        return PromotionMapper.toDTO(saved);
    }

    @Override
    public void deletePromotion(Long id) {
        promotionRepository.deleteById(id);
    }

    @Override
    public Page<PromotionDTO> searchPromotions(Long companyId, Long branchId,
                                               PromotionType type, PromotionStatus status,
                                               ZonedDateTime start, ZonedDateTime end,
                                               Pageable pageable) {

        Specification<PromotionModel> spec = Specification.allOf(
                PromotionSpecifications.hasCompany(companyId),
                PromotionSpecifications.hasBranch(branchId),
                PromotionSpecifications.hasType(type),
                PromotionSpecifications.hasStatus(status),
                PromotionSpecifications.startsAfter(start),
                PromotionSpecifications.endsBefore(end)
        );

        return promotionRepository.findAll(spec, pageable)
                .map(PromotionMapper::toDTO);
    }

    @Override
    public long countAllByCompany(Long companyId) {
        Specification<PromotionModel> spec = PromotionSpecifications.hasCompany(companyId);
        return promotionRepository.count(spec);
    }

    @Override
    public long countAllByCompanyAndBranch(Long companyId, Long branchId) {
        Specification<PromotionModel> spec = Specification.allOf(
                PromotionSpecifications.hasCompany(companyId),
                PromotionSpecifications.hasBranch(branchId)
        );
        return promotionRepository.count(spec);
    }

    @Override
    public long countByStatusAndCompany(PromotionStatus status, Long companyId) {
        Specification<PromotionModel> spec = Specification.allOf(
                PromotionSpecifications.hasCompany(companyId),
                PromotionSpecifications.hasStatus(status)
        );
        return promotionRepository.count(spec);
    }

    @Override
    public long countByStatusAndCompanyAndBranch(PromotionStatus status, Long companyId, Long branchId) {
        Specification<PromotionModel> spec = Specification.allOf(
                PromotionSpecifications.hasCompany(companyId),
                PromotionSpecifications.hasBranch(branchId),
                PromotionSpecifications.hasStatus(status)
        );
        return promotionRepository.count(spec);
    }
}
