package com.ast.storagemdp_api.repositories;

import com.ast.storagemdp_api.models.BranchModel;
import com.ast.storagemdp_api.models.CompanyModel;
import com.ast.storagemdp_api.models.PurchaseModel;
import com.ast.storagemdp_api.models.SupplierModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface PurchaseRepository extends JpaRepository<PurchaseModel, Long> {

    long countByPurchaseDateBetween(LocalDate startDate, LocalDate endDate);

    List<PurchaseModel> findBySupplier(SupplierModel supplier);
    Page<PurchaseModel> findBySupplier(SupplierModel supplier, Pageable pageable);

    List<PurchaseModel> findBySupplierAndCompanyId(SupplierModel supplier, Long companyId);
    Page<PurchaseModel> findBySupplierAndCompanyId(SupplierModel supplier, Long companyId, Pageable pageable);

    List<PurchaseModel> findBySupplierAndCompanyIdAndBranchId(SupplierModel supplier, Long companyId, Long branchId);
    Page<PurchaseModel> findBySupplierAndCompanyIdAndBranchId(SupplierModel supplier, Long companyId, Long branchId, Pageable pageable);

    List<PurchaseModel> findBySupplierAndPaid(SupplierModel supplier, Boolean paid);
    Page<PurchaseModel> findBySupplierAndPaid(SupplierModel supplier, Boolean paid, Pageable pageable);

    List<PurchaseModel> findBySupplierAndPaidAndCompanyId(SupplierModel supplier, Boolean paid, Long companyId);
    Page<PurchaseModel> findBySupplierAndPaidAndCompanyId(SupplierModel supplier, Boolean paid, Long companyId, Pageable pageable);

    List<PurchaseModel> findBySupplierAndPaidAndCompanyIdAndBranchId(SupplierModel supplier, Boolean paid, Long companyId, Long branchId);
    Page<PurchaseModel> findBySupplierAndPaidAndCompanyIdAndBranchId(SupplierModel supplier, Boolean paid, Long companyId, Long branchId, Pageable pageable);

    /** Compras por fecha exacta (todas las empresas y sedes). */
    List<PurchaseModel> findByPurchaseDate(LocalDate date);
    Page<PurchaseModel> findByPurchaseDate(LocalDate date, Pageable pageable);

    /** Compras por fecha exacta y empresa. */
    List<PurchaseModel> findByPurchaseDateAndCompanyId(LocalDate date, Long companyId);
    Page<PurchaseModel> findByPurchaseDateAndCompanyId(LocalDate date, Pageable pageable, Long companyId);

    /** Compras por fecha exacta, empresa y sede. */
    List<PurchaseModel> findByPurchaseDateAndCompanyIdAndBranchId(LocalDate date, Long companyId, Long branchId);
    Page<PurchaseModel> findByPurchaseDateAndCompanyIdAndBranchId(LocalDate date, Long companyId, Long branchId, Pageable pageable);

    /** Compras entre dos fechas (todas las empresas y sedes). */
    List<PurchaseModel> findByPurchaseDateBetween(LocalDate startDate, LocalDate endDate);

    /** Compras entre dos fechas y por empresa. */
    List<PurchaseModel> findByPurchaseDateBetweenAndCompanyId(LocalDate startDate, LocalDate endDate, Long companyId);
    Page<PurchaseModel> findByPurchaseDateBetweenAndCompanyId(LocalDate startDate, LocalDate endDate, Long companyId, Pageable pageable);

    List<PurchaseModel> findByPurchaseDateBetweenAndCompanyIdAndBranchId(LocalDate startDate, LocalDate endDate, Long companyId, Long branchId);
    Page<PurchaseModel> findByPurchaseDateBetweenAndCompanyIdAndBranchId(LocalDate startDate, LocalDate endDate, Long companyId, Long branchId, Pageable pageable);

    Page<PurchaseModel> findByCompanyOrderByPurchaseDateDesc(CompanyModel company, Pageable pageable);

    Page<PurchaseModel> findByCompanyAndSupplierOrderByPurchaseDateDesc(CompanyModel company, SupplierModel supplier, Pageable pageable);

}
