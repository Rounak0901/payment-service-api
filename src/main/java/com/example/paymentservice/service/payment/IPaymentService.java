package com.example.paymentservice.service.payment;

import com.razorpay.Payment;

public interface IPaymentService {
    public String createPaymentLink(
        Long orderId
        ,String currency
        ,Double amount
        ,String customerEmail
        ,String customerPhone
    );

    public Payment fetchPaymentDetails(String paymentId);
}
