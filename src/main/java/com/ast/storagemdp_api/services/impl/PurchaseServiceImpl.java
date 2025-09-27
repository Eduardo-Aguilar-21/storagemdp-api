package com.ast.storagemdp_api.services.impl;

import com.ast.storagemdp_api.dtos.PurchaseDTO;
import com.ast.storagemdp_api.mappers.PurchaseMapper;
import com.ast.storagemdp_api.models.PurchaseModel;
import com.ast.storagemdp_api.repositories.PurchaseRepository;
import com.ast.storagemdp_api.services.PurchaseService;
import com.ast.storagemdp_api.specifications.PurchaseSpecifications;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class PurchaseServiceImpl implements PurchaseService {

    private final PurchaseRepository purchaseRepository;

    @Autowired
    public PurchaseServiceImpl(PurchaseRepository purchaseRepository) {
        this.purchaseRepository = purchaseRepository;
    }

    @Override
    public PurchaseDTO getPurchaseById(Long id) {
        Optional<PurchaseModel> purchase = purchaseRepository.findById(id);
        return purchase.map(PurchaseMapper::toDTO).orElse(null);
    }

    @Override
    public PurchaseDTO createPurchase(PurchaseDTO purchaseDTO) {
        PurchaseModel entity = PurchaseMapper.toEntity(purchaseDTO);
        PurchaseModel saved = purchaseRepository.save(entity);
        return PurchaseMapper.toDTO(saved);
    }

    @Override
    public PurchaseDTO updatePurchase(Long id, PurchaseDTO purchaseDTO) {
        return purchaseRepository.findById(id)
                .map(existing -> {
                    PurchaseModel entity = PurchaseMapper.toEntity(purchaseDTO);
                    entity.setId(id);
                    PurchaseModel updated = purchaseRepository.save(entity);
                    return PurchaseMapper.toDTO(updated);
                })
                .orElse(null);
    }

    @Override
    public void deletePurchase(Long id) {
        purchaseRepository.deleteById(id);
    }

    @Override
    public Page<PurchaseDTO> searchPurchases(
            Long companyId,
            Long branchId,
            Long supplierId,
            Boolean paid,
            LocalDate startDate,
            LocalDate endDate,
            Pageable pageable
    ) {
        Specification<PurchaseModel> spec = Specification.allOf(
                PurchaseSpecifications.hasCompany(companyId),
                PurchaseSpecifications.hasBranch(branchId),
                PurchaseSpecifications.hasSupplier(supplierId),
                PurchaseSpecifications.isPaid(paid),
                PurchaseSpecifications.purchaseDateBetween(startDate, endDate)
        );

        return purchaseRepository.findAll(spec, pageable)
                .map(PurchaseMapper::toDTO);
    }

    /** ---------------- Métricas simples (globales) ---------------- */

    @Override
    public long countPurchasesInMonth(int year, int month) {
        LocalDate start = LocalDate.of(year, month, 1);
        LocalDate end = start.withDayOfMonth(start.lengthOfMonth());
        return purchaseRepository.countByPurchaseDateBetween(start, end);
    }

    @Override
    public double getTotalSpentInMonth(int year, int month) {
        LocalDate start = LocalDate.of(year, month, 1);
        LocalDate end = start.withDayOfMonth(start.lengthOfMonth());

        List<PurchaseModel> purchases = purchaseRepository.findByPurchaseDateBetween(start, end);
        return purchases.stream()
                .mapToDouble(PurchaseModel::getTotalAmount)
                .sum();
    }

    @Override
    public double getAveragePerPurchaseInMonth(int year, int month) {
        long count = countPurchasesInMonth(year, month);
        if (count == 0) return 0.0;
        return getTotalSpentInMonth(year, month) / count;
    }

    /** ---------------- Métricas por Empresa ---------------- */

    @Override
    public long countPurchasesInMonthByCompany(Long companyId, int year, int month) {
        LocalDate start = LocalDate.of(year, month, 1);
        LocalDate end = start.withDayOfMonth(start.lengthOfMonth());

        Specification<PurchaseModel> spec = Specification.allOf(
                PurchaseSpecifications.hasCompany(companyId),
                PurchaseSpecifications.purchaseDateBetween(start, end)
        );

        return purchaseRepository.count(spec);
    }


    @Override
    public double getTotalSpentInMonthByCompany(Long companyId, int year, int month) {
        LocalDate start = LocalDate.of(year, month, 1);
        LocalDate end = start.withDayOfMonth(start.lengthOfMonth());

        Specification<PurchaseModel> spec = Specification.allOf(
                PurchaseSpecifications.hasCompany(companyId),
                PurchaseSpecifications.purchaseDateBetween(start, end)
        );

        return purchaseRepository.findAll(spec).stream()
                .mapToDouble(PurchaseModel::getTotalAmount)
                .sum();
    }


    @Override
    public double getAveragePerPurchaseInMonthByCompany(Long companyId, int year, int month) {
        long count = countPurchasesInMonthByCompany(companyId, year, month);
        if (count == 0) return 0.0;
        return getTotalSpentInMonthByCompany(companyId, year, month) / count;
    }

    /** ---------------- Métricas por Empresa + Sede ---------------- */

    @Override
    public long countPurchasesInMonthByCompanyAndBranch(Long companyId, Long branchId, int year, int month) {
        LocalDate start = LocalDate.of(year, month, 1);
        LocalDate end = start.withDayOfMonth(start.lengthOfMonth());

        Specification<PurchaseModel> spec = Specification.allOf(
                PurchaseSpecifications.hasCompany(companyId),
                PurchaseSpecifications.hasBranch(branchId),
                PurchaseSpecifications.purchaseDateBetween(start, end)
        );

        return purchaseRepository.count(spec);
    }

    @Override
    public double getTotalSpentInMonthByCompanyAndBranch(Long companyId, Long branchId, int year, int month) {
        LocalDate start = LocalDate.of(year, month, 1);
        LocalDate end = start.withDayOfMonth(start.lengthOfMonth());

        Specification<PurchaseModel> spec = Specification.allOf(
                PurchaseSpecifications.hasCompany(companyId),
                PurchaseSpecifications.hasBranch(branchId),
                PurchaseSpecifications.purchaseDateBetween(start, end)
        );

        return purchaseRepository.findAll(spec).stream()
                .mapToDouble(PurchaseModel::getTotalAmount)
                .sum();
    }


    @Override
    public double getAveragePerPurchaseInMonthByCompanyAndBranch(Long companyId, Long branchId, int year, int month) {
        long count = countPurchasesInMonthByCompanyAndBranch(companyId, branchId, year, month);
        if (count == 0) return 0.0;
        return getTotalSpentInMonthByCompanyAndBranch(companyId, branchId, year, month) / count;
    }
}
