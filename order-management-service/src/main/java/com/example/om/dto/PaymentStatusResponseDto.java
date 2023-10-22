package com.example.om.dto;

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
    private final double paymentAmount;
    private final String paymentStatus;
    private final String createdBy;
    private final String paymentTime;
}
