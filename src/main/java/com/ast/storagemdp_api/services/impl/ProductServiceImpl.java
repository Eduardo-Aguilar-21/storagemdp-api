package com.ast.storagemdp_api.services.impl;

import com.ast.storagemdp_api.dtos.ProductDTO;
import com.ast.storagemdp_api.mappers.ProductMapper;
import com.ast.storagemdp_api.models.ProductModel;
import com.ast.storagemdp_api.repositories.ProductRepository;
import com.ast.storagemdp_api.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override
    public ProductDTO findById(Long id) {
        ProductModel entity = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + id));
        return ProductMapper.toDTO(entity);
    }

    @Override
    public List<ProductDTO> findAll() {
        return productRepository.findAll()
                .stream()
                .map(ProductMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Page<ProductDTO> findAll(Pageable pageable) {
        Page<ProductModel> page = productRepository.findAll(pageable);
        List<ProductDTO> dtos = page.stream()
                .map(ProductMapper::toDTO)
                .collect(Collectors.toList());
        return new PageImpl<>(dtos, pageable, page.getTotalElements());
    }

    @Override
    public ProductDTO save(ProductDTO productDTO) {
        ProductModel entity = ProductMapper.toEntity(productDTO);
        ProductModel saved = productRepository.save(entity);
        return ProductMapper.toDTO(saved);
    }

    @Override
    public ProductDTO update(Long id, ProductDTO productDTO) {
        return productRepository.findById(id)
                .map(existing -> {
                    existing.setName(productDTO.getName());
                    existing.setPrice(productDTO.getPrice());
                    existing.setQuantity(productDTO.getQuantity());
                    // actualizar categoría si viene
                    if (productDTO.getCategoryId() != null) {
                        existing.getCategory().setId(productDTO.getCategoryId());
                    }
                    ProductModel updated = productRepository.save(existing);
                    return ProductMapper.toDTO(updated);
                })
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + id));
    }

    @Override
    public void deleteById(Long id) {
        if (!productRepository.existsById(id)) {
            throw new RuntimeException("Product not found with id: " + id);
        }
        productRepository.deleteById(id);
    }

    @Override
    public List<ProductDTO> findByCategoryId(Long categoryId) {
        return productRepository.findByCategoryModelId(categoryId)
                .stream()
                .map(ProductMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Page<ProductDTO> findByCategoryId(Long categoryId, Pageable pageable) {
        Page<ProductModel> page = productRepository.findByCategoryModelId(categoryId, pageable);
        List<ProductDTO> dtos = page.stream()
                .map(ProductMapper::toDTO)
                .collect(Collectors.toList());
        return new PageImpl<>(dtos, pageable, page.getTotalElements());
    }
}
