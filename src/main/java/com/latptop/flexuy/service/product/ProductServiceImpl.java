package com.latptop.flexuy.service.product;

import java.util.List;

import org.springframework.stereotype.Service;

import com.latptop.flexuy.model.Product;
import com.latptop.flexuy.repository.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }
    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }
}
