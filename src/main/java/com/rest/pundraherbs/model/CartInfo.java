package com.rest.pundraherbs.model;

import java.util.List;

/**
 * This is sent as a request body, to place an order REST as of now does not
 * support get CART operation. Cart is removed once the user logouts
 * 
 * This will be used in spring MVC to add/modify/remove products. It will not be
 * stored in db it will be in model and stored in session.
 */
public class CartInfo {

	private List<CartLineInfo> details;
	private UserInfo userInfo;

	public List<CartLineInfo> getDetails() {
		return details;
	}

	public void setDetails(List<CartLineInfo> details) {
		this.details = details;
	}

	public UserInfo getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}
}