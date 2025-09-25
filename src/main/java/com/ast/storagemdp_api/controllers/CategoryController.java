package com.ast.storagemdp_api.controllers;

import com.ast.storagemdp_api.dtos.CategoryDTO;
import com.ast.storagemdp_api.mappers.CategoryMapper;
import com.ast.storagemdp_api.models.CategoryModel;
import com.ast.storagemdp_api.services.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/category")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;

    @GetMapping("/{id}")
    public ResponseEntity<CategoryDTO> findById(@PathVariable Long id){
        try {
            CategoryDTO category = categoryService.findById(id);
            return ResponseEntity.ok(category);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<CategoryDTO>> findAll(){
        List<CategoryDTO> categories = categoryService.findAll();
        return categories.isEmpty()
                ? ResponseEntity.noContent().build()
                : ResponseEntity.ok(categories);
    }


    @GetMapping("/page")
    public ResponseEntity<Page<CategoryDTO>> findAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<CategoryDTO> categoryDTOPage = categoryService.findAll(pageable);
        return categoryDTOPage.isEmpty()
                ? ResponseEntity.noContent().build()
                : ResponseEntity.ok(categoryDTOPage);
    }

    @PostMapping
    public ResponseEntity<CategoryDTO> save(@RequestBody CategoryDTO categoryDTO){
        CategoryDTO saved  = categoryService.save(categoryDTO);
        return ResponseEntity.ok(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoryDTO> update(@PathVariable Long id, @RequestBody CategoryDTO categoryDTO) {
        CategoryDTO updated = categoryService.update(id, categoryDTO);
        if (updated == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        try {
            categoryService.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

}
