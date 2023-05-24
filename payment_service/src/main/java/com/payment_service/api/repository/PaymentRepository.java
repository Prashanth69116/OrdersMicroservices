package com.payment_service.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.payment_service.api.entity.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Integer>{

}
