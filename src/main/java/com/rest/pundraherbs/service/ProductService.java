package com.rest.pundraherbs.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rest.pundraherbs.dao.IProductDAO;
import com.rest.pundraherbs.entity.Product;
import com.rest.pundraherbs.entity.ProductType;
import com.rest.pundraherbs.entity.Review;

@Service
public class ProductService implements IProductService {

	@Autowired
	IProductDAO productDAO;

	@Override
	public List<Product> getAllProducts() {
		return productDAO.getAllProducts();
	}

	@Override
	public List<Product> getProductByType(ProductType productType) {
		return productDAO.getProductByType(productType);
	}

	@Override
	public Product getProduct(Long productId) {
		return productDAO.getProduct(productId);
	}

	@Override
	public void reviewProduct(Long productId, Review review) {
		productDAO.reviewProduct(productId, review);
	}

}
