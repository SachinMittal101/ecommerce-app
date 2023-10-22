package com.example.om.service.email;

import com.example.om.entity.Order;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class OrderDeliveredEmailTemplate implements EmailTemplate {
    @Override
    public void sendEmail(Order order) {
        // Generate email content for order created
        log.info("Dear Customer, your order #{} has been delivered.",  order.getOrderNumber());
    }
}
