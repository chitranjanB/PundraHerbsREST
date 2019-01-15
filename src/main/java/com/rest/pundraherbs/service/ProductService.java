package com.rest.pundraherbs.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rest.pundraherbs.dao.IProductDAO;
import com.rest.pundraherbs.entity.Product;
import com.rest.pundraherbs.entity.ProductType;
import com.rest.pundraherbs.entity.Review;
import com.rest.pundraherbs.model.ProductInfo;
import com.rest.pundraherbs.model.ReviewInfo;
import com.rest.pundraherbs.util.Util;

@Service
public class ProductService implements IProductService {

	@Autowired
	IProductDAO productDAO;

	@Override
	public List<ProductInfo> getAllProducts() {
		List<ProductInfo> listOfProductInfo = new ArrayList<>();
		for (Product product : productDAO.getAllProducts()) {
			ProductInfo productInfo = Util.convertToProductInfo(product);
			listOfProductInfo.add(productInfo);
		}
		return listOfProductInfo;
	}

	@Override
	public List<ProductInfo> getProductByType(ProductType productType) {
		List<ProductInfo> listOfProductInfoByType = new ArrayList<>();
		for (Product product : productDAO.getProductByType(productType)) {
			ProductInfo productInfo = Util.convertToProductInfo(product);
			listOfProductInfoByType.add(productInfo);
		}

		return listOfProductInfoByType;
	}

	@Override
	public ProductInfo getProduct(Long productId) {
		Product product = productDAO.getProduct(productId);
		ProductInfo productInfo = Util.convertToProductInfo(product);
		return productInfo;
	}

	@Override
	public void reviewProduct(Long productId, ReviewInfo reviewInfo) {
		Review review = Util.convertToReview(reviewInfo);
		productDAO.reviewProduct(productId, review);
	}

}
