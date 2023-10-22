package com.example.om.client;

import com.example.om.dto.PaymentDto;
import com.example.om.dto.PaymentStatusResponseDto;
import com.example.om.dto.ProductDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
@Slf4j
public class PaymentServiceClient {

    @Autowired
    private RestTemplate template;

    @Autowired
    private ObjectMapper objectMapper;

    @CircuitBreaker(name = "paymentService", fallbackMethod = "fallback")
    public PaymentStatusResponseDto processPayment(final String orderNumber,
                                     final double paymentAmount,
                                     final String paymentMethod,
                                     final String username) throws JsonProcessingException {
        final PaymentDto paymentDto = PaymentDto.builder().orderNumber(orderNumber)
                .paymentAmount(paymentAmount)
                .paymentMethod(paymentMethod)
                .createdBy(username).build();
        HttpEntity<PaymentDto> requestEntity = new HttpEntity<>(paymentDto);
        log.info("Trying to call payment service for payment processing");
        ResponseEntity<String> response = template.exchange("http://PAYMENT-SERVICE/payment/process",
                HttpMethod.POST, requestEntity, String.class);
        String responseBody = response.getBody();
        log.info("Received response from payment service:{}", responseBody);
        final PaymentStatusResponseDto paymentStatusResponseDto = objectMapper.readValue(responseBody,
                PaymentStatusResponseDto.class);
        log.info("paymentStatusResponseDto={}", paymentStatusResponseDto);
        return paymentStatusResponseDto;
    }

    public PaymentStatusResponseDto fallback(Exception e) {
        log.error("Payment service is unavailable. Returning a default response.");
        return PaymentStatusResponseDto.builder().build();
    }
}
