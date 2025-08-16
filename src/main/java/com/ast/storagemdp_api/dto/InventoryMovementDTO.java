package com.ast.storagemdp_api.dto;

import com.ast.storagemdp_api.enums.MovementType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InventoryMovementDTO {
    private Long id;
    private MovementType type;
    private Integer quantity;
    private Long productId;
    private String productName;
    private String createdBy;
    private ZonedDateTime createdAt;
    private ZonedDateTime updatedAt;
}
