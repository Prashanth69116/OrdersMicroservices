package com.payment_service.api.service;

import java.util.Random;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.payment_service.api.entity.Payment;
import com.payment_service.api.repository.PaymentRepository;

@Service
public class PaymentService {
	
	@Autowired
	private PaymentRepository repository;
	
	public Payment doPayment(Payment payment) {
		payment.setPaymentStatus(paymentProcessing());
		payment.setTransactionId(UUID.randomUUID().toString());
		return repository.save(payment);
	}
	
	public String paymentProcessing() {
		//api should be 3rd party gateway(paypal,paytm..)
		return new Random().nextBoolean()?"success":"false";
	}

	public void deleteAll() {
		repository.deleteAll();
		
	}
	

}
