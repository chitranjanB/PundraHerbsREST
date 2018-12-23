package com.rest.pundraherbs.model;

public class OrderDetailsInfo {

	private ProductInfo product;
	private Integer quantity;

	public ProductInfo getProduct() {
		return product;
	}

	public void setProduct(ProductInfo product) {
		this.product = product;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

}
