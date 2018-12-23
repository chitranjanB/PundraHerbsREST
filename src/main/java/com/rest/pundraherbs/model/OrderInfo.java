package com.rest.pundraherbs.model;

import java.util.List;

/**
 * Stores information to find orders or show on a Orders dashboard This will be
 * returned in json response, so that spring mvc can present a page using this
 * information
 */
public class OrderInfo {

	private Long orderId;
	private String orderStatus;
	private List<OrderDetailsInfo> details;

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long id) {
		this.orderId = id;
	}

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	public List<OrderDetailsInfo> getDetails() {
		return details;
	}

	public void setDetails(List<OrderDetailsInfo> details) {
		this.details = details;
	}
}