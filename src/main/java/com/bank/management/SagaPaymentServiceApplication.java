package com.bank.management;

import com.bank.management.entity.SagaPayment;
import com.bank.management.repository.SagaPaymentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import java.sql.Date;
import java.util.Arrays;
import java.util.List;


@Slf4j
@SpringBootApplication
@EnableFeignClients
public class SagaPaymentServiceApplication {

	@Autowired
	private SagaPaymentRepository repository;

	public static void main(String[] args) {
		SpringApplication.run(SagaPaymentServiceApplication.class, args);
	}

}
