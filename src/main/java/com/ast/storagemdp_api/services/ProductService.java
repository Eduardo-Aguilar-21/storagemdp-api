package com.ast.storagemdp_api.services;

import com.ast.storagemdp_api.dtos.ProductDTO;
import com.ast.storagemdp_api.models.ProductModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    ProductDTO findById(Long id);

    List<ProductDTO> findAll();

    Page<ProductDTO> findAll(Pageable pageable);

    ProductDTO save(ProductDTO productDTO);

    ProductDTO update(Long id, ProductDTO productDTO);

    void deleteById(Long id);

    List<ProductDTO> findByCategoryId(Long categoryId);

    Page<ProductDTO> findByCategoryId(Long categoryId, Pageable pageable);
}
