package com.example.om.service;

import com.example.om.entity.Order;
import com.example.om.service.email.EmailTemplate;
import com.example.om.service.email.EmailTemplateFactory;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
    private EmailTemplateFactory emailTemplateFactory;

    public EmailService() {
        this.emailTemplateFactory = new EmailTemplateFactory();
    }

    public void sendOrderEmail(final Order order) {
        EmailTemplate emailTemplate = emailTemplateFactory.createEmailTemplate(order.getOrderStatus());
        emailTemplate.sendEmail(order);
    }
}

