package com.example.paymentservice.service;

import com.example.paymentservice.dto.PaymentDto;
import com.example.paymentservice.dto.PaymentStatusResponseDto;
import com.example.paymentservice.entity.Payment;
import com.example.paymentservice.repository.PaymentRepository;
import com.example.paymentservice.service.strategy.PaymentAction;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@Slf4j
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    public PaymentStatusResponseDto processPayment(final PaymentDto paymentDto) {
        boolean isExecuted = new PaymentAction().executeAction(paymentDto);
        if (isExecuted) {
            Payment payment = PaymentDto.toEntity(paymentDto);
            payment.setPaymentStatus("SUCCESS");
            payment.setPaymentTime(LocalDateTime.now());
            log.info("eventName=processPayment,trying to save payment details in db={}", payment);
            Payment savedPayment = paymentRepository.save(payment);
            log.info("eventName=processPayment,successfully saved payment details in db");
            return PaymentStatusResponseDto.builder().orderNumber(savedPayment.getOrderNumber())
                    .paymentStatus(savedPayment.getPaymentStatus())
                    .paymentAmount(savedPayment.getPaymentAmount())
                    .createdBy(savedPayment.getCreatedBy())
                    .paymentTime(savedPayment.getPaymentTime()).build();
        } else {
            log.error("eventName=processPayment,failed while doing payment processing");
            throw new RuntimeException("received exception while doing payment processing");
        }
    }
}
