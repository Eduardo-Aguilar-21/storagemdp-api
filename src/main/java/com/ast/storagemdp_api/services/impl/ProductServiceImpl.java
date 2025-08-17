package com.ast.storagemdp_api.services.impl;

import com.ast.storagemdp_api.dto.ProductDTO;
import com.ast.storagemdp_api.mappers.ProductMapper;
import com.ast.storagemdp_api.models.ProductModel;
import com.ast.storagemdp_api.repositories.ProductRepository;
import com.ast.storagemdp_api.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    @Override
    public Optional<ProductModel> findById(Long id){
        return productRepository.findById(id);
    }

    @Override
    public List<ProductModel> findByCategoryModelId(Long categoryId){
        return productRepository.findByCategoryModelId(categoryId);
    }

    @Override
    public Page<ProductModel> findByCategoryModelId(Long categoryId, Pageable pageable){
        return productRepository.findByCategoryModelId(categoryId, pageable);
    }

    @Override
    public List<ProductModel> findAll(){
        return productRepository.findAll();
    }

    @Override
    public Page<ProductModel> findAll(Pageable pageable){
        return productRepository.findAll(pageable);
    }

    @Override
    public ProductDTO save(ProductDTO productDTO) {
        ProductModel entity = ProductMapper.toEntity(productDTO);
        ProductModel saved = productRepository.save(entity);
        return ProductMapper.toDTO(saved);
    }

    @Override
    public ProductDTO update(Long id, ProductDTO productDTO){
        return productRepository.findById(id)
                .map(existing -> {
                    existing.setName(productDTO.getName());
                    existing.setPrice(productDTO.getPrice());
                    ProductModel updated = productRepository.save(existing);
                    return ProductMapper.toDTO(updated);
                })
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + id));
    }

    @Override
    public void deleteById(Long id){
        if (!productRepository.existsById(id)) {
            throw new RuntimeException("Product not found with id: " + id);
        }
        productRepository.deleteById(id);
    }
}
