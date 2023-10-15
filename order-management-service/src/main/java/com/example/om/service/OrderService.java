package com.example.om.service;

import com.example.om.client.ProductServiceClient;
import com.example.om.dto.ProductDto;
import com.example.om.entity.Order;
import com.example.om.repository.OrderRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {

    @Autowired
    private ProductServiceClient productServiceClient;

    @Autowired
    private OrderRepository orderRepository;

    public Order createOrder(final String username,
                            final Order order) throws JsonProcessingException {
        ProductDto productDto = productServiceClient.fetchProduct(username,
                order.getProductName());
        order.setProductName(productDto.getName());
        order.setCreatedBy(username);
        Order savedOrder = orderRepository.save(order);
        System.out.println("Order saved successfully");
        return savedOrder;
    }

    public List<Order> getOrdersByUserName(String username) {
        return orderRepository.findAll().stream().filter(order -> order.getCreatedBy().equalsIgnoreCase(username))
                .collect(Collectors.toList());
    }
}
