package com.example.om.service.email;

import com.example.om.entity.Order;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class OrderShippedEmailTemplate implements EmailTemplate {
    @Override
    public void sendEmail(Order order) {
        log.info("Dear Customer, your order #{} has been shipped.",  order.getOrderNumber());
    }
}
