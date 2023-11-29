package com.bank.management.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name="SAGA_PAYMENT_JPA")
public class SagaPayment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long paymentId;

    private Long orderId;

    @Enumerated(EnumType.STRING)
    private PaymentStatus status;
}


