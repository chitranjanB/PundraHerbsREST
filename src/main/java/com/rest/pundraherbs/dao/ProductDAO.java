package com.rest.pundraherbs.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.rest.pundraherbs.model.Product;
import com.rest.pundraherbs.model.ProductType;
import com.rest.pundraherbs.model.Review;
import com.rest.pundraherbs.repository.ProductRepository;

@Repository
public class ProductDAO implements IProductDAO {

	@Autowired
	ProductRepository productRepository;

	@Override
	public List<Product> getAllProducts() {
		return productRepository.findAll();
	}
	
	@Override
	public List<Product> getProductByType(ProductType productType) {
		return productRepository.findByProductType(productType);
	}

	@Override
	public Product getProduct(Long productId) {
		return productRepository.getOne(productId);
	}

	@Override
	public void reviewProduct(Long productId, Review review) {
		Product product = productRepository.getOne(productId);
		if (product != null && product.getReviewComments() != null) {
			product.getReviewComments().add(review);
			productRepository.save(product);
		}
	}

}
