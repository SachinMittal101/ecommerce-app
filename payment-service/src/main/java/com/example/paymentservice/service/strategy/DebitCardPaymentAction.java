package com.example.paymentservice.service.strategy;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DebitCardPaymentAction implements PaymentStrategy {
    @Override
    public boolean processPayment(double amount) {
        log.info("eventName=DebitCardPaymentAction, processing payment={}", amount);
        return true;
    }
}
