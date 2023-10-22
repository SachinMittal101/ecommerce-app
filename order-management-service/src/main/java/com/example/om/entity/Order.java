package com.example.om.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String orderNumber;
    @Column(name = "product_name")
    private String productName;
    @Column(name = "order_amount")
    private double orderAmount;
    @Column(name = "payment_method")
    private String paymentMethod;
    @Column(name = "payment_status")
    private String paymentStatus;

    @Column(name = "payment_time")
    private String paymentTime;

    @Column(name = "order_status")
    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    private String createdBy;
    @Version
    private Long version;

}
