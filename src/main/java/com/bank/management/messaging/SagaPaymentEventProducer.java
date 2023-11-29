package com.bank.management.messaging;

import com.bank.management.entity.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class SagaPaymentEventProducer {

    @Autowired
    private KafkaTemplate<String, Event> kafkaTemplate;

    public void createEvent(Event txn) {

        kafkaTemplate.send("paymentTopic", txn);
    }
}
