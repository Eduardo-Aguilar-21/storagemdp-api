package com.ast.storagemdp_api.dtos;

import com.ast.storagemdp_api.enums.PromotionStatus;
import com.ast.storagemdp_api.enums.PromotionType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PromotionDTO {

    private Long id;

    private PromotionType type; // PRODUCT, CATEGORY, ORDER

    private String name;

    private Double discount;

    private ZonedDateTime startDate;

    private ZonedDateTime endDate;

    private PromotionStatus status; // ACTIVE, EXPIRED, INACTIVE

    private String createdBy;

    private Long companyId; // ID de la empresa

    private Long branchId; // ID de la sucursal (opcional)

    private List<Long> productIds; // IDs de productos asociados

    private List<Long> categoryIds; // IDs de categorías asociadas

    private ZonedDateTime createdAt;

    private ZonedDateTime updatedAt;
}
