package com.rest.pundraherbs.service;

import java.util.List;

import com.rest.pundraherbs.model.CartInfo;
import com.rest.pundraherbs.model.OrderInfo;

public interface IOrderService {
	public List<OrderInfo> getAllOrders();

	public OrderInfo createOrder(CartInfo cartInfo);

}
