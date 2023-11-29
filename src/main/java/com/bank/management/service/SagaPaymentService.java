package com.bank.management.service;

import com.bank.management.entity.Event;
import com.bank.management.entity.PaymentStatus;
import com.bank.management.entity.SagaPayment;
import com.bank.management.messaging.SagaPaymentEventProducer;
import com.bank.management.repository.SagaPaymentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class SagaPaymentService {

    @Autowired
    private SagaPaymentRepository repository;

    @Autowired
    private SagaPaymentEventProducer producer;


    public void cancelPayment(Long orderId) {

        List<SagaPayment> payments = repository.getPaymentRecordsByOrderId(orderId);
        SagaPayment payment = payments.get(0);
        payment.setStatus(PaymentStatus.FAILED);
        repository.save(payment);
    }

    public void processPayment(Long orderId) {

        try {
            SagaPayment payment = new SagaPayment();
            payment.setOrderId(orderId);
            payment.setStatus(PaymentStatus.COMPLETED);
            SagaPayment p = repository.save(payment);
            Event event = new Event();
            event.setPhase("Payment");
            event.setOrderId(orderId);
            event.setEventStatus("SUCCESS");
            producer.createEvent(event);
        } catch (Exception e) {
            e.getMessage();
            Event event = new Event();
            event.setPhase("Payment");
            event.setOrderId(orderId);
            event.setEventStatus("FAILURE");
            producer.createEvent(event);
        }
    }

   /* public void processPayment(Event event){

        if("INITIATE_PAYMENT".equalsIgnoreCase(event.getEventType()) &&
                "CREDIT".equalsIgnoreCase(event.getPaymentType())) {
            SagaPayment payment = new SagaPayment();
            payment.setOrderId(event.getOrderId());
            payment.setStatus(PaymentStatus.COMPLETED);
            repository.save(payment);
            Event event2 = new Event(event.getOrderId(),"SUCCESS","");
            //event2.setEventType("SUCCESS");
            //event2.setPaymentType("");
            producer.createSuccessEvent(event2);
        }else if("INITIATE_PAYMENT".equalsIgnoreCase(event.getEventType())&&
             "COD".equalsIgnoreCase(event.getPaymentType())) {
            SagaPayment payment = new SagaPayment();
            payment.setOrderId(event.getOrderId());
            payment.setStatus(PaymentStatus.PENDING);
            repository.save(payment);
            Event event2 = new Event(event.getOrderId(),"SUCCESS","");
            //event2.setEventType("SUCCESS");
            //event2.setPaymentType("");
            producer.createSuccessEvent(event2);
        }
        else{
            SagaPayment payment = new SagaPayment();
            payment.setOrderId(event.getOrderId());
            payment.setStatus(PaymentStatus.FAILED);
            repository.save(payment);
            Event event2 = new Event(event.getOrderId(),"FAILURE","");
            //event2.setEventType("FAILURE");
            //event2.setPaymentType("");
            producer.createFailureEvent(event2);
        }
    }*/
}