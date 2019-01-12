package com.rest.pundraherbs.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rest.pundraherbs.entity.ProductType;
import com.rest.pundraherbs.model.ProductInfo;
import com.rest.pundraherbs.model.ReviewInfo;
import com.rest.pundraherbs.service.IProductService;

@RestController
@RequestMapping(value = "/products")
public class ProductsController {

	@Autowired
	private IProductService productService;

	@RequestMapping(method = RequestMethod.GET, params="!type")
	public List<ProductInfo> getAllProducts() {
		return productService.getAllProducts();
	}

	@RequestMapping(method = RequestMethod.GET)
	public List<ProductInfo> getProducts(@RequestParam(value = "type") ProductType productType) {
		return productService.getProductByType(productType);
	}

	@RequestMapping(value = "{productId}", method = RequestMethod.GET)
	public ProductInfo getProduct(@PathVariable(value = "productId") Long productId) {
		return productService.getProduct(productId);
	}

	@RequestMapping(value = "{productId}/review", method = RequestMethod.POST)
	public ResponseEntity<String> reviewProduct(@PathVariable(value = "productId") Long productId,
			@RequestBody ReviewInfo reviewInfo) {
		productService.reviewProduct(productId, reviewInfo);
		return new ResponseEntity<String>(HttpStatus.CREATED);
	}

}
