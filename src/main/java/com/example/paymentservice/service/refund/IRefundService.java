package com.example.paymentservice.service.refund;

import org.json.JSONObject;

public interface IRefundService {
    String issueRefund(String paymentId, Double amount, String receipt);
    String updateRefund(String refundId, JSONObject jsonObject);
}
