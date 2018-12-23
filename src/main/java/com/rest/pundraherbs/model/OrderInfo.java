package com.rest.pundraherbs.model;

import java.util.List;

/**
 * Stores information to find orders or show on a Orders dashboard
 * This will be returned in json response, so that spring mvc can present a page using this information
 */
public class OrderInfo {

	private List<OrderDetailsInfo> details;

	public List<OrderDetailsInfo> getDetails() {
		return details;
	}

	public void setDetails(List<OrderDetailsInfo> details) {
		this.details = details;
	}
}