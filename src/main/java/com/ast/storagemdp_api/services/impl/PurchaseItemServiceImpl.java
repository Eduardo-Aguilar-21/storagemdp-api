package com.ast.storagemdp_api.services.impl;

import com.ast.storagemdp_api.dtos.PurchaseItemDTO;
import com.ast.storagemdp_api.mappers.PurchaseItemMapper;
import com.ast.storagemdp_api.models.ProductModel;
import com.ast.storagemdp_api.models.PurchaseItemModel;
import com.ast.storagemdp_api.models.PurchaseModel;
import com.ast.storagemdp_api.repositories.PurchaseItemRepository;
import com.ast.storagemdp_api.services.PurchaseItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PurchaseItemServiceImpl implements PurchaseItemService {

    @Autowired
    private PurchaseItemRepository purchaseItemRepository;

    @Override
    public PurchaseItemDTO getItemById(Long id) {
        PurchaseItemModel item = purchaseItemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Purchase item not found with id: " + id));
        return PurchaseItemMapper.toDTO(item);
    }

    @Override
    public PurchaseItemDTO createItem(PurchaseItemDTO itemDTO) {
        PurchaseItemModel entity = PurchaseItemMapper.toEntity(itemDTO);
        PurchaseItemModel saved = purchaseItemRepository.save(entity);
        return PurchaseItemMapper.toDTO(saved);
    }

    @Override
    public PurchaseItemDTO updateItem(Long id, PurchaseItemDTO itemDTO) {
        PurchaseItemModel existingItem = purchaseItemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Purchase item not found with id: " + id));

        existingItem.setQuantity(itemDTO.getQuantity());
        existingItem.setUnitPrice(itemDTO.getUnitPrice());

        if (itemDTO.getPurchaseId() != null) {
            PurchaseModel purchase = new PurchaseModel();
            purchase.setId(itemDTO.getPurchaseId());
            existingItem.setPurchase(purchase);
        }

        if (itemDTO.getProductId() != null) {
            ProductModel product = new ProductModel();
            product.setId(itemDTO.getProductId());
            existingItem.setProduct(product);
        }

        PurchaseItemModel updatedItem = purchaseItemRepository.save(existingItem);
        return PurchaseItemMapper.toDTO(updatedItem);
    }

    @Override
    public void deleteItem(Long id) {
        purchaseItemRepository.deleteById(id);
    }

    @Override
    public List<PurchaseItemDTO> getItemsByPurchase(PurchaseModel purchase) {
        return purchaseItemRepository.findByPurchase(purchase)
                .stream()
                .map(PurchaseItemMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<PurchaseItemDTO> getItemsByProduct(ProductModel product) {
        return purchaseItemRepository.findByProduct(product)
                .stream()
                .map(PurchaseItemMapper::toDTO)
                .collect(Collectors.toList());
    }
}
