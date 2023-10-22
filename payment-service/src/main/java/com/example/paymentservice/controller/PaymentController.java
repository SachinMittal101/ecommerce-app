package com.example.paymentservice.controller;

import com.example.paymentservice.dto.PaymentDto;
import com.example.paymentservice.dto.PaymentStatusResponseDto;
import com.example.paymentservice.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/payment/")
@Slf4j
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @PostMapping("/process")
    public ResponseEntity<PaymentStatusResponseDto> processPayment(@RequestBody final PaymentDto paymentDto) {
        String orderNumber = paymentDto.getOrderNumber();
        log.info("eventName=ProductController, received payment processing request for orderNumber={}", orderNumber);
        try {
            PaymentStatusResponseDto paymentStatusResponseDto = paymentService.processPayment(paymentDto);
            return ResponseEntity.ok().body(paymentStatusResponseDto);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

}
