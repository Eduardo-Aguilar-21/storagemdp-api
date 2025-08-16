package com.ast.storagemdp_api.services.impl;

import com.ast.storagemdp_api.dto.CategoryDTO;
import com.ast.storagemdp_api.mappers.CategoryMapper;
import com.ast.storagemdp_api.models.CategoryModel;
import com.ast.storagemdp_api.repositories.CategoryRepository;
import com.ast.storagemdp_api.services.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    @Override
    public Optional<CategoryModel> findById(Long id) {
        return categoryRepository.findById(id);
    }

    @Override
    public List<CategoryModel> findAll() {
        return categoryRepository.findAll();
    }

    @Override
    public Page<CategoryModel> findAll(Pageable pageable) {
        return categoryRepository.findAll(pageable);
    }

    @Override
    public CategoryDTO save(CategoryDTO categoryDTO) {
        CategoryModel entity = CategoryMapper.toEntity(categoryDTO);
        CategoryModel saved = categoryRepository.save(entity);
        return CategoryMapper.toDTO(saved);
    }

    @Override
    public CategoryModel update(Long id, CategoryDTO categoryDTO) {
        return categoryRepository.findById(id)
                .map(existing -> {
                    existing.setName(categoryDTO.getName());
                    existing.setDescription(categoryDTO.getDescription());
                    return categoryRepository.save(existing);
                })
                .orElse(null);
    }

    @Override
    public void deleteById(Long id) {
        categoryRepository.deleteById(id);
    }
}
