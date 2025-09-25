package com.ast.storagemdp_api.services;

import com.ast.storagemdp_api.dtos.PurchaseDTO;
import com.ast.storagemdp_api.models.CompanyModel;
import com.ast.storagemdp_api.models.SupplierModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.List;

public interface PurchaseService {
    PurchaseDTO getPurchaseById(Long id);
    PurchaseDTO createPurchase(PurchaseDTO purchaseDTO);
    PurchaseDTO updatePurchase(Long id, PurchaseDTO purchaseDTO);
    void deletePurchase(Long id);

    List<PurchaseDTO> getPurchasesBySupplier(SupplierModel supplier);
    Page<PurchaseDTO> getPurchasesBySupplier(SupplierModel supplier, Pageable pageable);

    List<PurchaseDTO> getPurchasesBySupplierAndPaid(SupplierModel supplier, Boolean paid);
    Page<PurchaseDTO> getPurchasesBySupplierAndPaid(SupplierModel supplier, Boolean paid, Pageable pageable);

    List<PurchaseDTO> getPurchasesByDate(LocalDate date);
    Page<PurchaseDTO> getPurchasesByDate(LocalDate date, Pageable pageable);

    Page<PurchaseDTO> getAllPurchasesByCompanyPagedDesc(CompanyModel company, Pageable pageable);

    Page<PurchaseDTO> getPurchasesByCompanyAndSupplierPagedDesc(CompanyModel company,SupplierModel supplier, Pageable pageable);

    long countPurchasesInMonth(int year, int month);

    double getTotalSpentInMonth(int year, int month);

    double getAveragePerPurchaseInMonth(int year, int month);

}
