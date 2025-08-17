package com.ast.storagemdp_api.services;

import com.ast.storagemdp_api.dto.CategoryDTO;
import com.ast.storagemdp_api.models.CategoryModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface CategoryService {
    Optional<CategoryModel> findById(Long id);

    List<CategoryModel> findAll();

    Page<CategoryModel> findAll(Pageable pageable);

    CategoryDTO save(CategoryDTO categoryDTO);

    CategoryDTO update(Long id, CategoryDTO categoryDTO);

    void deleteById(Long id);
}
