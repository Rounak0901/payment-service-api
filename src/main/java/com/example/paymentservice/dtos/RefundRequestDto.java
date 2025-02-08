package com.example.paymentservice.dtos;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class RefundRequestDto {
    String paymentId;
    Double amount;
    String receipt;
    RefundSpeed refundSpeed;
}