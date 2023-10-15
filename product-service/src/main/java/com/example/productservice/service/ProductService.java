package com.example.productservice.service;

import com.example.productservice.entity.Product;
import com.example.productservice.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    public Product updateProduct(Product product) {
        return productRepository.save(product);
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product getProductById(Long id) {
        return productRepository.getReferenceById(id);
    }

    public List<Product> getAllProductsByUserName(final String username) {
        List<Product> products = productRepository.findAll();
        List<Product> productsByUserName = products.stream().filter(product ->
                product.getCreatedBy().equalsIgnoreCase(username)).collect(Collectors.toList());
        return productsByUserName;
    }

    public List<Product> getAllProductsByUserNameAndProductName(final String username,
                                                                final String name) {
        List<Product> products = productRepository.findAll();
        List<Product> productsByUserName = products.stream().filter(product ->
                product.getCreatedBy().equalsIgnoreCase(username) && product.getName().equalsIgnoreCase(name))
                .collect(Collectors.toList());
        return productsByUserName;
    }
}

