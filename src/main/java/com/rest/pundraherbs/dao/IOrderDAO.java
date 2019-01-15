package com.rest.pundraherbs.dao;

import java.util.List;

import com.rest.pundraherbs.entity.Order;

public interface IOrderDAO {
	public List<Order> getAllOrders();

	public Order createOrder(Order order);

	public Order getOrder(Long orderId);

	public List<Order> getOrdersByUserId(Long userId);

	public List<Order> getPendingOrders();

	public List<Order> getPendingOrdersByUserId(Long userId);

}
