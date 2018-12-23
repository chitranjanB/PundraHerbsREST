package com.rest.pundraherbs.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rest.pundraherbs.dao.IOrderProductDAO;
import com.rest.pundraherbs.model.OrderProduct;

@Service
public class OrderProductService implements IOrderProductService {

	@Autowired
	IOrderProductDAO orderProductDAO;

	@Override
	public OrderProduct createOrder(OrderProduct orderProduct) {
		return orderProductDAO.createOrder(orderProduct);
	}

}
