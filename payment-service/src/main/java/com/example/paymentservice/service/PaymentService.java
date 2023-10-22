package com.example.paymentservice.service;

import com.example.paymentservice.dto.PaymentDto;
import com.example.paymentservice.dto.PaymentStatusResponseDto;

public interface PaymentService {

    PaymentStatusResponseDto processPayment(final PaymentDto paymentDto);
}
