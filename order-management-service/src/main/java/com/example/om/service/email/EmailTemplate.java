package com.example.om.service.email;

import com.example.om.entity.Order;

public interface EmailTemplate {
    void sendEmail(final Order order);
}

