package com.example.paymentservice.clients;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import com.razorpay.Refund;

@Component
public class RazorpayPaymentGatewayClient {

    @Autowired
    private RazorpayClient razorpayClient;

    private final String paymentId = "pay_39QqoUAi66xm2f"; //use this paymentId, wherever needed

    public String issueInstantRefund(Double amount, String receipt) {
        JSONObject requestObject = new JSONObject();
        requestObject.put("amount", amount);
        requestObject.put("speed", "optimum");
        requestObject.put("receipt", receipt);
        JSONObject notes = new JSONObject();
        notes.put("note", "sample_note");
        Refund response;
        try {
            response = razorpayClient.payments.refund(paymentId, requestObject);
        } catch (RazorpayException e) {
            throw new RuntimeException("Refund failed");
        }
        return response.get("id");
    }

    public String updateRefund(String refundId,JSONObject jsonObject) {
        Refund response;
        try {
            response = razorpayClient.refunds.edit(refundId, jsonObject);
        } catch (RazorpayException e) {
            throw new RuntimeException("Update failed");
        }
        return response.get("id");
        
    }

    // private final RestTemplate restTemplate = new RestTemplate();

    // public String issueInstantRefund(Double amount, String receipt) {
    //     String url = String.format("https://api.razorpay.com/v1/payments/%s/refund", paymentId);
    //     HttpHeaders headers = new HttpHeaders();
    //     headers.add(HttpHeaders.ACCEPT, "application/json");
    //     headers.add(HttpHeaders.CONTENT_TYPE, "application/json");

    //     HttpEntity request_entity = new HttpEntity<>(razorpayClient, headers);
    //     ResponseEntity<Object> res = restTemplate.exchange(url,HttpMethod.POST, request_entity, new ParameterizedTypeReference<Object>() {});
    //     Map<String, Object> responseBody = (Map<String, Object>) res.getBody();
    //     String refund_id = (String) responseBody.get("id");
    //     return refund_id;
    // }

    // public String updateRefund(String refundId,JSONObject jsonObject) {
    //     String url = String.format("https://api.razorpay.com/v1/refunds/%s", refundId);
    //     HttpHeaders headers = new HttpHeaders();
    //     headers.add(HttpHeaders.ACCEPT, "application/json");
    //     headers.add(HttpHeaders.CONTENT_TYPE, "application/json");

    //     HttpEntity request_entity = new HttpEntity<>(jsonObject, headers);
    //     ResponseEntity<Object> res = restTemplate.exchange(url,HttpMethod.PATCH, request_entity, new ParameterizedTypeReference<Object>() {});
    //     Map<String, Object> responseBody = (Map<String, Object>) res.getBody();
    //     String refund_id = (String) responseBody.get("id");
    //     return refund_id;
    // }
}
