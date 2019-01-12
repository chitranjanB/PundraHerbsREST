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

@Service
public class ProductService implements IProductService {

	@Autowired
	IProductDAO productDAO;

	@Override
	public List<ProductInfo> getAllProducts() {
		List<ProductInfo> listOfProductInfo = new ArrayList<>();

		for (Product product : productDAO.getAllProducts()) {
			ProductInfo productInfo = new ProductInfo();
			productInfo.setProductId(product.getProductId());
			productInfo.setProductName(product.getProductName());
			productInfo.setProductType(product.getProductType());
			productInfo.setProductSummary(product.getProductSummary());
			productInfo.setProductPrice(product.getProductPrice());
			productInfo.setProductDiscount(product.getProductDiscount());
			productInfo.setProductImg(product.getProductImg());
			productInfo.setUnitInStock(product.getUnitInStock());
			productInfo.setIngredients(product.getIngredients());
			productInfo.setPackings(product.getPackings());
			productInfo.setIndications(product.getIndications());
			productInfo.setReviewComments(product.getReviewComments());
			productInfo.setDosage(product.getDosage());

			listOfProductInfo.add(productInfo);
		}
		return listOfProductInfo;
	}

	@Override
	public List<ProductInfo> getProductByType(ProductType productType) {

		List<ProductInfo> listOfProductInfoByType = new ArrayList<>();

		for (Product product : productDAO.getProductByType(productType)) {
			ProductInfo productInfo = new ProductInfo();
			productInfo.setProductId(product.getProductId());
			productInfo.setProductName(product.getProductName());
			productInfo.setProductType(product.getProductType());
			productInfo.setProductSummary(product.getProductSummary());
			productInfo.setProductPrice(product.getProductPrice());
			productInfo.setProductDiscount(product.getProductDiscount());
			productInfo.setProductImg(product.getProductImg());
			productInfo.setUnitInStock(product.getUnitInStock());
			productInfo.setIngredients(product.getIngredients());
			productInfo.setPackings(product.getPackings());
			productInfo.setIndications(product.getIndications());
			productInfo.setReviewComments(product.getReviewComments());
			productInfo.setDosage(product.getDosage());

			listOfProductInfoByType.add(productInfo);
		}

		return listOfProductInfoByType;
	}

	@Override
	public ProductInfo getProduct(Long productId) {
		Product product = productDAO.getProduct(productId);
		
		ProductInfo productInfo = new ProductInfo();
		productInfo.setProductId(product.getProductId());
		productInfo.setProductName(product.getProductName());
		productInfo.setProductType(product.getProductType());
		productInfo.setProductSummary(product.getProductSummary());
		productInfo.setProductPrice(product.getProductPrice());
		productInfo.setProductDiscount(product.getProductDiscount());
		productInfo.setProductImg(product.getProductImg());
		productInfo.setUnitInStock(product.getUnitInStock());
		productInfo.setIngredients(product.getIngredients());
		productInfo.setPackings(product.getPackings());
		productInfo.setIndications(product.getIndications());
		productInfo.setReviewComments(product.getReviewComments());
		productInfo.setDosage(product.getDosage());
		
		return productInfo;
	}

	@Override
	public void reviewProduct(Long productId, ReviewInfo reviewInfo) {
		Review review = new Review();
		review.setReviewId(reviewInfo.getReviewId());
		review.setReviewedBy(reviewInfo.getReviewedBy());
		review.setReviewComment(reviewInfo.getReviewComment());
		productDAO.reviewProduct(productId, review);
	}

}
