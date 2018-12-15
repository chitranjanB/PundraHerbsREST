package com.rest.pundraherbs.dao;

import java.util.List;

import com.rest.pundraherbs.model.Product;
import com.rest.pundraherbs.model.Review;


public interface IProductDAO {

	public List<Product> getAllProducts();

	public Product getProduct(Long productId);

	public void reviewProduct(Long productId, Review review);

}
