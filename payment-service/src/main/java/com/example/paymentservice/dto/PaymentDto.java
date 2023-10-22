package com.example.paymentservice.dto;

import com.example.paymentservice.entity.Payment;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PaymentDto {

    private final String orderNumber;
    private final double paymentAmount;
    private final String paymentMethod;
    private final String createdBy;

    @JsonCreator
    public PaymentDto(@JsonProperty("orderNumber") final String orderNumber,
                      @JsonProperty("paymentAmount") final double paymentAmount,
                      @JsonProperty("paymentMethod") final String paymentMethod,
                      @JsonProperty("username") final String createdBy) {
        this.orderNumber = orderNumber;
        this.paymentAmount = paymentAmount;
        this.paymentMethod = paymentMethod;
        this.createdBy = createdBy;
    }

    public static Payment toEntity(final PaymentDto paymentDto) {
        Payment payment = new Payment();
        payment.setOrderNumber(paymentDto.getOrderNumber());
        payment.setPaymentMethod(paymentDto.paymentMethod);
        payment.setPaymentAmount(paymentDto.getPaymentAmount());
        payment.setCreatedBy(paymentDto.getCreatedBy());
        return payment;
    }

}
