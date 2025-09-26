package com.ast.storagemdp_api.services;

import com.ast.storagemdp_api.dtos.PurchaseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;

public interface PurchaseService {

    PurchaseDTO getPurchaseById(Long id);
    PurchaseDTO createPurchase(PurchaseDTO purchaseDTO);
    PurchaseDTO updatePurchase(Long id, PurchaseDTO purchaseDTO);
    void deletePurchase(Long id);


    Page<PurchaseDTO> searchPurchases(
            Long companyId,
            Long branchId,
            Long supplierId,
            Boolean paid,
            LocalDate startDate,
            LocalDate endDate,
            Pageable pageable
    );

    /** Métricas simples */
    long countPurchasesInMonth(int year, int month);
    double getTotalSpentInMonth(int year, int month);
    double getAveragePerPurchaseInMonth(int year, int month);

    /** ---------------- Métricas por Empresa ---------------- */
    long countPurchasesInMonthByCompany(Long companyId, int year, int month);
    double getTotalSpentInMonthByCompany(Long companyId, int year, int month);
    double getAveragePerPurchaseInMonthByCompany(Long companyId, int year, int month);

    /** ---------------- Métricas por Empresa + Sede ---------------- */
    long countPurchasesInMonthByCompanyAndBranch(Long companyId, Long branchId, int year, int month);
    double getTotalSpentInMonthByCompanyAndBranch(Long companyId, Long branchId, int year, int month);
    double getAveragePerPurchaseInMonthByCompanyAndBranch(Long companyId, Long branchId, int year, int month);
}
