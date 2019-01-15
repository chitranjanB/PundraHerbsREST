package com.rest.pundraherbs.model;

import java.util.Set;

public class UserInfo {
	private Long userId;
	private String emailId;
	private String firstName;
	private String lastName;
	private String userName;
	private String userPhone;
	private Set<OrderInfo> orders;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPhone() {
		return userPhone;
	}

	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}

	public Set<OrderInfo> getOrders() {
		return orders;
	}

	public void setOrders(Set<OrderInfo> orders) {
		this.orders = orders;
	}

}
