package com.example.productservice.service;

import com.example.productservice.entity.Product;
import com.example.productservice.repository.ProductRepository;
import com.example.productservice.service.ProductService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import java.util.ArrayList;
import java.util.List;

public class ProductServiceTest {

    @InjectMocks
    private ProductService productService;

    @Mock
    private ProductRepository productRepository;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testSaveProduct() {
        Product product = new Product();
        Mockito.when(productRepository.save(product)).thenReturn(product);

        Product savedProduct = productService.saveProduct(product);

        // Assert that the saved product matches the input product
        Assertions.assertEquals(product, savedProduct);
    }

    @Test
    public void testUpdateProduct() {
        Product product = new Product();
        Mockito.when(productRepository.save(product)).thenReturn(product);

        Product updatedProduct = productService.updateProduct(product);

        // Assert that the updated product matches the input product
        Assertions.assertEquals(product, updatedProduct);
    }

    @Test
    public void testGetAllProducts() {
        List<Product> productList = new ArrayList<>();
        Mockito.when(productRepository.findAll()).thenReturn(productList);

        List<Product> retrievedProducts = productService.getAllProducts();

        // Assert that the retrieved products match the mocked list
        Assertions.assertEquals(productList, retrievedProducts);
    }

    @Test
    public void testGetProductById() {
        Long productId = 1L;
        Product product = new Product();
        Mockito.when(productRepository.getReferenceById(productId)).thenReturn(product);

        Product retrievedProduct = productService.getProductById(productId);

        // Assert that the retrieved product matches the mocked product
        Assertions.assertEquals(product, retrievedProduct);
    }

    @Test
    public void testGetAllProductsByUserName() {
        String username = "test-user";
        List<Product> productList = new ArrayList<>();
        productList.add(new Product(1L, "test-product", "test-brand", "test-user", 1));
        // Mock products with the specified username
        List<Product> productsWithUsername = new ArrayList<>();
        productsWithUsername.add(new Product(1L, "test-product", "test-brand", "test-user", 1));
        Mockito.when(productRepository.findAll()).thenReturn(productList);

        List<Product> retrievedProducts = productService.getAllProducts(username);

        // Assert that the retrieved products match the mocked products with the specified username
        Assertions.assertEquals(productsWithUsername, retrievedProducts);
    }

    @Test
    public void testGetAllProductsByUserNameAndProductName() {
        String username = "test-user";
        String productName = "test-product";
        List<Product> productList = new ArrayList<>();
        productList.add(new Product(1L, "test-product", "test-brand", "test-user", 1));
        // Mock products with the specified username and product name
        List<Product> productsWithUsernameAndProductName = new ArrayList<>();
        productsWithUsernameAndProductName.add(new Product(1L, "test-product", "test-brand", "test-user", 1));
        Mockito.when(productRepository.findAll()).thenReturn(productList);

        List<Product> retrievedProducts = productService.getAllProductsByUserNameAndProductName(username, productName);

        // Assert that the retrieved products match the mocked products with the specified username and product name
        Assertions.assertEquals(productsWithUsernameAndProductName, retrievedProducts);
    }
}
   
