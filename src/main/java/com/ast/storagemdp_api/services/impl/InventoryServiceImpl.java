package com.ast.storagemdp_api.services.impl;

import com.ast.storagemdp_api.dtos.InventoryDTO;
import com.ast.storagemdp_api.mappers.InventoryMapper;
import com.ast.storagemdp_api.models.InventoryModel;
import com.ast.storagemdp_api.repositories.InventoryRepository;
import com.ast.storagemdp_api.services.InventoryService;
import com.ast.storagemdp_api.specifications.InventorySpecifications;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InventoryServiceImpl implements InventoryService {

    private final InventoryRepository inventoryRepository;
    private final InventoryMapper inventoryMapper;

    /** CRUD básico */
    @Override
    public InventoryDTO getById(Long id) {
        InventoryModel inventory = inventoryRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Inventory not found with id: " + id));
        return inventoryMapper.toDTO(inventory);
    }

    @Override
    public InventoryDTO create(InventoryDTO dto) {
        InventoryModel model = inventoryMapper.toEntity(dto);
        InventoryModel saved = inventoryRepository.save(model);
        return inventoryMapper.toDTO(saved);
    }

    @Override
    public InventoryDTO update(Long id, InventoryDTO dto) {
        InventoryModel existing = inventoryRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Inventory not found with id: " + id));

        // Actualizamos campos principales
        existing.setPrice(dto.getPrice());
        existing.setQuantity(dto.getQuantity());

        InventoryModel updated = inventoryRepository.save(existing);
        return inventoryMapper.toDTO(updated);
    }

    @Override
    public void delete(Long id) {
        if (!inventoryRepository.existsById(id)) {
            throw new EntityNotFoundException("Inventory not found with id: " + id);
        }
        inventoryRepository.deleteById(id);
    }

    /** Métricas generales */
    @Override
    public long countAllProducts() {
        return inventoryRepository.count();
    }

    @Override
    public long countProductsByCompany(Long companyId) {
        return inventoryRepository.findByCompanyId(companyId).size();
    }

    @Override
    public long countProductsByCompanyAndBranch(Long companyId, Long branchId) {
        return inventoryRepository.findByCompanyIdAndBranchId(companyId, branchId).size();
    }

    /** ---------------- Bajo stock ---------------- */
    @Override
    public long countLowStockByCompany(Long companyId) {
        return inventoryRepository.findByCompanyId(companyId).stream()
                .filter(inv -> inv.getQuantity() < inv.getMinStock())
                .count();
    }

    @Override
    public long countLowStockByCompanyAndBranch(Long companyId, Long branchId) {
        return inventoryRepository.findByCompanyIdAndBranchId(companyId, branchId).stream()
                .filter(inv -> inv.getQuantity() < inv.getMinStock())
                .count();
    }

    /** Categorías */
    @Override
    public long countCategories() {
        return inventoryRepository.findAll().stream()
                .map(inv -> inv.getProduct().getCategory().getId())
                .distinct()
                .count();
    }

    @Override
    public long countCategoriesByCompany(Long companyId) {
        return inventoryRepository.findByCompanyId(companyId).stream()
                .map(inv -> inv.getProduct().getCategory().getId())
                .distinct()
                .count();
    }

    @Override
    public long countCategoriesByCompanyAndBranch(Long companyId, Long branchId) {
        return inventoryRepository.findByCompanyIdAndBranchId(companyId, branchId).stream()
                .map(inv -> inv.getProduct().getCategory().getId())
                .distinct()
                .count();
    }

    /** Valor económico */
    @Override
    public double getTotalValueByCompany(Long companyId) {
        return inventoryRepository.findByCompanyId(companyId).stream()
                .mapToDouble(inv -> inv.getPrice() * inv.getQuantity())
                .sum();
    }

    @Override
    public double getTotalValueByCompanyAndBranch(Long companyId, Long branchId) {
        return inventoryRepository.findByCompanyIdAndBranchId(companyId, branchId).stream()
                .mapToDouble(inv -> inv.getPrice() * inv.getQuantity())
                .sum();
    }

    /** Búsqueda flexible con Specification */
    @Override
    public Page<InventoryDTO> searchInventories(
            Long companyId,
            Long branchId,
            Long categoryId,
            Boolean lowStock,
            Pageable pageable
    ) {
        Specification<InventoryModel> spec = Specification.allOf(
                InventorySpecifications.hasCompany(companyId),
                InventorySpecifications.hasBranch(branchId),
                InventorySpecifications.hasCategory(categoryId),
                InventorySpecifications.isLowStock(lowStock)
        );

        return inventoryRepository.findAll(spec, pageable)
                .map(inventoryMapper::toDTO);
    }

    /** Listado general */
    @Override
    public Page<InventoryDTO> getAllInventories(Pageable pageable) {
        return inventoryRepository.findAll(pageable)
                .map(inventoryMapper::toDTO);
    }
}
