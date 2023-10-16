package com.example.om.service;

import com.example.om.client.ProductServiceClient;
import com.example.om.dto.ProductDto;
import com.example.om.entity.Order;
import com.example.om.repository.OrderRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OrderServiceTest {

    @InjectMocks
    private OrderService orderService;

    @Mock
    private ProductServiceClient productServiceClient;

    @Mock
    private OrderRepository orderRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateOrder() throws JsonProcessingException {
        String username = "testUser";
        Order order = new Order();
        order.setProductName("Test Product");
        ProductDto productDto = new ProductDto();
        productDto.setName("Test Product");

        // Mock the ProductServiceClient to return the product DTO
        Mockito.when(productServiceClient.fetchProduct(username, order.getProductName()))
                .thenReturn(productDto);

        // Mock the OrderRepository to return the saved order
        Mockito.when(orderRepository.save(order)).thenReturn(order);

        Order savedOrder = orderService.createOrder(username, order);

        // Verify that the product name and createdBy are set correctly
        assertEquals("Test Product", savedOrder.getProductName());
        assertEquals("testUser", savedOrder.getCreatedBy());
    }

    @Test
    public void testGetOrdersByUserName() {
        String username = "testUser";
        Order order1 = new Order();
        order1.setCreatedBy(username);
        Order order2 = new Order();
        order2.setCreatedBy("anotherUser");

        List<Order> orders = new ArrayList<>();
        orders.add(order1);
        orders.add(order2);

        // Mock the OrderRepository to return the list of orders
        Mockito.when(orderRepository.findAll()).thenReturn(orders);

        List<Order> userOrders = orderService.getOrdersByUserName(username);

        // Verify that only orders created by the specified user are returned
        assertEquals(1, userOrders.size());
        assertEquals("testUser", userOrders.get(0).getCreatedBy());
    }
}
