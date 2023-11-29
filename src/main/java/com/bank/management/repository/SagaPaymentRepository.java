package com.bank.management.repository;

import com.bank.management.entity.SagaPayment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SagaPaymentRepository extends JpaRepository<SagaPayment, Long> {

    @Query("select p from SagaPayment p where p.orderId= :orderId ")
    List<SagaPayment> getPaymentRecordsByOrderId(@Param("orderId") Long orderId);

}
