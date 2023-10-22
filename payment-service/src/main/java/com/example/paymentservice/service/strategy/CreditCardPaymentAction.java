package com.example.paymentservice.service.strategy;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CreditCardPaymentAction implements PaymentStrategy {
    @Override
    public boolean processPayment(double amount) {
        log.info("eventName=CreditCardPaymentAction, processing payment={}", amount);
        return true;
    }
}
