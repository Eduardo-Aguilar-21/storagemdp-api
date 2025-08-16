package com.ast.storagemdp_api.dto;

import com.ast.storagemdp_api.models.CategoryModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {
    private Long id;
    private String name;
    private Double price;
    private Integer quantity;
    private Long categoryId;
    private String categoryName;
}
