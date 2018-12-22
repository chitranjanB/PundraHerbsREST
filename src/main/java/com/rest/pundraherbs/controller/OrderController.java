package com.rest.pundraherbs.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.rest.pundraherbs.form.OrderForm;
import com.rest.pundraherbs.model.Order;
import com.rest.pundraherbs.service.OrderService;
import com.rest.pundraherbs.transferobject.OrderProductDTO;

@RestController
@RequestMapping(value = "/orders")
public class OrderController {

	@Autowired
	private OrderService orderService;

	@RequestMapping(method = RequestMethod.GET)
	public List<Order> getAllOrders() {
		return orderService.getAllOrders();
	}

	@RequestMapping(method = RequestMethod.POST)
	public List<Order> createOrder(@RequestBody OrderForm form) {
		return orderService.createOrder();
	}

}
