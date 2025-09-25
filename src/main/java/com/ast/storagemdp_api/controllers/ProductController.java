package com.ast.storagemdp_api.controllers;

import com.ast.storagemdp_api.dtos.ProductDTO;
import com.ast.storagemdp_api.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> findById(@PathVariable Long id) {
        ProductDTO product = productService.findById(id);
        return ResponseEntity.ok(product);
    }

    @GetMapping
    public ResponseEntity<List<ProductDTO>> findAll() {
        List<ProductDTO> products = productService.findAll();
        return products.isEmpty()
                ? ResponseEntity.noContent().build()
                : ResponseEntity.ok(products);
    }

    @GetMapping("/page")
    public ResponseEntity<Page<ProductDTO>> findAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        Page<ProductDTO> productDTOPage = productService.findAll(pageable);
        return productDTOPage.isEmpty()
                ? ResponseEntity.noContent().build()
                : ResponseEntity.ok(productDTOPage);
    }

    @PostMapping
    public ResponseEntity<ProductDTO> save(@RequestBody ProductDTO productDTO) {
        ProductDTO saved = productService.save(productDTO);
        return ResponseEntity.ok(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductDTO> update(@PathVariable Long id, @RequestBody ProductDTO productDTO) {
        ProductDTO updated = productService.update(id, productDTO);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        productService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/category/{categoryId}")
    public ResponseEntity<List<ProductDTO>> findByCategoryId(@PathVariable Long categoryId) {
        List<ProductDTO> products = productService.findByCategoryId(categoryId);
        return products.isEmpty()
                ? ResponseEntity.noContent().build()
                : ResponseEntity.ok(products);
    }

    @GetMapping("/category/{categoryId}/page")
    public ResponseEntity<Page<ProductDTO>> findByCategoryId(
            @PathVariable Long categoryId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        Page<ProductDTO> productDTOPage = productService.findByCategoryId(categoryId, pageable);
        return productDTOPage.isEmpty()
                ? ResponseEntity.noContent().build()
                : ResponseEntity.ok(productDTOPage);
    }
}
