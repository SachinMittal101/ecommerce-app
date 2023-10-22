package com.example.paymentservice.service.strategy;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class UpiPaymentAction implements PaymentStrategy {
    @Override
    public boolean processPayment(double amount) {
        log.error("eventName=UpiPaymentAction, payment method not supported yet");
        return false;
    }
}
