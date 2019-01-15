package com.rest.pundraherbs.util;

import java.util.ArrayList;
import java.util.List;

import com.rest.pundraherbs.entity.Order;
import com.rest.pundraherbs.entity.OrderProduct;
import com.rest.pundraherbs.entity.Product;
import com.rest.pundraherbs.entity.Review;
import com.rest.pundraherbs.model.OrderDetailsInfo;
import com.rest.pundraherbs.model.OrderInfo;
import com.rest.pundraherbs.model.ProductInfo;
import com.rest.pundraherbs.model.ReviewInfo;
import com.rest.pundraherbs.model.UserInfo;

public class Util {

	public static ProductInfo convertToProductInfo(Product product) {
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

	public static Product convertToProduct(ProductInfo productInfo) {
		Product product = new Product();
		product.setProductId(productInfo.getProductId());
		product.setProductName(productInfo.getProductName());
		product.setProductType(productInfo.getProductType());
		product.setProductSummary(productInfo.getProductSummary());
		product.setProductPrice(productInfo.getProductPrice());
		product.setProductDiscount(productInfo.getProductDiscount());
		product.setProductImg(productInfo.getProductImg());
		product.setUnitInStock(productInfo.getUnitInStock());
		product.setIngredients(productInfo.getIngredients());
		product.setPackings(productInfo.getPackings());
		product.setIndications(productInfo.getIndications());
		product.setReviewComments(productInfo.getReviewComments());
		product.setDosage(productInfo.getDosage());
		return product;
	}

	public static OrderDetailsInfo convertToOrderProductInfo(OrderProduct orderProduct) {
		OrderDetailsInfo orderDetailsInfo = new OrderDetailsInfo();
		ProductInfo productInfo = new ProductInfo();
		productInfo.setProductId(orderProduct.getProduct().getProductId());
		orderDetailsInfo.setProduct(productInfo);
		orderDetailsInfo.setQuantity(orderProduct.getQuantity());
		return orderDetailsInfo;
	}

	public static OrderInfo convertToOrderInfo(Order order) {
		OrderInfo orderInfo = new OrderInfo();
		List<OrderDetailsInfo> details = new ArrayList<>();
		for (OrderProduct orderProduct : order.getOrderProducts()) {
			OrderDetailsInfo orderDetailsInfo = Util.convertToOrderProductInfo(orderProduct);
			details.add(orderDetailsInfo);
		}
		orderInfo.setDetails(details);
		orderInfo.setOrderId(order.getId());
		orderInfo.setOrderStatus(order.getStatus());
		UserInfo userInfo = new UserInfo();
		userInfo.setUserId(order.getUser().getUserId());
		orderInfo.setUserInfo(userInfo);
		return orderInfo;
	}

	public static List<OrderInfo> convertToListOfOrderInfo(List<Order> orders) {
		List<OrderInfo> listOfOrders = new ArrayList<>();
		for (Order order : orders) {
			OrderInfo orderInfo = new OrderInfo();
			List<OrderDetailsInfo> details = new ArrayList<>();
			for (OrderProduct orderProduct : order.getOrderProducts()) {
				OrderDetailsInfo orderDetailsInfo = convertToOrderProductInfo(orderProduct);
				details.add(orderDetailsInfo);
			}
			orderInfo.setDetails(details);
			orderInfo.setOrderId(order.getId());
			orderInfo.setOrderStatus(order.getStatus());
			
			UserInfo userInfo = new UserInfo();
			userInfo.setUserId(order.getUser().getUserId());
			orderInfo.setUserInfo(userInfo);
			listOfOrders.add(orderInfo);
		}
		return listOfOrders;
	}


	public static Review convertToReview(ReviewInfo reviewInfo) {
		Review review = new Review();
		review.setReviewId(reviewInfo.getReviewId());
		review.setReviewedBy(reviewInfo.getReviewedBy());
		review.setReviewComment(reviewInfo.getReviewComment());
		return review;
	}
}
