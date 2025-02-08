package com.example.paymentservice.clients;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.razorpay.Payment;
import com.razorpay.PaymentLink;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import com.razorpay.Refund;

@Component
public class RazorpayPaymentGatewayClient {

    @Autowired
    private RazorpayClient razorpayClient;

    // private final String paymentId = "pay_39QqoUAi66xm2f"; //use this paymentId, wherever needed

    public String createPaymentLink(
        Long orderId
        ,String currency
        ,Double amount
        ,String customerEmail
        ,String customerPhone
    ) throws RazorpayException {
        JSONObject paymentRequest = new JSONObject();
        paymentRequest.put("amount",amount * 100); // Razorpay expects amount in paise
        paymentRequest.put("currency",currency);
        paymentRequest.put("receipt", "order_" +orderId);
        paymentRequest.put("payment_capture", 1);

        JSONObject customer = new JSONObject();
        customer.put("email",customerEmail);
        customer.put("contact",customerPhone);
        paymentRequest.put("customer", customer);

        PaymentLink paymentLink = razorpayClient.paymentLink.create(paymentRequest);
        return paymentLink.get("short_url");
    }

    public Payment fetchPaymentDetails(String paymentId) throws RazorpayException{
        return razorpayClient.payments.fetch(paymentId);
    }
    
    public String issueInstantRefund(String paymentId, Double amount, String receipt) throws RazorpayException {
        JSONObject requestObject = new JSONObject();
        requestObject.put("amount", amount);
        requestObject.put("speed", "optimum");
        requestObject.put("receipt", receipt);
        
        JSONObject notes = new JSONObject();
        notes.put("note", "sample_note");
        
        Refund response = razorpayClient.payments.refund(paymentId, requestObject);
        return response.get("id");
    }

    public String updateRefund(String refundId,JSONObject jsonObject) throws RazorpayException {
        Refund response;
        response = razorpayClient.refunds.edit(refundId, jsonObject);
        
        return response.get("id");
        
    }
}
