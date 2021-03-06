package com.rest.pundraherbs.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.rest.pundraherbs.entity.Order;
import com.rest.pundraherbs.repository.OrderRepository;

@Repository
public class OrderDAO implements IOrderDAO {

	@Autowired
	OrderRepository orderRepository;

	@Override
	public List<Order> getAllOrders() {
		return orderRepository.findAll();
	}

	@Override
	public Order createOrder(Order order) {
		return orderRepository.save(order);
	}

	@Override
	public Order getOrder(Long orderId) {
		return orderRepository.getOne(orderId);
	}

	@Override
	public List<Order> getOrdersByUserId(Long userId) {
		return orderRepository.findByUserUserId(userId);
	}

	@Override
	public List<Order> getPendingOrders() {
		return orderRepository.findByStatusInIgnoreCase("PENDING");
	}

	@Override
	public List<Order> getPendingOrdersByUserId(Long userId) {
		return orderRepository.findByUserUserIdAndStatusInIgnoreCase(userId, "PENDING");
	}

}
