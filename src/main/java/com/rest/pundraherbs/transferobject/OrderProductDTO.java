package com.rest.pundraherbs.transferobject;

import com.rest.pundraherbs.model.Product;

public class OrderProductDTO {

	private Product product;
	private Integer quantity;

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

}
