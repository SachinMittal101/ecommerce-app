package com.example.productservice.controller;

import com.example.productservice.entity.Product;
import com.example.productservice.service.ProductService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private ObjectMapper objectMapper;

    @GetMapping("/")
    public ResponseEntity<String> getProducts(@RequestHeader("x-username") String username) throws JsonProcessingException {
        List<Product> products = productService.getAllProductsByUserName(username);
        return ResponseEntity.ok().body(objectMapper.writeValueAsString(products));
    }

    @GetMapping("/{name}")
    public ResponseEntity<String> getProductByName(@RequestHeader("x-username") String username,
                                                   @PathVariable String name) throws JsonProcessingException {
        List<Product> products = productService.getAllProductsByUserNameAndProductName(username, name);
        return ResponseEntity.ok().body(objectMapper.writeValueAsString(products));
    }

    @PostMapping("/add")
    public String createProduct(@RequestBody Product product,
                                @RequestHeader("x-username") String username) {
        product.setCreatedBy(username);
        Product savedProduct = productService.saveProduct(product);
        return "Product saved successfully";
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateProduct(@PathVariable Long id,
                                                @RequestBody Product product,
                                                @RequestHeader("x-username") String username) {
        Product existingProduct = productService.getProductById(id);
        if (existingProduct == null) {
            return ResponseEntity.notFound().build();
        }

        existingProduct.setId(id);
        existingProduct.setName(product.getName());
        existingProduct.setBrand(product.getBrand());
        existingProduct.setCreatedBy(username);

        try {
            Product updatedProduct = productService.updateProduct(existingProduct);
            return ResponseEntity.ok("resource added successfully");
        } catch (OptimisticLockingFailureException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Optimistic locking conflict");
        }
    }
}
