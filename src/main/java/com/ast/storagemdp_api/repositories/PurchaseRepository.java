package com.ast.storagemdp_api.repositories;

import com.ast.storagemdp_api.models.BranchModel;
import com.ast.storagemdp_api.models.CompanyModel;
import com.ast.storagemdp_api.models.PurchaseModel;
import com.ast.storagemdp_api.models.SupplierModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface PurchaseRepository extends JpaRepository<PurchaseModel, Long>, JpaSpecificationExecutor<PurchaseModel> {

    long countByPurchaseDateBetween(LocalDate startDate, LocalDate endDate);

    /** Compras por fecha exacta (todas las empresas y sedes). */
    List<PurchaseModel> findByPurchaseDate(LocalDate date);
    Page<PurchaseModel> findByPurchaseDate(LocalDate date, Pageable pageable);

    /** Compras entre dos fechas (todas las empresas y sedes). */
    List<PurchaseModel> findByPurchaseDateBetween(LocalDate startDate, LocalDate endDate);

}
