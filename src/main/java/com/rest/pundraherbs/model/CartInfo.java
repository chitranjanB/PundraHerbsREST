package com.rest.pundraherbs.model;

import java.util.List;

/**
 * This is sent as a request body, to place an order
 * REST as of now does not support get CART operation.
 * Cart is removed once the user logsout
 */
public class CartInfo {

	private List<CartLineInfo> details;

	public List<CartLineInfo> getDetails() {
		return details;
	}

	public void setDetails(List<CartLineInfo> details) {
		this.details = details;
	}
}