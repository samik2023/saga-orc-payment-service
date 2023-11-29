package com.bank.management.controller;

import com.bank.management.service.SagaPaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Slf4j
@RestController
@RequestMapping("/api/payments")
public class SagaPaymentController {


    @Autowired
    SagaPaymentService service;

    @PostMapping(value = "/makePayment",
            produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<String> makePayment(@RequestBody Long orderId) {

        service.processPayment(orderId);
        return new ResponseEntity<>("Payment processed", HttpStatus.OK);
    }

    @PostMapping(value = "/cancelPayment",
            produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<String> cancelPayment(@RequestBody Long orderId) {

        service.cancelPayment(orderId);
        return new ResponseEntity<>("Payment cancelled", HttpStatus.OK);
    }
}
