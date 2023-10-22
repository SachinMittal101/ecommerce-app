package com.example.om.service.email;

import com.example.om.entity.OrderStatus;

public class EmailTemplateFactory {
    public EmailTemplate createEmailTemplate(OrderStatus status) {

        return switch (status) {
            case CREATED -> new OrderCreatedEmailTemplate();
            case SHIPPED -> new OrderShippedEmailTemplate();
            case DELIVERED -> new OrderDeliveredEmailTemplate();
            case CANCELED -> new OrderCanceledEmailTemplate();
            default -> throw new IllegalArgumentException("Unsupported order status");
        };
    }

}
