package com.prashanth.orderservices.api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.prashanth.orderservices.api.common.Payment;
import com.prashanth.orderservices.api.common.TransactionRequest;
import com.prashanth.orderservices.api.common.TransactionResponse;
import com.prashanth.orderservices.api.entity.Order;
import com.prashanth.orderservices.api.repository.OrderRepository;

@Service
public class OrderService {

	@Autowired 
	private OrderRepository repository;
	
	@Autowired
	private RestTemplate template;
	
	
	
	public TransactionResponse saveOrder(TransactionRequest request) {
		
		String response="";
		Order order=request.getOrder();
		Payment payment=request.getPayment();
		payment.setOrderId(order.getId());
		payment.setAmount(order.getPrice());
		
		//rest call
		
		Payment paymentResponse = template.postForObject("http://localhost:9191/payment/dopayment", payment, Payment.class);
		
		response=paymentResponse.getPaymentStatus().equals("success")?"payment processing successfull and order placed":"there is failure in payment api, order added to cart";
		repository.save(order);
		return new TransactionResponse(order, paymentResponse.getAmount(),paymentResponse.getTransactionId(),response);
	}

	public List<Order> getOrders(Order order) {
	
		List<Order> allOrders= repository.findAll();
		return allOrders;
	}

	public Order getOrders(int id) {
		
		return repository.findById(id);
	}

	public void deleteAll() {
		// TODO Auto-generated method stub
		repository.deleteAll();
	}
}
