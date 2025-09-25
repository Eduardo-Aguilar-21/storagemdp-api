package com.ast.storagemdp_api.services;

import com.ast.storagemdp_api.dtos.CategoryDTO;
import com.ast.storagemdp_api.models.CategoryModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface CategoryService {
    CategoryDTO findById(Long id);

    List<CategoryDTO> findAll();

    Page<CategoryDTO> findAll(Pageable pageable);

    CategoryDTO save(CategoryDTO categoryDTO);

    CategoryDTO update(Long id, CategoryDTO categoryDTO);

    void deleteById(Long id);
}
