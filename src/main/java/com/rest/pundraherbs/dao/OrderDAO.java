package com.rest.pundraherbs.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.rest.pundraherbs.model.Order;
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
		orderRepository.save(order);
		System.out.println("chit method unimplemented");
		return order;
	}

}
