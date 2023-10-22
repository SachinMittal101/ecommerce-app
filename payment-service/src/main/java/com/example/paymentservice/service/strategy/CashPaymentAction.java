package com.example.paymentservice.service.strategy;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CashPaymentAction implements PaymentStrategy {
    @Override
    public boolean processPayment(double amount) {
        log.error("eventName=CashPaymentAction, payment method not supported yet");
        return false;
    }
}
