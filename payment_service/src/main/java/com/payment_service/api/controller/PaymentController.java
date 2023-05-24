package com.payment_service.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.payment_service.api.entity.Payment;
import com.payment_service.api.service.PaymentService;

@RestController
@RequestMapping("/payment")
public class PaymentController {
	
	@Autowired
	private PaymentService service;
	
	@PostMapping("/dopayment")
	public Payment doPayment(@RequestBody Payment payment) {
		return service.doPayment(payment);
	}
	@DeleteMapping("/deleteAll")
	public ResponseEntity<String> deleteAll()
	{
		service.deleteAll();
		return ResponseEntity.ok("SUCCESSFULY_DELETED");
	}

}
