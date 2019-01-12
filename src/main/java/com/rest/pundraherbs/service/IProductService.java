package com.rest.pundraherbs.service;

import java.util.List;

import com.rest.pundraherbs.entity.ProductType;
import com.rest.pundraherbs.model.ProductInfo;
import com.rest.pundraherbs.model.ReviewInfo;


public interface IProductService {
	public List<ProductInfo> getAllProducts();

	public List<ProductInfo> getProductByType(ProductType productType);
	
	public ProductInfo getProduct(Long productId);

	public void reviewProduct(Long productId, ReviewInfo reviewInfo);
}
