package com.ast.storagemdp_api.services;

import com.ast.storagemdp_api.dto.ProductDTO;
import com.ast.storagemdp_api.models.ProductModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    Optional<ProductModel> findById(Long id);
    List<ProductModel> findByCategoryModelId(Long categoryId);
    Page<ProductModel> findByCategoryModelId(Long categoryId, Pageable pageable);
    List<ProductModel> findAll();
    Page<ProductModel> findAll(Pageable pageable);
    ProductDTO save(ProductDTO productDTO);
    ProductModel update(Long id, ProductDTO productDTO);
    void deleteById(Long id);
}
