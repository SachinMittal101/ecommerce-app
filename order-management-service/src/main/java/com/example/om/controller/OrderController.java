package com.example.om.controller;

import com.example.om.entity.Order;
import com.example.om.service.OrderService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order/")
public class OrderController {

    @Autowired
    private OrderService service;

    @Autowired
    private ObjectMapper objectMapper;

    @GetMapping
    public ResponseEntity<String> getOrders(@RequestHeader("x-username") String username) throws JsonProcessingException {
        return ResponseEntity.ok().body(objectMapper.writeValueAsString(service.getOrdersByUserName(username)));
    }

    @PostMapping
    public ResponseEntity<String> createOrder(@RequestBody Order orderRequest,
                                              @RequestHeader("x-username") String username) throws JsonProcessingException {
        try {
            Order order = service.createOrder(username, orderRequest);
            return ResponseEntity.status(201).eTag(order.getVersion().toString()).build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

}
