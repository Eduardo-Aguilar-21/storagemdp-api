package com.ast.storagemdp_api.services.impl;

import com.ast.storagemdp_api.dto.InventoryMovementDTO;
import com.ast.storagemdp_api.mappers.InventoryMovementMapper;
import com.ast.storagemdp_api.models.InventoryMovementModel;
import com.ast.storagemdp_api.models.ProductModel;
import com.ast.storagemdp_api.repositories.InventoryMovementRepository;
import com.ast.storagemdp_api.services.InventoryMovementService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class InventoryMovementServiceImpl implements InventoryMovementService {

    private final InventoryMovementRepository inventoryMovementRepository;

    @Override
    public Optional<InventoryMovementModel> findById(Long id){
        return inventoryMovementRepository.findById(id);
    }

    @Override
    public List<InventoryMovementModel> findByProductModelId(Long productId){
        return inventoryMovementRepository.findByProductModelId(productId);
    }

    @Override
    public Page<InventoryMovementModel> findByProductModelId(Long productId, Pageable pageable){
        return inventoryMovementRepository.findByProductModelId(productId, pageable);
    }

    @Override
    public List<InventoryMovementModel> findAll(){
        return inventoryMovementRepository.findAll();
    }

    @Override
    public Page<InventoryMovementModel> findAll(Pageable pageable){
        return inventoryMovementRepository.findAll(pageable);
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
