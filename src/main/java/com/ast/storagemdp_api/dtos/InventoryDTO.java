package com.ast.storagemdp_api.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InventoryDTO {
    private Long id;

    private Double price;
    private Integer quantity;

    private Long productId;
    private String productName;
    private String productBarcode;

    private Long companyId;
    private String companyName;

    private Long branchId;
    private String branchName;
}
