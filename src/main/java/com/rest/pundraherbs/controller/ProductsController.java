package com.rest.pundraherbs.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.rest.pundraherbs.model.Product;
import com.rest.pundraherbs.model.Review;
import com.rest.pundraherbs.service.ProductService;

@RestController
@RequestMapping(value = "/products")
public class ProductsController {

	@Autowired
	private ProductService productService;

	@RequestMapping(method = RequestMethod.GET)
	public List<Product> getAllProducts() {
		return productService.getAllProducts();
	}
	
	@RequestMapping(value = "{productId}", method = RequestMethod.GET)
	public Product getProduct(@PathVariable(value = "productId") Long productId) {
		return productService.getProduct(productId);
	}

	@RequestMapping(value = "{productId}/review", method = RequestMethod.POST)
	public ResponseEntity<String> reviewProduct(@PathVariable(value = "productId") Long productId,
			@RequestBody Review review) {
		productService.reviewProduct(productId, review);
		return new ResponseEntity<String>(HttpStatus.CREATED);
	}

}
