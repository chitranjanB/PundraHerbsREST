package com.rest.pundraherbs.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.rest.pundraherbs.model.CartInfo;
import com.rest.pundraherbs.model.OrderInfo;
import com.rest.pundraherbs.service.OrderService;

@RestController
@RequestMapping(value = "/orders")
public class OrderController {

	@Autowired
	private OrderService orderService;

	@RequestMapping(method = RequestMethod.GET)
	public List<OrderInfo> getAllOrders() {
		return orderService.getAllOrders();
	}

	@RequestMapping(value = "{orderId}", method = RequestMethod.GET)
	public OrderInfo getOrder( @PathVariable(value = "orderId") Long orderId) {
		return orderService.getOrder(orderId);
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<OrderInfo> createOrder(@RequestBody CartInfo cart) {
		OrderInfo orderInfo = orderService.createOrder(cart);

		String uri = ServletUriComponentsBuilder.fromCurrentServletMapping().path("/orders/{id}")
				.buildAndExpand(orderInfo.getOrderId()).toString();
		HttpHeaders headers = new HttpHeaders();
		headers.add("Location", uri);

		return new ResponseEntity<OrderInfo>(orderInfo, headers, HttpStatus.CREATED);
	}
}
