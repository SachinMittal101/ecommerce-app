package com.example.paymentservice.service.strategy;

import com.example.paymentservice.dto.PaymentDto;
import com.example.paymentservice.enums.PaymentMethod;

import java.util.EnumMap;

public class PaymentAction {

    private final EnumMap<PaymentMethod, PaymentStrategy> PAYMENT_METHOD_MAP = new EnumMap<>(PaymentMethod.class);

    public PaymentAction() {
        PAYMENT_METHOD_MAP.put(PaymentMethod.DEBIT_CARD, new DebitCardPaymentAction());
        PAYMENT_METHOD_MAP.put(PaymentMethod.CREDIT_CARD, new CreditCardPaymentAction());
        PAYMENT_METHOD_MAP.put(PaymentMethod.UPI, new UpiPaymentAction());
        PAYMENT_METHOD_MAP.put(PaymentMethod.CASH, new DebitCardPaymentAction());
    }

    public boolean executeAction(final PaymentDto paymentDto) {
        String paymentMethod = paymentDto.getPaymentMethod();
        try {
            return PAYMENT_METHOD_MAP.get(PaymentMethod.valueOf(paymentMethod))
                    .processPayment(paymentDto.getPaymentAmount());
        } catch (final IllegalArgumentException exception) {
            throw new RuntimeException("received invalid payment method" + paymentMethod);
        }

    }

}
