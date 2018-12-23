package com.rest.pundraherbs.service;

import java.util.List;

import com.rest.pundraherbs.entity.Product;
import com.rest.pundraherbs.entity.ProductType;
import com.rest.pundraherbs.entity.Review;


public interface IProductService {
	public List<Product> getAllProducts();

	public List<Product> getProductByType(ProductType productType);
	
	public Product getProduct(Long productId);

	public void reviewProduct(Long productId, Review review);
}
