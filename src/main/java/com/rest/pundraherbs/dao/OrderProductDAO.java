package com.rest.pundraherbs.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.rest.pundraherbs.model.OrderProduct;
import com.rest.pundraherbs.repository.OrderProductRepository;

@Repository
public class OrderProductDAO implements IOrderProductDAO {

	@Autowired
	OrderProductRepository orderProductRepository;

	@Override
	public OrderProduct createOrder(OrderProduct orderProduct) {
		return orderProductRepository.save(orderProduct);
	}

}
