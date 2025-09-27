package com.ast.storagemdp_api.mappers;

import com.ast.storagemdp_api.dtos.PromotionDTO;
import com.ast.storagemdp_api.models.BranchModel;
import com.ast.storagemdp_api.models.CategoryModel;
import com.ast.storagemdp_api.models.CompanyModel;
import com.ast.storagemdp_api.models.ProductModel;
import com.ast.storagemdp_api.models.PromotionModel;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class PromotionMapper {

    public static PromotionDTO toDTO(PromotionModel entity) {
        if (entity == null) {
            return null;
        }

        PromotionDTO dto = new PromotionDTO();
        dto.setId(entity.getId());
        dto.setType(entity.getType());
        dto.setName(entity.getName());
        dto.setDiscount(entity.getDiscount());
        dto.setStartDate(entity.getStartDate());
        dto.setEndDate(entity.getEndDate());
        dto.setStatus(entity.getStatus());
        dto.setCreatedBy(entity.getCreatedBy());
        dto.setCreatedAt(entity.getCreatedAt());
        dto.setUpdatedAt(entity.getUpdatedAt());

        // Mapear IDs de productos
        if (entity.getProducts() != null) {
            List<Long> productIds = entity.getProducts()
                    .stream()
                    .map(ProductModel::getId)
                    .collect(Collectors.toList());
            dto.setProductIds(productIds);
        } else {
            dto.setProductIds(Collections.emptyList());
        }

        // Mapear IDs de categorías
        if (entity.getCategories() != null) {
            List<Long> categoryIds = entity.getCategories()
                    .stream()
                    .map(CategoryModel::getId)
                    .collect(Collectors.toList());
            dto.setCategoryIds(categoryIds);
        } else {
            dto.setCategoryIds(Collections.emptyList());
        }

        // Mapear empresa y sucursal
        if (entity.getCompany() != null) {
            dto.setCompanyId(entity.getCompany().getId());
        }
        if (entity.getBranch() != null) {
            dto.setBranchId(entity.getBranch().getId());
        }

        return dto;
    }

    public static PromotionModel toEntity(PromotionDTO dto) {
        if (dto == null) {
            return null;
        }

        PromotionModel entity = new PromotionModel();
        entity.setId(dto.getId());
        entity.setType(dto.getType());
        entity.setName(dto.getName());
        entity.setDiscount(dto.getDiscount());
        entity.setStartDate(dto.getStartDate());
        entity.setEndDate(dto.getEndDate());
        entity.setStatus(dto.getStatus());
        entity.setCreatedBy(dto.getCreatedBy());

        // Mapear productos desde IDs (solo con ID, para evitar cargar entidades completas)
        if (dto.getProductIds() != null) {
            List<ProductModel> products = dto.getProductIds()
                    .stream()
                    .map(id -> {
                        ProductModel product = new ProductModel();
                        product.setId(id);
                        return product;
                    })
                    .collect(Collectors.toList());
            entity.setProducts(products);
        }

        // Mapear categorías desde IDs (solo con ID)
        if (dto.getCategoryIds() != null) {
            List<CategoryModel> categories = dto.getCategoryIds()
                    .stream()
                    .map(id -> {
                        CategoryModel category = new CategoryModel();
                        category.setId(id);
                        return category;
                    })
                    .collect(Collectors.toList());
            entity.setCategories(categories);
        }

        // Mapear empresa y sucursal desde IDs (solo con ID)
        if (dto.getCompanyId() != null) {
            CompanyModel company = new CompanyModel();
            company.setId(dto.getCompanyId());
            entity.setCompany(company);
        }
        if (dto.getBranchId() != null) {
            BranchModel branch = new BranchModel();
            branch.setId(dto.getBranchId());
            entity.setBranch(branch);
        }

        return entity;
    }
}
