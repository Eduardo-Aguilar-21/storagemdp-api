package com.ast.storagemdp_api.services;

import com.ast.storagemdp_api.dtos.PurchaseItemDTO;
import com.ast.storagemdp_api.models.ProductModel;
import com.ast.storagemdp_api.models.PurchaseModel;

import java.util.List;

public interface PurchaseItemService {
    PurchaseItemDTO getItemById(Long id);
    PurchaseItemDTO createItem(PurchaseItemDTO itemDTO);
    PurchaseItemDTO updateItem(Long id, PurchaseItemDTO itemDTO);
    void deleteItem(Long id);

    List<PurchaseItemDTO> getItemsByPurchase(PurchaseModel purchase);
    List<PurchaseItemDTO> getItemsByProduct(ProductModel product);
}
