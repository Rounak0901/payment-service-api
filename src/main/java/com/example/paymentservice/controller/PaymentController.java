package com.example.paymentservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.paymentservice.dtos.PaymentRequestDto;
import com.example.paymentservice.service.payment.IPaymentService;
import com.razorpay.Payment;

@RestController("/payments")
public class PaymentController {

    @Autowired
    private IPaymentService paymentService;

    @PostMapping("/create")
    public ResponseEntity<String> createPayment(@RequestBody PaymentRequestDto request) {
        try {
            String paymentUrl = paymentService.createPaymentLink(
                request.getOrderId()
                ,request.getCurrency() 
                ,request.getAmount()
                ,request.getCustomerEmail()
                ,request.getCustomerPhone()
            );
            return ResponseEntity.ok(paymentUrl);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error creating payment");
        }
    }

    
    @GetMapping("/{paymentId}")
    public ResponseEntity<String> getPaymentDetails(@PathVariable String paymentId) {
        try {
            Payment payment = paymentService.fetchPaymentDetails(paymentId);
            return ResponseEntity.ok(payment.toString());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error fetching payment details");
        }
    }

}
