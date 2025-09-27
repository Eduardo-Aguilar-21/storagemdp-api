package com.ast.storagemdp_api.services.impl;

import com.ast.storagemdp_api.dtos.InventoryMovementDTO;
import com.ast.storagemdp_api.dtos.InventoryMovementSummaryDTO;
import com.ast.storagemdp_api.enums.MovementType;
import com.ast.storagemdp_api.mappers.InventoryMovementMapper;
import com.ast.storagemdp_api.models.InventoryMovementModel;
import com.ast.storagemdp_api.repositories.InventoryMovementRepository;
import com.ast.storagemdp_api.services.InventoryMovementService;
import com.ast.storagemdp_api.specifications.InventoryMovementSpecifications;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class InventoryMovementServiceImpl implements InventoryMovementService {

    private final InventoryMovementRepository inventoryMovementRepository;

    @Override
    public InventoryMovementDTO findById(Long id) {
        InventoryMovementModel entity = inventoryMovementRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("InventoryMovement not found with id: " + id));
        return InventoryMovementMapper.toDTO(entity);
    }

    @Override
    public List<InventoryMovementDTO> findAll() {
        return inventoryMovementRepository.findAll()
                .stream()
                .map(InventoryMovementMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Page<InventoryMovementDTO> findAll(Pageable pageable) {
        Page<InventoryMovementModel> page = inventoryMovementRepository.findAll(pageable);
        List<InventoryMovementDTO> dtos = page.stream()
                .map(InventoryMovementMapper::toDTO)
                .collect(Collectors.toList());
        return new PageImpl<>(dtos, pageable, page.getTotalElements());
    }

    @Override
    public InventoryMovementDTO save(InventoryMovementDTO inventoryMovementDTO){
        InventoryMovementModel entity = InventoryMovementMapper.toEntity(inventoryMovementDTO);
        InventoryMovementModel saved = inventoryMovementRepository.save(entity);
        return InventoryMovementMapper.toDTO(saved);
    }

    @Override
    public InventoryMovementDTO update(Long id, InventoryMovementDTO inventoryMovementDTO){
        return inventoryMovementRepository.findById(id)
                .map(existing -> {
                    existing.setType(inventoryMovementDTO.getType());
                    InventoryMovementModel updated = inventoryMovementRepository.save(existing);
                    return InventoryMovementMapper.toDTO(updated);
                })
                .orElseThrow(() -> new RuntimeException("not found with id: " + id));
    }

    @Override
    public void deleteById(Long id){
        if (!inventoryMovementRepository.existsById(id)) {
            throw new RuntimeException("not found with id: " + id);
        }
        inventoryMovementRepository.deleteById(id);
    }

    @Override
    public Page<InventoryMovementDTO> searchMovements(
            Long companyId,
            Long branchId,
            MovementType type,
            ZonedDateTime start,
            ZonedDateTime end,
            Pageable pageable
    ) {
        Specification<InventoryMovementModel> spec = Specification.allOf(
                InventoryMovementSpecifications.hasCompany(companyId),
                InventoryMovementSpecifications.hasBranch(branchId),
                InventoryMovementSpecifications.hasType(type),
                InventoryMovementSpecifications.createdBetween(start, end)
        );

        return inventoryMovementRepository.findAll(spec, pageable)
                .map(InventoryMovementMapper::toDTO);
    }

    @Override
    public List<InventoryMovementDTO> findByProductId(Long productId) {
        return inventoryMovementRepository.findByProductModelId(productId)
                .stream()
                .map(InventoryMovementMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Page<InventoryMovementDTO> findByProductId(Long productId, Pageable pageable) {
        Page<InventoryMovementModel> page = inventoryMovementRepository.findByProductModelId(productId, pageable);
        List<InventoryMovementDTO> dtos = page.stream()
                .map(InventoryMovementMapper::toDTO)
                .collect(Collectors.toList());
        return new PageImpl<>(dtos, pageable, page.getTotalElements());
    }

    @Override
    public Long getTotalByCompanyAndTypeAndDate(Long companyId, MovementType type, ZonedDateTime start, ZonedDateTime end) {
        return inventoryMovementRepository.getTotalByCompanyAndTypeAndDate(companyId, type, start, end);
    }

    @Override
    public Long getTotalByCompanyBranchAndTypeAndDate(Long companyId, Long branchId, MovementType type, ZonedDateTime start, ZonedDateTime end) {
        return inventoryMovementRepository.getTotalByCompanyBranchAndTypeAndDate(companyId, branchId, type, start, end);
    }

    @Override
    public InventoryMovementSummaryDTO getSummaryByCompanyAndBranch(Long companyId, Long branchId, ZonedDateTime start, ZonedDateTime end) {
        Long totalEntries;
        Long totalExits;

        if (branchId != null) {
            totalEntries = inventoryMovementRepository.getTotalByCompanyBranchAndTypeAndDate(companyId, branchId, MovementType.ENTRY, start, end);
            totalExits = inventoryMovementRepository.getTotalByCompanyBranchAndTypeAndDate(companyId, branchId, MovementType.EXIT, start, end);
        } else {
            totalEntries = inventoryMovementRepository.getTotalByCompanyAndTypeAndDate(companyId, MovementType.ENTRY, start, end);
            totalExits = inventoryMovementRepository.getTotalByCompanyAndTypeAndDate(companyId, MovementType.EXIT, start, end);
        }

        if (totalEntries == null) totalEntries = 0L;
        if (totalExits == null) totalExits = 0L;

        InventoryMovementSummaryDTO summary = new InventoryMovementSummaryDTO();
        summary.setTotalEntries(totalEntries);
        summary.setTotalExits(totalExits);
        summary.setTotalMovements(totalEntries + totalExits);

        return summary;
    }
}
