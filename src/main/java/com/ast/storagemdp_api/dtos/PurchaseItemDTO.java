package com.ast.storagemdp_api.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PurchaseItemDTO {

    private Long id;
    private Long purchaseId;
    private Long productId;
    private String productName;
    private Integer quantity;
    private Double unitPrice;
}
