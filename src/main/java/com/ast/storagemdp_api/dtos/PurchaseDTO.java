package com.ast.storagemdp_api.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PurchaseDTO {

    private Long id;

    private Long supplierId;
    private String supplierName;

    private LocalDate purchaseDate;
    private Double totalAmount;
    private String invoiceNumber;
    private String notes;
    private Boolean paid;

    private List<PurchaseItemDTO> items;

    private ZonedDateTime createdAt;
    private ZonedDateTime updatedAt;
}
