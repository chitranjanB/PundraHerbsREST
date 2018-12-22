package com.rest.pundraherbs.service;

import java.util.List;

import com.rest.pundraherbs.model.Order;

public interface IOrderService {
	public List<Order> getAllOrders();
	public List<Order> createOrder();

}
