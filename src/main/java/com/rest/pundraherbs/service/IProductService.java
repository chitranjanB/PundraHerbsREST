package com.rest.pundraherbs.service;

import java.util.List;

import com.rest.pundraherbs.model.Product;
import com.rest.pundraherbs.model.Review;


public interface IProductService {
	public List<Product> getAllProducts();

	public Product getProduct(Long productId);

	void reviewProduct(Long productId, Review review);
}
