package com.ast.storagemdp_api.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InventoryMovementSummaryDTO {
    private Long totalEntries;
    private Long totalExits;
    private Long totalMovements;
}
