package com.ast.storagemdp_api.repositories;

import com.ast.storagemdp_api.models.PurchaseItemModel;
import com.ast.storagemdp_api.models.PurchaseModel;
import com.ast.storagemdp_api.models.ProductModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PurchaseItemRepository extends JpaRepository<PurchaseItemModel, Long> {

    // Listar items por compra
    List<PurchaseItemModel> findByPurchase(PurchaseModel purchase);

    // Listar items por producto
    List<PurchaseItemModel> findByProduct(ProductModel product);
}
