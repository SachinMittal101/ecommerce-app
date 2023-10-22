package com.example.paymentservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@Builder
@ToString
public class PaymentStatusResponseDto {

    private final String orderNumber;
    private final Long paymentId;
    private final double paymentAmount;
    private final String paymentStatus;
    private final String createdBy;
    private final LocalDateTime paymentTime;
}
