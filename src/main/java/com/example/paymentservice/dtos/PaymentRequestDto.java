package com.example.paymentservice.dtos;

import lombok.Data;

@Data
public class PaymentRequestDto {
    private Long orderId;
    private String currency;
    private Double amount;
    private String customerEmail;
    private String customerPhone;
}
