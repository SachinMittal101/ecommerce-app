package com.example.paymentservice.enums;

public enum PaymentMethod {

    DEBIT_CARD("DEBIT_CARD"),
    CREDIT_CARD("CREDIT_CARD"),
    UPI("UPI"),
    CASH("CASH");

    private String paymentType;

    PaymentMethod(String paymentType) {
        this.paymentType = paymentType;
    }

    private String getPaymentType(){
        return paymentType;
    }

}
