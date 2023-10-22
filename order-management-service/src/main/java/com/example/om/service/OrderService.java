package com.example.om.service;

import com.example.om.client.PaymentServiceClient;
import com.example.om.client.ProductServiceClient;
import com.example.om.dto.PaymentStatusResponseDto;
import com.example.om.dto.ProductDto;
import com.example.om.entity.Order;
import com.example.om.entity.OrderStatus;
import com.example.om.repository.OrderRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@Slf4j
public class OrderService {

    @Autowired
    private ProductServiceClient productServiceClient;

    @Autowired
    private PaymentServiceClient paymentServiceClient;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private EmailService emailService;

    public Order createOrder(final String username,
                             final Order order) throws JsonProcessingException {
        Order savedOrder = fulfillOrder(username, order);
        sendOrderEmail(savedOrder);
        return savedOrder;
    }

    private void sendOrderEmail(Order savedOrder) {
        emailService.sendOrderEmail(savedOrder);
    }

    private Order fulfillOrder(String username, Order order) throws JsonProcessingException {
        ProductDto productDto = getProductDetailResponse(username, order);
        order.setProductName(productDto.getName());
        order.setCreatedBy(username);
        double orderAmount = order.getOrderAmount();
        String paymentMethod = order.getPaymentMethod();
        PaymentStatusResponseDto paymentDetailResponse = getPaymentDetailResponse(username, order, orderAmount, paymentMethod);
        if (Objects.isNull(paymentDetailResponse)) {
            order.setOrderAmount(orderAmount);
            order.setPaymentMethod(paymentMethod);
            order.setOrderStatus(OrderStatus.FAILED);
            return saveOrder(order);
        } else {
            order.setOrderAmount(orderAmount);
            order.setPaymentMethod(paymentMethod);
            order.setOrderStatus(OrderStatus.CREATED);
            return saveOrder(order);
        }
    }

    private Order saveOrder(final Order order) {
        log.info("eventName=createOrder,trying to create order in db");
        Order savedOrder = orderRepository.save(order);
        log.info("Order saved successfully");
        return savedOrder;
    }

    private PaymentStatusResponseDto getPaymentDetailResponse(String username, Order order, double orderAmount, String paymentMethod) throws JsonProcessingException {
        PaymentStatusResponseDto paymentStatusResponseDto = paymentServiceClient.processPayment(order.getOrderNumber(), orderAmount, paymentMethod,
                username);
        order.setPaymentStatus(paymentStatusResponseDto.getPaymentStatus());
        order.setPaymentTime(paymentStatusResponseDto.getPaymentTime());
        return null;
    }

    private ProductDto getProductDetailResponse(String username, Order order) throws JsonProcessingException {
        ProductDto productDto = productServiceClient.fetchProduct(username,
                order.getProductName());
        return productDto;
    }

    public List<Order> getOrdersByUserName(String username) {
        return orderRepository.findAll().stream().filter(order -> order.getCreatedBy().equalsIgnoreCase(username))
                .collect(Collectors.toList());
    }
}
