package com.ast.storagemdp_api.services.impl;

import com.ast.storagemdp_api.dtos.InventoryMovementDTO;
import com.ast.storagemdp_api.mappers.InventoryMovementMapper;
import com.ast.storagemdp_api.models.InventoryMovementModel;
import com.ast.storagemdp_api.repositories.InventoryMovementRepository;
import com.ast.storagemdp_api.services.InventoryMovementService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

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
}
