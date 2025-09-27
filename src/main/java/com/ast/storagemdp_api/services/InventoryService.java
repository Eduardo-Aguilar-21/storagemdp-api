package com.ast.storagemdp_api.services;

import com.ast.storagemdp_api.dtos.InventoryDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface InventoryService {

    /** CRUD básico */
    InventoryDTO getById(Long id);
    InventoryDTO create(InventoryDTO dto);
    InventoryDTO update(Long id, InventoryDTO dto);
    void delete(Long id);

    /** Métricas generales */
    long countAllProducts(); // total de productos en todo el inventario
    long countProductsByCompany(Long companyId);
    long countProductsByCompanyAndBranch(Long companyId, Long branchId);

    long countLowStockByCompany(Long companyId);
    long countLowStockByCompanyAndBranch(Long companyId, Long branchId);


    /** Categorías */
    long countCategories();
    long countCategoriesByCompany(Long companyId);
    long countCategoriesByCompanyAndBranch(Long companyId, Long branchId);

    /** Valor económico */
    double getTotalValueByCompany(Long companyId);
    double getTotalValueByCompanyAndBranch(Long companyId, Long branchId);

    /** Búsqueda flexible con filtros */
    Page<InventoryDTO> searchInventories(
            Long companyId,
            Long branchId,
            Long categoryId,
            Boolean lowStock,
            Pageable pageable
    );

    /** Listado general */
    Page<InventoryDTO> getAllInventories(Pageable pageable);
}
