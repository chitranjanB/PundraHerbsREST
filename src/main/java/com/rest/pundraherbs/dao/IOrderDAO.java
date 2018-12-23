package com.rest.pundraherbs.dao;

import java.util.List;

import com.rest.pundraherbs.entity.Order;

public interface IOrderDAO {
	public List<Order> getAllOrders();

	public Order createOrder(Order order);

}
