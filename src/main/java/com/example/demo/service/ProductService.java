package com.example.demo.service;

import com.example.demo.dto.ProductDto;
import com.example.demo.dto.UpdateStockDto;
import com.example.demo.entity.ProductEntity;
import com.example.demo.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }


    public List<ProductEntity> find(boolean isInStock) {
        if (isInStock) {
            // find all products with product > 0
            return findAllInStock();
        } else {
            // find all products
            return findAll();
        }
    }

    public List<ProductEntity> findAll() {
        return (List<ProductEntity>) productRepository.findAll();
    }

    public List<ProductEntity> findAllInStock() {
        return productRepository.findByStockGreaterThan(0);
    }

    public ProductEntity findById(long id) {
        return productRepository.findById(id).orElse(new ProductEntity());
    }

    public List<ProductEntity> findUnderPrice(long price) {
        return productRepository.findByPriceLessThanEqual(price);
    }

    public ProductEntity add(ProductDto request) {
        // dto to entity
        ProductEntity product = new ProductEntity();
        product.setName(request.getProductName());
        product.setStock(request.getStock());
        product.setPrice(request.getPrice());

        return productRepository.save(product);
    }

    public void delete(long id) {
        productRepository.deleteById(id);
    }

    public ProductEntity updateStock(UpdateStockDto request) {
        ProductEntity product = findById(request.getId());

        long newStock = product.getStock() + request.getStockUpdate();
        product.setStock(newStock);

        return productRepository.save(product);
    }
}
