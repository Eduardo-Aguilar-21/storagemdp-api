package com.ast.storagemdp_api.repositories;

import com.ast.storagemdp_api.models.ProductModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<ProductModel, Long> {
    List<ProductModel> findByCategoryModelId(Long categoryId);

    Page<ProductModel> findByCategoryModelId(Long categoryId, Pageable pageable);
}
