package com.ast.storagemdp_api.controllers;

import com.ast.storagemdp_api.dtos.PurchaseItemDTO;
import com.ast.storagemdp_api.models.ProductModel;
import com.ast.storagemdp_api.models.PurchaseModel;
import com.ast.storagemdp_api.services.PurchaseItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/purchase-items")
public class PurchaseItemController {

    @Autowired
    private PurchaseItemService purchaseItemService;

    @GetMapping("/{id}")
    public PurchaseItemDTO getItemById(@PathVariable Long id) {
        return purchaseItemService.getItemById(id);
    }

    @PostMapping
    public PurchaseItemDTO createItem(@RequestBody PurchaseItemDTO itemDTO) {
        return purchaseItemService.createItem(itemDTO);
    }

    @PutMapping("/{id}")
    public PurchaseItemDTO updateItem(@PathVariable Long id, @RequestBody PurchaseItemDTO itemDTO) {
        return purchaseItemService.updateItem(id, itemDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteItem(@PathVariable Long id) {
        purchaseItemService.deleteItem(id);
    }

    @GetMapping("/purchase/{purchaseId}")
    public List<PurchaseItemDTO> getItemsByPurchase(@PathVariable Long purchaseId) {
        PurchaseModel purchase = new PurchaseModel();
        purchase.setId(purchaseId);
        return purchaseItemService.getItemsByPurchase(purchase);
    }

    @GetMapping("/product/{productId}")
    public List<PurchaseItemDTO> getItemsByProduct(@PathVariable Long productId) {
        ProductModel product = new ProductModel();
        product.setId(productId);
        return purchaseItemService.getItemsByProduct(product);
    }
}
