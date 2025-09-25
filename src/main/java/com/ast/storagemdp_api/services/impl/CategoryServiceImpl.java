package com.ast.storagemdp_api.services.impl;

import com.ast.storagemdp_api.dtos.CategoryDTO;
import com.ast.storagemdp_api.mappers.CategoryMapper;
import com.ast.storagemdp_api.models.CategoryModel;
import com.ast.storagemdp_api.repositories.CategoryRepository;
import com.ast.storagemdp_api.services.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    @Override
    public CategoryDTO findById(Long id) {
        CategoryModel category = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found with id: " + id));
        return CategoryMapper.toDTO(category);
    }

    @Override
    public List<CategoryDTO> findAll() {
        return categoryRepository.findAll()
                .stream()
                .map(CategoryMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Page<CategoryDTO> findAll(Pageable pageable) {
        Page<CategoryModel> page = categoryRepository.findAll(pageable);
        List<CategoryDTO> dtos = page.stream()
                .map(CategoryMapper::toDTO)
                .collect(Collectors.toList());
        return new PageImpl<>(dtos, pageable, page.getTotalElements());
    }

    @Override
    public CategoryDTO save(CategoryDTO categoryDTO) {
        CategoryModel entity = CategoryMapper.toEntity(categoryDTO);
        CategoryModel saved = categoryRepository.save(entity);
        return CategoryMapper.toDTO(saved);
    }

    @Override
    public CategoryDTO update(Long id, CategoryDTO categoryDTO) {
        return categoryRepository.findById(id)
                .map(existing -> {
                    existing.setName(categoryDTO.getName());
                    existing.setDescription(categoryDTO.getDescription());
                    CategoryModel updated = categoryRepository.save(existing);
                    return CategoryMapper.toDTO(updated);
                })
                .orElseThrow(() -> new RuntimeException("Category not found with id: " + id));
    }

    @Override
    public void deleteById(Long id) {
        if (!categoryRepository.existsById(id)) {
            throw new RuntimeException("Category not found with id: " + id);
        }
        categoryRepository.deleteById(id);
    }
}
