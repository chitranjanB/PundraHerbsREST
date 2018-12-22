package com.rest.pundraherbs.form;

import java.util.List;

import com.rest.pundraherbs.transferobject.OrderProductDTO;

public class OrderForm {

	private List<OrderProductDTO> productOrders;

	public List<OrderProductDTO> getProductOrders() {
		return productOrders;
	}

	public void setProductOrders(List<OrderProductDTO> productOrders) {
		this.productOrders = productOrders;
	}
}