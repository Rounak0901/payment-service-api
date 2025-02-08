package com.example.paymentservice.controller;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.paymentservice.dtos.RefundRequestDto;
import com.example.paymentservice.service.IRefundService;

@RestController
public class PaymentsController {
    
    @Autowired
    private IRefundService refundService;
    
    @GetMapping("/payments")
    String hello() {
        return "Hello user,this is a payment request" ;
    }
    
    @PostMapping("/issueRefund")
    public ResponseEntity<String> issueRefund(@RequestBody RefundRequestDto requestDto){
        return new ResponseEntity<>(
            refundService.issueRefund(requestDto.getAmount(), requestDto.getReceipt()),
            HttpStatus.OK 
        );
    }

    @PatchMapping("/updateRefund/{refundId}")
    public ResponseEntity<String> updateRefund(
        @PathVariable(name = "refundId") String refundId,
        @RequestBody JSONObject jsonObject
        ){
        return new ResponseEntity<>(
            refundService.updateRefund(refundId, jsonObject) , 
            HttpStatus.OK
        );
    }
}
