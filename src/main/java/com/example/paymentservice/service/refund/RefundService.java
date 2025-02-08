package com.example.paymentservice.service.refund;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.paymentservice.clients.RazorpayPaymentGatewayClient;

@Service
public class RefundService implements IRefundService {

    @Autowired
    private RazorpayPaymentGatewayClient razorpayPaymentGatewayClient;

    public String issueRefund(String paymentId, Double amount, String receipt) {
      try {
        return razorpayPaymentGatewayClient.issueInstantRefund(paymentId, amount, receipt);
      } catch (Exception e) {
        e.printStackTrace();
      }

      return "Incorrect request";
    }

    public String updateRefund(String refundId, JSONObject jsonObject) {
      try {
        return razorpayPaymentGatewayClient.updateRefund(refundId, jsonObject);
      } catch (Exception e) {
        throw new RuntimeException("RazorPay: Refund update failed");
      }
    }
}
