package com.example.paymentservice.service.payment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.paymentservice.clients.RazorpayPaymentGatewayClient;
import com.razorpay.Payment;
import com.razorpay.RazorpayException;

@Service
public class PaymentService implements IPaymentService{

    @Autowired
    RazorpayPaymentGatewayClient razorpayPaymentGatewayClient;

    @Override
    public String createPaymentLink(
        Long orderId
        ,String currency
        ,Double amount
        ,String customerEmail
        ,String customerPhone
    ) {
        try {
            return razorpayPaymentGatewayClient.createPaymentLink(orderId, currency, amount, customerEmail, customerPhone);
        } catch (RazorpayException e) {
            throw new RuntimeException("RazorPay: Payment link creation failed");
        }
    }

    @Override
    public Payment fetchPaymentDetails(String paymentId) {
        try {
            return razorpayPaymentGatewayClient.fetchPaymentDetails(paymentId);
        } catch (RazorpayException e) {
            throw new RuntimeException("RazorPay: Payment details fetch failed");
        }
    }
    
}
