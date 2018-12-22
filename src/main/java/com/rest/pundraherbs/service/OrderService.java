package com.rest.pundraherbs.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rest.pundraherbs.dao.IOrderDAO;
import com.rest.pundraherbs.model.Order;

@Service
public class OrderService implements IOrderService {

	@Autowired
	IOrderDAO orderDAO;

	public List<Order> getAllOrders() {
		return orderDAO.getAllOrders();
	}

	public List<Order> createOrder() {
		System.out.println("create order service");
		return null;
	}

}
