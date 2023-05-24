package com.prashanth.orderservices.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.prashanth.orderservices.api.common.Payment;
import com.prashanth.orderservices.api.common.TransactionRequest;
import com.prashanth.orderservices.api.common.TransactionResponse;
import com.prashanth.orderservices.api.entity.Order;
import com.prashanth.orderservices.api.service.OrderService;

@RestController
@RequestMapping("/order")
public class OrderController {
	
	@Autowired
	private OrderService service;
	
	/*booking an order*/
	@PostMapping("/bookOrder")
	public TransactionResponse bookOrder(@RequestBody TransactionRequest request)
	{
		return service.saveOrder(request);	
	}
	/*getting the orders*/
	@GetMapping("/orders")
	public List<Order> getOrders(Order order)
	{
		return service.getOrders(order);
	}
	@GetMapping("getOrder/{id}")
	public Order getOrder(@PathVariable int id)
	{
		return service.getOrders(id);
	}
	@DeleteMapping("deleteAll")
	public ResponseEntity<String> deleteAll()
	{
		service.deleteAll();
		return ResponseEntity.ok("SUCCESSFULLY_DELETED");
		
	}

}
